package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @deprecated Use {@link OnPlayPauseButtonClickListener} instead to toggle play/pause now.
 * @author khairul.ikhwan
 *
 */
@Deprecated
public class OnPlayButtonClickListener extends ButtonClickListener {
	
	public OnPlayButtonClickListener(Player player, Context context) {
		super(player, context);
	}

	@Override
	public void onClick(View v) {
		if(!PlayerState.PLAY.equals(player.getState())) {
			try {
				player.play();
			} catch (PlayerException e) {
				Toast.makeText(context, "Failed!!!", Toast.LENGTH_LONG).show();
			}
		}
	}

}
