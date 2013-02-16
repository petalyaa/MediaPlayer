package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.exception.PlayerException;
import org.pet.mediaplayer.status.MuteStatus;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

public class OnMuteButtonClickListener extends ButtonClickListener {

	private ImageButton muteButton;
	
	public OnMuteButtonClickListener(Player player, Context context, ImageButton muteButton) {
		super(player, context);
		this.muteButton = muteButton;
		toggleMuteImage();
	}

	private void toggleMuteImage() {
		int currentMuteStatus = player.getMuteStatus();
		if(currentMuteStatus != MuteStatus.MUTE) {
			muteButton.setImageResource(R.drawable.speaker);
		} else {
			muteButton.setImageResource(R.drawable.mute);
		}
	}
	
	@Override
	public void onClick(View v) {
		int currentMuteStatus = player.getMuteStatus();
		try {
			if (currentMuteStatus == MuteStatus.MUTE) {
				player.unmute();
			} else {
				player.mute();
			}
			toggleMuteImage();
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

}
