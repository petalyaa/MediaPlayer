package org.pet.mediaplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class PlayerFragmentView {
	
	protected Context context;
	
	protected ViewGroup viewGroup;
	
	public PlayerFragmentView(Context context, ViewGroup viewGroup) {
		this.context = context;
		this.viewGroup = viewGroup;
	}
	
	public abstract View buildView();
	
}
