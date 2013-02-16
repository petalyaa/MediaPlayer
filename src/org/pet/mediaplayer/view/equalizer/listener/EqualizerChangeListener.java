package org.pet.mediaplayer.view.equalizer.listener;

import android.content.Context;
import android.widget.SeekBar.OnSeekBarChangeListener;

public abstract class EqualizerChangeListener implements OnSeekBarChangeListener {

	protected Context context;
	
	protected int type;
	
	public EqualizerChangeListener(Context context, int type) {
		this.context = context;
		this.type = type;
	}
	
}
