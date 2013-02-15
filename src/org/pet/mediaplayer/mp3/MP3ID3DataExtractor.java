package org.pet.mediaplayer.mp3;

import java.io.File;

import android.media.MediaMetadataRetriever;

public class MP3ID3DataExtractor {
	
	private MediaMetadataRetriever metaRetriver;
	
	public MP3ID3DataExtractor(File mp3File){
		metaRetriver = new MediaMetadataRetriever();
		metaRetriver.setDataSource(mp3File.getPath());
	}
	
	public String getSongTitle() {
		return metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
	}
	
	public long getDuration() {
		String durationStr = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		return Long.valueOf(durationStr);
	}
	
	public byte[] getAlbumArt() {
		return metaRetriver.getEmbeddedPicture();
	}
	
	public String getArtistName() {
		return metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
	}
	
	public String getAlbumName() {
		return metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
	}

}
