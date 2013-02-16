package org.pet.mediaplayer;

import android.widget.TextView;

public class InfoPanelView {
	
	private TextView albumTitle;
	
	private TextView songTitle;
	
	private TextView artistName;
	
	private TextView endTime;
	
	public InfoPanelView(TextView albumTitle, TextView songTitle, TextView artistName, TextView endTime) {
		this.albumTitle = albumTitle;
		this.songTitle = songTitle;
		this.artistName = artistName;
		this.endTime = endTime;
	}
	
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle.setText(albumTitle);
	}
	
	public void setSongTitle(String songTitle) {
		this.songTitle.setText(songTitle);
	}
	
	public void setArtistName(String artistName) {
		this.artistName.setText(artistName);
	}
	
	public void setEndTime(String endTime) {
		this.endTime.setText(endTime);
	}

}
