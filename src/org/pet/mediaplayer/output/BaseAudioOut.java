package org.pet.mediaplayer.output;

import org.pet.mediaplayer.exception.OutputAddressNotFoundException;

public abstract class BaseAudioOut {
	
	public abstract void setSpeakerAddress(int add) throws OutputAddressNotFoundException;
	
	public abstract int getSpeakerAddress();
	
	public BaseAudioOut() {
		
	}

}
