package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.status.RepeatStatus;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class OnRepeatButtonClickListener extends ButtonClickListener {

	private TextView repeatLabel;
	
	public OnRepeatButtonClickListener(Player player, Context context, TextView repeatLabel) {
		super(player, context);
		this.repeatLabel = repeatLabel;
		toggleLabel();
	}
	
	private void toggleLabel() {
		int repeatStatus = player.getRepeatStatus();
		if(repeatStatus == RepeatStatus.OFF) {
			repeatLabel.setText(context.getString(R.string.repeat_off));
		} else if (repeatStatus == RepeatStatus.REPEAT_ALL) {
			repeatLabel.setText(context.getString(R.string.repeat_all));
		} else {
			repeatLabel.setText(context.getString(R.string.repeat_song));
		}
	}

	@Override
	public void onClick(View v) {
		int repeatStatus = player.getRepeatStatus();
		if(repeatStatus == RepeatStatus.OFF) {
			player.setRepeatStatus(RepeatStatus.REPEAT_ALL);
		} else if (repeatStatus == RepeatStatus.REPEAT_ALL) {
			player.setRepeatStatus(RepeatStatus.REPEAT_SONG);
		} else {
			player.setRepeatStatus(RepeatStatus.OFF);
		}
		toggleLabel();
	}

}
