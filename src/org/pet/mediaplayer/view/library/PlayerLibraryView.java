package org.pet.mediaplayer.view.library;

import java.util.ArrayList;

import org.pet.mediaplayer.PlayerFragmentView;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.R.id;
import org.pet.mediaplayer.R.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PlayerLibraryView extends PlayerFragmentView {

	public PlayerLibraryView(Context context, ViewGroup viewGroup) {
		super(context, viewGroup);
	}

	@Override
	public View buildView() {
		View view = View.inflate(context, R.layout.library_view, null);
		viewGroup.removeAllViews();
		
		ArrayList<String> data = new ArrayList<String>();
		String[] categoryArray = context.getResources().getStringArray(R.array.category_list);
		for (int i = 0; i < categoryArray.length; i++) {
			data.add(categoryArray[i]);
		}
		
		final ListView categoryListView = (ListView) view.findViewById(R.id.library_category_view);
		PlayerLibraryCategoryListAdapter adapter = new PlayerLibraryCategoryListAdapter(data, context);
		categoryListView.setAdapter(adapter);
		
		viewGroup.addView(view);
		return view;
	}
	

}
