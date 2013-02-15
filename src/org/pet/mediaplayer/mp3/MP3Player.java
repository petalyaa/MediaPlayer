package org.pet.mediaplayer.mp3;

import java.io.IOException;

import org.pet.mediaplayer.AudioFile;
import org.pet.mediaplayer.BasePlayer;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerException;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;

public class MP3Player extends BasePlayer implements Player {
	
	private static final String TAG = MP3Player.class.getName();

	private MediaPlayer mediaPlayer;
	
	private SeekBar seekBar;
	
	private Thread seekBarThread;
	
	public MP3Player(MP3File file, SeekBar seekBar) {
		super(file);
		setState(PlayerState.STOP);
		this.seekBar = seekBar;
	}
	
	private void init() {
		AudioFile audioFile = getAudioFile();
		String filePath = audioFile.getFilePath();
		try {
			this.mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(filePath);
			mediaPlayer.prepare();
			seekBarThread = new MP3SeekBarThread(seekBar, mediaPlayer, this);
			seekBarThread.start();
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
			setState(PlayerState.STOP);
			Log.v(TAG, "Releasing audio track now.");
		}
	}

	@Override
	public void seek(long position) {
		mediaPlayer.seekTo((int) position);
	}

	@Override
	public void skip(int duration) {

	}

}
