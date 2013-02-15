package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnPauseButtonClickListener extends BaseListener implements OnClickListener {

	public OnPauseButtonClickListener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View v) {
		if(!PlayerState.PAUSE.equals(getPlayer().getState()) && !PlayerState.STOP.equals(getPlayer().getState())) {
			try {
				getPlayer().pause();
			} catch (PlayerException e) {
				e.printStackTrace();
			}
			
		}
	}

}
