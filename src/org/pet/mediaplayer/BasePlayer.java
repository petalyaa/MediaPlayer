package org.pet.mediaplayer;

import android.content.Context;

public abstract class BasePlayer {

	public static enum PlayerState {
		PAUSE, PLAY, STOP;
	}
	
	private AudioFile audioFile;
	
	protected Context context;
	
	private PlayerState state;
	
	public BasePlayer(Context context, AudioFile audioFile) {
		this.setAudioFile(audioFile);
		this.context = context;
	}

	public AudioFile getAudioFile() {
		return audioFile;
	}

	public void setAudioFile(AudioFile audioFile) {
		this.audioFile = audioFile;
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}
	
}
