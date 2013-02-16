package org.pet.mediaplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.pet.mediaplayer.BasePlayer.PlayerState;
import org.pet.mediaplayer.listener.OnAudioSeekBarChangeListener;
import org.pet.mediaplayer.listener.OnEqualizerButtonClickListener;
import org.pet.mediaplayer.listener.OnLibraryButtonClickListener;
import org.pet.mediaplayer.listener.OnMuteButtonClickListener;
import org.pet.mediaplayer.listener.OnNextTrackButtonClickListener;
import org.pet.mediaplayer.listener.OnPlayPauseButtonClickListener;
import org.pet.mediaplayer.listener.OnPreviousTrackButtonClickLitener;
import org.pet.mediaplayer.listener.OnRepeatButtonClickListener;
import org.pet.mediaplayer.listener.OnShuffleButtonClickListener;
import org.pet.mediaplayer.listener.OnStopButtonClickListener;
import org.pet.mediaplayer.listener.OnVisualizationButtonClickListener;
import org.pet.mediaplayer.mp3.MP3Player;
import org.pet.mediaplayer.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class PlayerActivity extends Activity {
	
	private static final String TAG = PlayerActivity.class.getName();
	
	private static Player player;
	
	private SeekBar songSeekBar;
	
	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	protected void onResume() {
		super.onResume();
		if(player != null && PlayerState.PLAY.equals(player.getState())) {
			player.onPlayerResume(songSeekBar);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		player.onPlayerDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Creating application...");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);
		
		// TODO : Change below chunk to read file from media server. Hardcoded just for POC!
        List<AudioFile> audioFiles = new ArrayList<AudioFile>();
		File sdCard= Environment.getExternalStorageDirectory();
		File byob = new File(sdCard, "/Music/Mezmerize/BYOB.mp3");
		File cigaro = new File(sdCard, "/Music/Mezmerize/Cigaro.mp3");
		File question = new File(sdCard, "/Music/Mezmerize/Question.mp3");
		File revenga = new File(sdCard, "/Music/Mezmerize/Revenga.mp3");
		audioFiles.add(new AudioFile(byob));
		audioFiles.add(new AudioFile(cigaro));
		audioFiles.add(new AudioFile(question));
		audioFiles.add(new AudioFile(revenga));
		
		// Getting file list done.
		
		TextView songTitle = (TextView) findViewById(R.id.song_title);
		TextView artistName = (TextView) findViewById(R.id.artist_name);
		TextView albumName = (TextView) findViewById(R.id.album_name);
		TextView endTime = (TextView) findViewById(R.id.end_time);
		TextView shuffleLabel = (TextView) findViewById(R.id.shuffle_label);
		TextView repeatLabel = (TextView) findViewById(R.id.repeat_label);
		final TextView startTime = (TextView) findViewById(R.id.start_time);
		songSeekBar = (SeekBar) findViewById(R.id.song_seekbar);
		
		ImageButton stopButton = (ImageButton) findViewById(R.id.stop_button);
		ImageButton playPauseButton = (ImageButton) findViewById(R.id.pause_button);
		ImageButton muteButton = (ImageButton) findViewById(R.id.mute_button);
		ImageButton shuffleButton = (ImageButton) findViewById(R.id.shuffle_button);
		ImageButton repeatButton = (ImageButton) findViewById(R.id.repeat_button);
		ImageButton previousButton = (ImageButton) findViewById(R.id.previous_song_button);
		ImageButton nextButton = (ImageButton) findViewById(R.id.next_song_button);
		ImageButton equalizerButton = (ImageButton) findViewById(R.id.equalizer_button);
		ImageButton visualizationButton = (ImageButton) findViewById(R.id.visualization_button);
		ImageButton libraryButton = (ImageButton) findViewById(R.id.library_button);
		
		final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.fragment_middle);
		View visualizationView = View.inflate(this, R.layout.visualization_view, null);
		viewGroup.removeAllViews();
		viewGroup.addView(visualizationView);
		
		// TODO : For now, test using mp3, next check file type, then use appropriate player to play the audio.
		InfoPanelView infoPanel = new InfoPanelView(albumName, songTitle, artistName, endTime);
		if(player == null) {
			player = new MP3Player(getApplicationContext(), audioFiles, songSeekBar, infoPanel, playPauseButton);
			player.setState(PlayerState.STOP);
		} 
		player.resetSeekBar(songSeekBar);
		player.resetInfoPanel(infoPanel);
		player.resetPlayButton(playPauseButton);
		
		OnSeekBarChangeListener seekBarChangeListener = new OnAudioSeekBarChangeListener(startTime, player, getApplicationContext());
		songSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		
		// Setup all button
		OnClickListener onShuffleButtonClickListener = new OnShuffleButtonClickListener(player, getApplicationContext(), shuffleLabel);
		OnClickListener onRepeatButtonClickListener = new OnRepeatButtonClickListener(player, getApplicationContext(), repeatLabel);
		OnClickListener onMuteButtonClickListener = new OnMuteButtonClickListener(player, getApplicationContext(), muteButton);
		OnClickListener onStopButtonClickListener = new OnStopButtonClickListener(player, getApplicationContext(), playPauseButton);
		OnClickListener onPlayPauseButtonClickListener = new OnPlayPauseButtonClickListener(player, getApplicationContext(), playPauseButton);
		OnClickListener onNextButtonClickListener = new OnNextTrackButtonClickListener(player, getApplicationContext());
		OnClickListener onPreviousButtonClickListener = new OnPreviousTrackButtonClickLitener(player, getApplicationContext());
		OnClickListener onEqualizerButtonClickListener = new OnEqualizerButtonClickListener(player, getApplicationContext(), viewGroup);
		OnClickListener onVisualizationButtonClickListener = new OnVisualizationButtonClickListener(player, getApplicationContext(), viewGroup);
		OnClickListener onLibraryButtonClickListener = new OnLibraryButtonClickListener(player, getApplicationContext(), viewGroup);
		
		shuffleButton.setOnClickListener(onShuffleButtonClickListener);
		repeatButton.setOnClickListener(onRepeatButtonClickListener);
		muteButton.setOnClickListener(onMuteButtonClickListener);
		playPauseButton.setOnClickListener(onPlayPauseButtonClickListener);
		stopButton.setOnClickListener(onStopButtonClickListener);
		nextButton.setOnClickListener(onNextButtonClickListener);
		previousButton.setOnClickListener(onPreviousButtonClickListener);
		equalizerButton.setOnClickListener(onEqualizerButtonClickListener);
		visualizationButton.setOnClickListener(onVisualizationButtonClickListener);
		libraryButton.setOnClickListener(onLibraryButtonClickListener);
		
		visualizationButton.performClick();
	}
	
}
