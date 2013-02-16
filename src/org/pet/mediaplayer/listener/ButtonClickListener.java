package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;

import android.content.Context;
import android.view.View.OnClickListener;

public abstract class ButtonClickListener implements OnClickListener {

	protected Player player;
	
	protected Context context;
	
	public ButtonClickListener(Player player, Context context) {
		this.setPlayer(player);
		this.setContext(context);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
}
