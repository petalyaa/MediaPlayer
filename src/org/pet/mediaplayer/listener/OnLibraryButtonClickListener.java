package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.view.library.PlayerLibraryView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class OnLibraryButtonClickListener extends ButtonClickListener {

	private PlayerLibraryView libraryView;
	
	public OnLibraryButtonClickListener(Player player, Context context, ViewGroup viewGroup) {
		super(player, context);
		this.libraryView = new PlayerLibraryView(context, viewGroup);
	}

	@Override
	public void onClick(View arg0) {
		libraryView.buildView();
	}

}
