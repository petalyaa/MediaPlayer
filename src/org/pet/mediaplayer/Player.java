package org.pet.mediaplayer;

import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerException;

public interface Player {
	
	public void play() throws PlayerException;
	
	public void pause() throws PlayerException;
	
	public void stop() throws PlayerException;
	
	public void seek(long position) throws PlayerException;
	
	public void skip(int duration) throws PlayerException;
	
	public AudioFile getAudioFile();
	
	public PlayerState getState();
	
	public void setState(PlayerState state);
	
}
