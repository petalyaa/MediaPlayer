package org.pet.mediaplayer;

import java.util.List;

import android.content.Context;

public abstract class BasePlayer {

	public static enum PlayerState {
		PAUSE, PLAY, STOP;
	}
	
	protected int currentTrackNumber;
	
	protected List<AudioFile> audioFiles;
	
	protected Context context;
	
	protected PlayerState state;
	
	public BasePlayer(Context context, List<AudioFile> audioFiles) {
		this.context = context;
		currentTrackNumber = -1;
		this.audioFiles = audioFiles;
	}
	
	public abstract String getNextTrack();
	
	public abstract boolean hasNextTrack();
	
	public abstract boolean hasPreviousTrack();

}
