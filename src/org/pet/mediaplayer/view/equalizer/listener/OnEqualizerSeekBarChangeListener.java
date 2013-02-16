package org.pet.mediaplayer.view.equalizer.listener;

import org.pet.mediaplayer.EqualizerHandler;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerEqualizerException;

import android.content.Context;
import android.widget.SeekBar;

public class OnEqualizerSeekBarChangeListener extends EqualizerChangeListener {

	private int type;
	
	private boolean isMasterGain = false;

	private EqualizerHandler equalizer;

	public OnEqualizerSeekBarChangeListener(Context context, int type, Player player, boolean isMasterGain) {
		super(context, type);
		this.type = type;
		try {
			this.equalizer = player.getEqualizer();
		} catch (PlayerEqualizerException e) {
			e.printStackTrace();
		}
		this.isMasterGain = isMasterGain;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		short actualGainValue = (short) (progress + equalizer.getMinimumBandGain());
		if(isMasterGain) {
			
		} else {
			equalizer.setGain((short) type, actualGainValue);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

}
