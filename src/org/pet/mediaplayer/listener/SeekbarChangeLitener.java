package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;

import android.content.Context;
import android.widget.SeekBar.OnSeekBarChangeListener;

public abstract class SeekbarChangeLitener implements OnSeekBarChangeListener {
	
	protected Player player;
	
	protected Context context;
	
	public SeekbarChangeLitener(Player player, Context context){
		this.player = player;
		this.context = context;
	}

}
