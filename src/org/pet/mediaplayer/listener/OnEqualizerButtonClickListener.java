package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.view.equalizer.PlayerEqualizerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class OnEqualizerButtonClickListener extends ButtonClickListener {

	private PlayerEqualizerView equalizerView;
	
	public OnEqualizerButtonClickListener(Player player, Context context, ViewGroup viewGroup) {
		super(player, context);
		this.equalizerView = new PlayerEqualizerView(context, viewGroup);
	}

	@Override
	public void onClick(View v) {
		equalizerView.buildView();
	}

}
