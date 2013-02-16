package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;

public class OnNextTrackButtonClickListener extends ButtonClickListener {

	public OnNextTrackButtonClickListener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View arg0) {
		try {
			player.next();
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

}
