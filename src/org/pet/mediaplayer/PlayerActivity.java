package org.pet.mediaplayer;

import java.io.File;

import org.pet.mediaplayer.listener.OnAudioSeekBarChangeListener;
import org.pet.mediaplayer.listener.OnPauseButtonClickListener;
import org.pet.mediaplayer.listener.OnPlayButtonClickListener;
import org.pet.mediaplayer.listener.OnStopButtonClickListener;
import org.pet.mediaplayer.mp3.MP3File;
import org.pet.mediaplayer.mp3.MP3Player;
import org.pet.mediaplayer.util.SystemUiHider;
import org.pet.mediaplayer.util.TimeUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View.OnClickListener;
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_player);
		
		// TODO : Change below chunk to read file from media server. Hardcoded just for POC!
		File sdCard= Environment.getExternalStorageDirectory();
		File mp3File = new File(sdCard, "/Music/Mezmerize/BYOB.mp3");
		// Getting file list done.
		
		TextView songTitle = (TextView) findViewById(R.id.song_title);
		TextView artistName = (TextView) findViewById(R.id.artist_name);
		TextView albumName = (TextView) findViewById(R.id.album_name);
		TextView endTime = (TextView) findViewById(R.id.end_time);
		final TextView startTime = (TextView) findViewById(R.id.start_time);
		SeekBar songProgressBar = (SeekBar) findViewById(R.id.song_seekbar);
		
		ImageButton playButton = (ImageButton) findViewById(R.id.play_button);
		ImageButton stopButton = (ImageButton) findViewById(R.id.stop_button);
		ImageButton pauseButton = (ImageButton) findViewById(R.id.pause_button);
		
		// TODO : For now, test using mp3, next check file type, then use appropriate player to play the audio.
		MP3File mp3 = new MP3File(mp3File);
		Player player = new MP3Player(mp3, songProgressBar);
		
		byte[] albumArt = mp3.getAlbumArt();
		if(albumArt != null) {
			Log.v(TAG, "Album art exist.");
		}
		
		long originalDuration = mp3.getDuration();
		long duration = originalDuration / 1000; // in seconds
		
		String endTimeStr = TimeUtil.secondsToString((int) duration);
		
		albumName.setText(mp3.getAlbumName());
		songTitle.setText(mp3.getSongTitle());
		artistName.setText(mp3.getArtistName());
		endTime.setText(endTimeStr);
		
		OnSeekBarChangeListener seekBarChangeListener = new OnAudioSeekBarChangeListener(startTime, player, getApplicationContext());
		
		songProgressBar.setMax((int) originalDuration);
		songProgressBar.setOnSeekBarChangeListener(seekBarChangeListener);
		
		// Setup all button
		OnClickListener onPlayButtonClickListener = new OnPlayButtonClickListener(player, getApplicationContext());
		OnClickListener onStopButtonClickListener = new OnStopButtonClickListener(player, getApplicationContext());
		OnClickListener onPauseButtonClickListener = new OnPauseButtonClickListener(player, getApplicationContext());
		
		playButton.setOnClickListener(onPlayButtonClickListener);
		stopButton.setOnClickListener(onStopButtonClickListener);
		pauseButton.setOnClickListener(onPauseButtonClickListener);
		
		
		
		
	}

}
