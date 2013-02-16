package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;

public class OnPreviousTrackButtonClickLitener extends ButtonClickListener {

	public OnPreviousTrackButtonClickLitener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View v) {
		try {
			player.previous();
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

}
