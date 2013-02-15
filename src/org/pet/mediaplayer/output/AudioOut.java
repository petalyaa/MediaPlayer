package org.pet.mediaplayer.output;

import org.pet.mediaplayer.exception.PlayerAudioException;

public interface AudioOut {

	public void sendStream(byte[] b) throws PlayerAudioException;
	
}
