package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.exception.PlayerException;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

public class OnStopButtonClickListener extends ButtonClickListener {

	private ImageButton playPauseButton;
	
	public OnStopButtonClickListener(Player player, Context context, ImageButton playPauseButton) {
		super(player, context);
		this.playPauseButton = playPauseButton;
	}

	@Override
	public void onClick(View v) {
		if(!PlayerState.STOP.equals(player.getState())) {
			try {
				player.signalUserInteruption();
				player.stop();
				this.playPauseButton.setImageResource(R.drawable.play);
			} catch (PlayerException e) {
				e.printStackTrace();
			}
			
		}
	}

}
