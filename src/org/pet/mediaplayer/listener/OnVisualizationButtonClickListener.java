package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.view.visualizer.PlayerVisualizerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class OnVisualizationButtonClickListener extends ButtonClickListener {

	private PlayerVisualizerView visualizerView;
	
	public OnVisualizationButtonClickListener(Player player, Context context, ViewGroup viewGroup) {
		super(player, context);
		this.visualizerView = new PlayerVisualizerView(context, viewGroup);
	}

	@Override
	public void onClick(View v) {
		visualizerView.buildView();
	}

}
