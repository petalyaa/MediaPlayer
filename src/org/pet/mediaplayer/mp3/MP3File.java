package org.pet.mediaplayer.mp3;

import java.io.File;

import org.pet.mediaplayer.AudioFile;

public class MP3File extends AudioFile {
	
	private String songTitle;
	
	private String albumName;
	
	private String artistName;
	
	private byte[] albumArt;
	
	public MP3File(File file) {
		super(file);
		init();
	}
	
	public void init() {
		MP3ID3DataExtractor dataExtractor = new MP3ID3DataExtractor(getFile());
		setSongTitle(dataExtractor.getSongTitle());
		setAlbumName(dataExtractor.getAlbumName());
		setArtistName(dataExtractor.getArtistName());
		setDuration(dataExtractor.getDuration());
		setAlbumArt(dataExtractor.getAlbumArt());
		setCurrentDuration(0);
	}

	@Override
	public long getDuration() {
		return super.getDuration();
	}

	@Override
	public void setDuration(long duration) {
		super.setDuration(duration);
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public byte[] getAlbumArt() {
		return albumArt;
	}

	public void setAlbumArt(byte[] albumArt) {
		this.albumArt = albumArt;
	}
	
	
}
