package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class OnShuffleButtonClickListener extends ButtonClickListener {

	private TextView shuffleLabel;
	
	public OnShuffleButtonClickListener(Player player, Context context, TextView shuffleLabel) {
		super(player, context);
		this.shuffleLabel = shuffleLabel;
	}
	
	private void toggleLabel() {
		
	}

	@Override
	public void onClick(View v) {
		
	}

}
