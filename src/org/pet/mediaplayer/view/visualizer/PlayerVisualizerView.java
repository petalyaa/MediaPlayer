package org.pet.mediaplayer.view.visualizer;

import org.pet.mediaplayer.PlayerFragmentView;
import org.pet.mediaplayer.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlayerVisualizerView extends PlayerFragmentView {

	public PlayerVisualizerView(Context context, ViewGroup viewGroup) {
		super(context, viewGroup);
	}

	@Override
	public View buildView() {
		View view = View.inflate(context, R.layout.visualization_view, null);
		viewGroup.removeAllViews();
		
		ViewGroup visualizerViewGroup = (ViewGroup) view.findViewById(R.id.visualizer_relative_layout);
		TextView test = new TextView(context);
		test.setText("this is just a test");
		visualizerViewGroup.addView(test);
		
		viewGroup.addView(view);
		return view;
	}

}
