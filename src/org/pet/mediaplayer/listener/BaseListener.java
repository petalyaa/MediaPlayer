package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;

import android.content.Context;

public class BaseListener {

	private Player player;
	
	private Context context;
	
	public BaseListener(Player player, Context context) {
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
