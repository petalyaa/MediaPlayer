package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnStopButtonClickListener extends BaseListener implements OnClickListener {

	public OnStopButtonClickListener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View v) {
		if(!PlayerState.STOP.equals(getPlayer().getState())) {
			try {
				getPlayer().stop();
			} catch (PlayerException e) {
				e.printStackTrace();
			}
			
		}
	}

}
