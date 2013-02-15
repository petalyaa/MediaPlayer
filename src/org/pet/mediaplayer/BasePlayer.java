package org.pet.mediaplayer;

public class BasePlayer {

	public static enum PlayerState {
		PAUSE, PLAY, STOP;
	}
	
	private AudioFile audioFile;
	
	private PlayerState state;
	
	public BasePlayer(AudioFile audioFile) {
		this.setAudioFile(audioFile);
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
