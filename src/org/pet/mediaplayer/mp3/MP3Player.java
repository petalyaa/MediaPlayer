package org.pet.mediaplayer.mp3;

import java.io.IOException;

import org.pet.mediaplayer.AudioFile;
import org.pet.mediaplayer.BasePlayer;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

public class MP3Player extends BasePlayer implements Player {
	
	private static final String TAG = MP3Player.class.getName();

	private MediaPlayer mediaPlayer;
	
	private SeekBar seekBar;
	
	private Thread seekBarThread;
	
	private MP3SeekBarThread runnable;
	
	public MP3Player(Context context, MP3File file, SeekBar seekBar) {
		super(context, file);
		setState(PlayerState.STOP);
		this.seekBar = seekBar;
	}
	
	public void startSeekBarThread() {
		runnable = new MP3SeekBarThread(seekBar, mediaPlayer, this);
		seekBarThread = new Thread(runnable);
		seekBarThread.start();
	}
	
	private void init() {
		AudioFile audioFile = getAudioFile();
		String filePath = audioFile.getFilePath();
		try {
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
	public void play() throws PlayerException {
		try {
			if(PlayerState.STOP.equals(getState())) {
				setState(PlayerState.PLAY);
				init();
			}
			Log.v(TAG, "Currently playing.");
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					stop();
				}
			});
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
		runnable.setSeekBar(seekBar);
	}

	@Override
	public void onPlayerDestroy() {
		runnable.terminate();
		if(getState().equals(PlayerState.PAUSE)) {
			stop();
		}
	}

	@Override
	public void resetSeekBar(SeekBar seekbar) {
		this.seekBar = seekbar;
	}

}
