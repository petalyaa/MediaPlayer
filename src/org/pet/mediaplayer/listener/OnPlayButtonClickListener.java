package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnPlayButtonClickListener extends BaseListener implements OnClickListener {
	
	public OnPlayButtonClickListener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View v) {
		if(!PlayerState.PLAY.equals(getPlayer().getState())) {
			try {
				getPlayer().play();
			} catch (PlayerException e) {
				Toast.makeText(getContext(), "Failed!!!", Toast.LENGTH_LONG).show();
			}
		}
	}

}
