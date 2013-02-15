package org.pet.mediaplayer.mp3;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;

import android.media.MediaPlayer;
import android.widget.SeekBar;

public class MP3SeekBarThread extends Thread {
	
	private SeekBar seekbar;
	
	private MediaPlayer mediaPlayer;
	
	private Player player;
	
	public MP3SeekBarThread(SeekBar seekbar, MediaPlayer mediaPlayer, Player player) {
		this.seekbar = seekbar;
		this.mediaPlayer = mediaPlayer;
		this.player = player;
	}
	
	public void run() {
		while(!player.getState().equals(PlayerState.STOP)) {
			if(!player.getState().equals(PlayerState.PAUSE)) {
				int currentPosition = mediaPlayer.getCurrentPosition();
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
