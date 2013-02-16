package org.pet.mediaplayer;

import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.exception.PlayerEqualizerException;
import org.pet.mediaplayer.exception.PlayerException;

import android.media.MediaPlayer;
import android.widget.ImageButton;
import android.widget.SeekBar;

public interface Player {
	
	public void play() throws PlayerException;
	
	public void pause() throws PlayerException;
	
	public void stop() throws PlayerException;
	
	public void seek(long position) throws PlayerException;
	
	public void skip(int duration) throws PlayerException;
	
	public void next() throws PlayerException;
	
	public void previous() throws PlayerException;
	
	public void mute() throws PlayerException;
	
	public void unmute() throws PlayerException;
	
	public int getMuteStatus();
	
	public void setRepeatStatus(int repeatStatus);
	
	public int getRepeatStatus();
	
	public void setShuffleStatus(int shuffleStatus);
	
	public int getShuffleStatus();
	
	public AudioFile getAudioFile();
	
	public PlayerState getState();
	
	public void setState(PlayerState state);
	
	public boolean isCurrentlyPlayed();
	
	public void startSeekBarThread();
	
	public void onPlayerResume(SeekBar seekBar);
	
	public void onPlayerDestroy();
	
	public void resetSeekBar(SeekBar seekbar);
	
	public void resetInfoPanel(InfoPanelView infoPanel);
	
	public void resetPlayButton(ImageButton playButton);
	
	public void signalUserInteruption();
	
	public MediaPlayer getMediaPlayerSession();
	
	public EqualizerHandler getEqualizer() throws PlayerEqualizerException;
}
