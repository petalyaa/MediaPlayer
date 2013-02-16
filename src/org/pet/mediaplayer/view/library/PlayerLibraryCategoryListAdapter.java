package org.pet.mediaplayer.view.library;

import java.util.ArrayList;

import org.pet.mediaplayer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerLibraryCategoryListAdapter extends BaseAdapter {
	
	private ArrayList<String> data;
	
	private Context context;
	
	private LayoutInflater inflater;
	
	public PlayerLibraryCategoryListAdapter(ArrayList<String> data, Context context) {
		this.data = data;
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data != null ? data.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return data != null ? data.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		Object item = getItem(position);
		return item != null ? item.hashCode() : 0;
	}

	@Override
	public View getView(int position, View switchView, ViewGroup viewGroup) {
		View view = inflater.inflate(R.layout.library_category_item_list_view, null, false);
		TextView categoryLabel = (TextView) view.findViewById(R.id.library_category_label);
		ImageView categoryImage = (ImageView) view.findViewById(R.id.library_category_image);
		String category = data.get(position);
		if(position == 0) { // artist
			categoryImage.setImageResource(R.drawable.artist);
		} else if (position == 1) { // Album
			categoryImage.setImageResource(R.drawable.album);
		} else if (position == 2) { // Song
			categoryImage.setImageResource(R.drawable.song);
		} else if (position == 3) { // Genre
			categoryImage.setImageResource(R.drawable.genre);
		} else if (position == 4) { // Playlist
			categoryImage.setImageResource(R.drawable.playlist);
		}
		categoryLabel.setText(category);
		return view;
	}

}
