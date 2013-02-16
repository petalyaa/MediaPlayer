package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class OnLibraryButtonClickListener extends ButtonClickListener {

	private ViewGroup viewGroup;
	
	public OnLibraryButtonClickListener(Player player, Context context, ViewGroup viewGroup) {
		super(player, context);
		this.viewGroup = viewGroup;
	}

	@Override
	public void onClick(View arg0) {
		viewGroup.removeAllViews();
		viewGroup.addView(View.inflate(context, R.layout.library_view, null));
	}

}
