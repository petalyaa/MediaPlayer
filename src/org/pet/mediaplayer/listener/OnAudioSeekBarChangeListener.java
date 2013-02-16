package org.pet.mediaplayer.listener;

import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.exception.PlayerException;
import org.pet.mediaplayer.util.TimeUtil;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

public class OnAudioSeekBarChangeListener extends SeekbarChangeLitener {
	
	private TextView startTime;
	
	public OnAudioSeekBarChangeListener(TextView startTime, Player player, Context context) {
		super(player, context);
		this.startTime = startTime;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		try {
			if(fromUser) {
				player.seek(progress);
			}
			String s = TimeUtil.secondsToString(progress/1000);
			startTime.setText(s);
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

}
