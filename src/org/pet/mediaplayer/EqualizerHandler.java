package org.pet.mediaplayer;

import org.pet.mediaplayer.exception.PlayerEqualizerException;

import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.util.Log;

public class EqualizerHandler {

	private static final int PRIORITY = 1;
	
	private Equalizer equalizer;
	
	private short totalBandSize;
	
	private short maximumBandGain;
	
	private short minimumBandGain;
	
	public EqualizerHandler(MediaPlayer mediaPlayer) throws PlayerEqualizerException {
		try {
			equalizer = new Equalizer(PRIORITY, mediaPlayer.getAudioSessionId());
			equalizer.setEnabled(true);
			init();
		} catch (Exception e) {
			throw new PlayerEqualizerException("Fail to construct new equalizer object", e);
		}
	}
	
	public void init() {
		setTotalBandSize(equalizer.getNumberOfBands());
		setMinimumBandGain(equalizer.getBandLevelRange()[0]);
		setMaximumBandGain(equalizer.getBandLevelRange()[1]);
	}
	
	public void setGain(short frequency, short gain) {
		Log.d("equalizer", "Changing gain for band " + frequency + " to " + gain);
		equalizer.setBandLevel(frequency, gain);
	}

	public short getTotalBandSize() {
		return totalBandSize;
	}

	public void setTotalBandSize(short totalBandSize) {
		this.totalBandSize = totalBandSize;
	}

	public short getMaximumBandGain() {
		return maximumBandGain;
	}

	public void setMaximumBandGain(short maximumBandGain) {
		this.maximumBandGain = maximumBandGain;
	}

	public short getMinimumBandGain() {
		return minimumBandGain;
	}

	public void setMinimumBandGain(short minimumBandGain) {
		this.minimumBandGain = minimumBandGain;
	}
	
	public Equalizer getEqualizerObject() {
		return equalizer;
	}
	
	

}
