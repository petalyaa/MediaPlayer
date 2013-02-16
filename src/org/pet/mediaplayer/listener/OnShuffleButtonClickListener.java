package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.status.ShuffleStatus;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class OnShuffleButtonClickListener extends ButtonClickListener {

	private TextView shuffleLabel;
	
	public OnShuffleButtonClickListener(Player player, Context context, TextView shuffleLabel) {
		super(player, context);
		this.shuffleLabel = shuffleLabel;
		toggleLabel();
	}
	
	private void toggleLabel() {
		int shuffleStatus = player.getShuffleStatus();
		if(shuffleStatus == ShuffleStatus.ON) {
			shuffleLabel.setText(context.getString(R.string.shuffle_on));
		} else {
			shuffleLabel.setText(context.getString(R.string.shuffle_off));
		}
	}

	@Override
	public void onClick(View v) {
		int shuffleStatus = player.getShuffleStatus();
		if(shuffleStatus == ShuffleStatus.ON) {
			player.setShuffleStatus(ShuffleStatus.OFF);
		} else {
			player.setShuffleStatus(ShuffleStatus.ON);
		}
		toggleLabel();
	}

}
