package org.pet.mediaplayer;

import java.io.File;

public class AudioFile {
	
	private File file;
	
	private long duration;
	
	private boolean pausable;
	
	private long currentDuration;
	
	public AudioFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}
	
	public String getFilePath() {
		return file.getPath();
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getCurrentDuration() {
		return currentDuration;
	}

	public void setCurrentDuration(long currentDuration) {
		this.currentDuration = currentDuration;
	}

	public boolean isPausable() {
		return pausable;
	}

	public void setPausable(boolean pausable) {
		this.pausable = pausable;
	}

}
