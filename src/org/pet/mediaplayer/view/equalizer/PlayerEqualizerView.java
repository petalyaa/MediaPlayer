package org.pet.mediaplayer.view.equalizer;

import org.pet.mediaplayer.PlayerFragmentView;
import org.pet.mediaplayer.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class PlayerEqualizerView extends PlayerFragmentView {

	public PlayerEqualizerView(Context context, ViewGroup viewGroup) {
		super(context, viewGroup);
	}

	@Override
	public View buildView() {
		View view = View.inflate(context, R.layout.equalizer_view, null);
		viewGroup.removeAllViews();
		
		viewGroup.addView(view);
		return view;
	}

}
