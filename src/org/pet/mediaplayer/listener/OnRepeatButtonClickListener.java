package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class OnRepeatButtonClickListener extends ButtonClickListener {

	private TextView repeatLabel;
	
	public OnRepeatButtonClickListener(Player player, Context context, TextView repeatLabel) {
		super(player, context);
		this.repeatLabel = repeatLabel;
	}
	
	private void toggleLabel() {
		
	}

	@Override
	public void onClick(View v) {
		
	}

}
