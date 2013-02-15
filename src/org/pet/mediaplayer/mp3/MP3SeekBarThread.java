package org.pet.mediaplayer.mp3;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;

public class MP3SeekBarThread implements Runnable {
	
	public SeekBar seekbar;
	
	private MediaPlayer mediaPlayer;
	
	private Player player;
	
	private boolean term;
	
	private boolean isRunning;
	
	public MP3SeekBarThread(SeekBar sb, MediaPlayer mediaPlayer, Player player) {
		this.seekbar = sb;
		this.mediaPlayer = mediaPlayer;
		this.player = player;
	}
	
	public void setSeekBar(SeekBar seekbar) {
		this.seekbar = seekbar;
	}
	
	public void terminate() {
		term = true;
	}
	
	public boolean isCurrentlyRunning() {
		return isRunning;
	}
	
	public void run() {
		isRunning = true;
		Log.d("CurrentState", "Current state is : " + player.getState());
		while(!player.getState().equals(PlayerState.STOP) && !term) {
			if(!player.getState().equals(PlayerState.PAUSE)) {
				int currentPosition = mediaPlayer.getCurrentPosition();
				Log.v("Current", "Current position : " + currentPosition);
				seekbar.setProgress(currentPosition);
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		seekbar.setProgress(0);
	}

}
