package org.pet.mediaplayer.view.equalizer;

import java.util.ArrayList;
import java.util.List;

import org.pet.android.widget.VerticalSeekBar;
import org.pet.mediaplayer.EqualizerHandler;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.PlayerFragmentView;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.exception.PlayerEqualizerException;
import org.pet.mediaplayer.view.equalizer.listener.OnEqualizerSeekBarChangeListener;

import android.content.Context;
import android.media.audiofx.Equalizer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PlayerEqualizerView extends PlayerFragmentView {
	
	private static final int MAX_EQ = 10;
	
	private Player player;

	public PlayerEqualizerView(Player player, Context context, ViewGroup viewGroup) {
		super(context, viewGroup);
		this.player = player;
	}

	@Override
	public View buildView() {
		View view = View.inflate(context, R.layout.equalizer_view, null);
		viewGroup.removeAllViews();
		
		VerticalSeekBar masterGainSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_master_gain_slider);
		OnSeekBarChangeListener masterGainChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.MASTER_GAIN, player, true);
		masterGainSlider.setOnSeekBarChangeListener(masterGainChangeListener);
		
		short bandNumbers = 0;
		int maxSeekbarValue = 100;
		short max = 100;
		short min = 0;
		EqualizerHandler equalizerHandler;
		Equalizer equalizer = null;
		try {
			equalizerHandler = player.getEqualizer();
			bandNumbers = equalizerHandler.getTotalBandSize();
			equalizer = equalizerHandler.getEqualizerObject();
			if(bandNumbers > MAX_EQ) {
				bandNumbers = MAX_EQ;
			}
			max = equalizerHandler.getMaximumBandGain();
			min = equalizerHandler.getMinimumBandGain();
			maxSeekbarValue = -(min - max);
		} catch (PlayerEqualizerException e) {
			e.printStackTrace();
		}
		
		VerticalSeekBar slider1 = (VerticalSeekBar) view.findViewById(R.id.equalizer_16_khz);
		VerticalSeekBar slider2 = (VerticalSeekBar) view.findViewById(R.id.equalizer_8_khz);
		VerticalSeekBar slider3 = (VerticalSeekBar) view.findViewById(R.id.equalizer_4_khz);
		VerticalSeekBar slider4 = (VerticalSeekBar) view.findViewById(R.id.equalizer_2_khz);
		VerticalSeekBar slider5 = (VerticalSeekBar) view.findViewById(R.id.equalizer_1_khz);
		VerticalSeekBar slider6 = (VerticalSeekBar) view.findViewById(R.id.equalizer_500_hz);
		VerticalSeekBar slider7 = (VerticalSeekBar) view.findViewById(R.id.equalizer_250_hz);
		VerticalSeekBar slider8 = (VerticalSeekBar) view.findViewById(R.id.equalizer_125_hz);
		VerticalSeekBar slider9 = (VerticalSeekBar) view.findViewById(R.id.equalizer_62_hz);
		VerticalSeekBar slider10 = (VerticalSeekBar) view.findViewById(R.id.equalizer_31_hz);
		slider1.setVisibility(View.GONE);
		slider2.setVisibility(View.GONE);
		slider3.setVisibility(View.GONE);
		slider4.setVisibility(View.GONE);
		slider5.setVisibility(View.GONE);
		slider6.setVisibility(View.GONE);
		slider7.setVisibility(View.GONE);
		slider8.setVisibility(View.GONE);
		slider9.setVisibility(View.GONE);
		slider10.setVisibility(View.GONE);
		
		List<VerticalSeekBar> seekBarList = new ArrayList<VerticalSeekBar>();
		seekBarList.add(slider1);
		seekBarList.add(slider2);
		seekBarList.add(slider3);
		seekBarList.add(slider4);
		seekBarList.add(slider5);
		seekBarList.add(slider6);
		seekBarList.add(slider7);
		seekBarList.add(slider8);
		seekBarList.add(slider9);
		seekBarList.add(slider10);
		
		for(int i = 0; i < bandNumbers; i++) {
			
			short band = (short) i;
			
			int[] range = equalizer.getBandFreqRange(band);
			maxSeekbarValue = range[0] - range[1];
			int centerFreq = equalizer.getCenterFreq(band);
			
			VerticalSeekBar bandSeekBar = seekBarList.get(i);
			bandSeekBar.setVisibility(View.VISIBLE);
			bandSeekBar.setMax(maxSeekbarValue);
			bandSeekBar.setProgress(range[1] - centerFreq);
			OnSeekBarChangeListener bandListener = new OnEqualizerSeekBarChangeListener(context, band, player, false);
			bandSeekBar.setOnSeekBarChangeListener(bandListener);
		}
		
		viewGroup.addView(view);
		return view;
	}

}

