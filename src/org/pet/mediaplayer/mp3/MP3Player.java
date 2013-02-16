package org.pet.mediaplayer.mp3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.pet.mediaplayer.AudioFile;
import org.pet.mediaplayer.BasePlayer;
import org.pet.mediaplayer.InfoPanelView;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.exception.PlayerException;
import org.pet.mediaplayer.status.MuteStatus;
import org.pet.mediaplayer.status.RepeatStatus;
import org.pet.mediaplayer.util.TimeUtil;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MP3Player extends BasePlayer implements Player {
	
	private static final String TAG = MP3Player.class.getName();

	private MediaPlayer mediaPlayer;
	
	private SeekBar seekBar;
	
	private Thread seekBarThread;
	
	private MP3SeekBarThread runnable;
	
	private int muteStatus;
	
	private int shuffleStatus;
	
	private int repeatStatus;
	
	private InfoPanelView infoPanel;
	
	private ImageButton playButton;
	
	private boolean isUserInteruption;
	
	public MP3Player(Context context, List<AudioFile> mp3Files, SeekBar seekBar, InfoPanelView infoPanel, ImageButton playButton) {
		super(context, mp3Files);
		setState(PlayerState.STOP);
		this.seekBar = seekBar;
		this.muteStatus = MuteStatus.UNMUTE;
		this.infoPanel = infoPanel;
		this.playButton = playButton;
	}
	
	public void startSeekBarThread() {
		runnable = new MP3SeekBarThread(seekBar, mediaPlayer, this);
		seekBarThread = new Thread(runnable);
		seekBarThread.start();
	}
	
	@Override
	public String getNextTrack() {
		currentTrackNumber++;
		AudioFile file = null;
		String filePath = null;
		try {
			
			// Handle repeat here.
			if(repeatStatus == RepeatStatus.REPEAT_SONG && currentTrackNumber > 0) {
				currentTrackNumber--;
			}
			
			Log.v(TAG, "Getting track number " + currentTrackNumber);
			file = audioFiles.get(currentTrackNumber);
			filePath = file.getFilePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	private void writeInfo(String filePath) {
		MP3File mp3File = new MP3File(new File(filePath));
		long totalDuration = mp3File.getDuration();
		String endTimeStr = TimeUtil.secondsToString((int) (totalDuration / 1000));
		seekBar.setMax((int) totalDuration);
		infoPanel.setAlbumTitle(mp3File.getAlbumName());
		infoPanel.setArtistName(mp3File.getArtistName());
		infoPanel.setSongTitle(mp3File.getSongTitle());
		infoPanel.setEndTime(endTimeStr);
	}
	
	private void init() throws PlayerException {
		String filePath = getNextTrack();
		if(filePath == null) {
			throw new PlayerException("No file to play.");
		}
		try {
			writeInfo(filePath);
			this.mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(filePath);
			mediaPlayer.prepare();
			startSeekBarThread();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean hasPreviousTrack() {
		int nextTrackNumber = currentTrackNumber - 1;
		AudioFile file = null;
		try {
			file = audioFiles.get(nextTrackNumber);
		} catch (Exception e) {
		}
		return file != null && file.getFilePath() != null && !file.getFilePath().equals("");
	}
	
	@Override
	public boolean hasNextTrack() {
		int nextTrackNumber = currentTrackNumber + 1;
		AudioFile file = null;
		try {
			file = audioFiles.get(nextTrackNumber);
		} catch (Exception e) {
		}
		return file != null && file.getFilePath() != null && !file.getFilePath().equals("");
	}

	@Override
	public void next() throws PlayerException {
		if(hasNextTrack()) {
			stop();
			try {
				play();
			} catch (PlayerException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(context, context.getString(R.string.end_of_list), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void previous() throws PlayerException {
		if(hasPreviousTrack()) {
			currentTrackNumber--;
			signalUserInteruption();
			stop();
			try {
				play();
			} catch (PlayerException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(context, context.getString(R.string.end_of_list), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void play() throws PlayerException {
		try {
			if(PlayerState.STOP.equals(state)) {
				setState(PlayerState.PLAY);
				init();
			}
			Log.v(TAG, "Currently playing.");
			if(muteStatus == MuteStatus.MUTE) {
				mute();
			}
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					stop();
					if(hasNextTrack()) {
						try {
							play();
						} catch (PlayerException e) {
							e.printStackTrace();
						}
					} else {
						currentTrackNumber--;
					}
				}
			});
			playButton.setImageResource(R.drawable.pause);
			setState(PlayerState.PLAY);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	@Override
	public void pause() {
		if(!PlayerState.PAUSE.equals(getState())) {
			mediaPlayer.pause();
			setState(PlayerState.PAUSE);
		}
	}

	@Override
	public void stop() {
		if(!PlayerState.STOP.equals(getState())) {
			mediaPlayer.stop();
			mediaPlayer.release();
			runnable.terminate();
			setState(PlayerState.STOP);
			if(isUserInteruption && repeatStatus != RepeatStatus.REPEAT_SONG) {
				currentTrackNumber--;
			}
			isUserInteruption = false;
			playButton.setImageResource(R.drawable.play);
			Log.v(TAG, "Releasing audio track now.");
		}
	}

	@Override
	public void seek(long position) {
		try {
			if(mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.seekTo((int) position);
			}
		} catch (IllegalStateException e) {
			Toast.makeText(context, "Fail to seek to position " + position, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void skip(int duration) {

	}

	@Override
	public boolean isCurrentlyPlayed() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public void onPlayerResume(SeekBar seekBar) {
		startSeekBarThread();
		if(runnable != null) {
			runnable.setSeekBar(seekBar);
		}
	}

	@Override
	public void onPlayerDestroy() {
		if(runnable != null) {
			runnable.terminate();
		}
		if(state.equals(PlayerState.PAUSE)) {
			stop();
		}
	}

	@Override
	public void resetSeekBar(SeekBar seekbar) {
		this.seekBar = seekbar;
	}

	@Override
	public void mute() throws PlayerException {
		if(mediaPlayer != null) {
			mediaPlayer.setVolume(0, 0);
		}
		muteStatus = MuteStatus.MUTE;
	}

	@Override
	public void unmute() throws PlayerException {
		if(mediaPlayer != null) {
			mediaPlayer.setVolume(1, 1);
		}
		muteStatus = MuteStatus.UNMUTE;
	}

	@Override
	public int getMuteStatus() {
		return muteStatus;
	}

	@Override
	public int getRepeatStatus() {
		return repeatStatus;
	}

	@Override
	public int getShuffleStatus() {
		return shuffleStatus;
	}

	@Override
	public AudioFile getAudioFile() {
		return null;
	}

	@Override
	public PlayerState getState() {
		return state;
	}

	@Override
	public void setState(PlayerState state) {
		this.state = state;
	}

	@Override
	public void resetInfoPanel(InfoPanelView infoPanel) {
		this.infoPanel = infoPanel;		
	}

	@Override
	public void resetPlayButton(ImageButton playButton) {
		this.playButton = playButton;
	}

	@Override
	public void signalUserInteruption() {
		this.isUserInteruption = true;
	}

	@Override
	public void setRepeatStatus(int repeatStatus) {
		this.repeatStatus = repeatStatus;
	}

	@Override
	public void setShuffleStatus(int shuffleStatus) {
		this.shuffleStatus = shuffleStatus;
	}

}
