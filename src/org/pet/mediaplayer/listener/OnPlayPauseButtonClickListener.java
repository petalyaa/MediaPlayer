package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class OnPlayPauseButtonClickListener extends ButtonClickListener {

	private ImageButton button;
	
	public OnPlayPauseButtonClickListener(Player player, Context context, ImageButton button) {
		super(player, context);
		this.button = button;
		toggleButtonImage();
	}
	
	private void toggleButtonImage() {
		PlayerState currentState = player.getState();
		if(PlayerState.PLAY.equals(currentState)) {
			button.setImageResource(R.drawable.pause);
		} else {
			button.setImageResource(R.drawable.play);
		}
	}

	@Override
	public void onClick(View v) {
		PlayerState currentState = player.getState();
		try {
			if (PlayerState.PLAY.equals(currentState)) {
				player.pause();
			} else {
				player.play();
			}
		} catch (PlayerException e) {
			Toast.makeText(context, "Failed!!!", Toast.LENGTH_LONG).show();
		}

		toggleButtonImage();
	}

}
