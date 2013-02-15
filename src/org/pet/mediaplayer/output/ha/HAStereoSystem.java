package org.pet.mediaplayer.output.ha;

import org.pet.mediaplayer.exception.OutputAddressNotFoundException;
import org.pet.mediaplayer.exception.PlayerAudioException;
import org.pet.mediaplayer.output.AudioOut;
import org.pet.mediaplayer.output.BaseAudioOut;

public class HAStereoSystem extends BaseAudioOut implements AudioOut {

	private int address;
	
	public HAStereoSystem(int address) throws OutputAddressNotFoundException {
		setSpeakerAddress(address);
	}
	
	@Override
	public void sendStream(byte[] b) throws PlayerAudioException {
		// TODO : Send audio to HA controller to be played through home stereo system wirelessly.
		// Need code to sync audio stream to all speaker system.
	}

	@Override
	public void setSpeakerAddress(int add) throws OutputAddressNotFoundException {
		this.address = add;
	}

	@Override
	public int getSpeakerAddress() {
		return this.address;
	}

}
