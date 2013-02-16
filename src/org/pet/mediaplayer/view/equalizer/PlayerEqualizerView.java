package org.pet.mediaplayer.view.equalizer;

import org.pet.android.widget.VerticalSeekBar;
import org.pet.mediaplayer.Player;
import org.pet.mediaplayer.PlayerFragmentView;
import org.pet.mediaplayer.R;
import org.pet.mediaplayer.view.equalizer.listener.OnEqualizerSeekBarChangeListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PlayerEqualizerView extends PlayerFragmentView {

	public PlayerEqualizerView(Player player, Context context, ViewGroup viewGroup) {
		super(context, viewGroup);
	}

	@Override
	public View buildView() {
		View view = View.inflate(context, R.layout.equalizer_view, null);
		viewGroup.removeAllViews();
		
		VerticalSeekBar masterGainSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_master_gain_slider);
		VerticalSeekBar eq31HzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_31_hz);
		VerticalSeekBar eq62HzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_62_hz);
		VerticalSeekBar eq125HzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_125_hz);
		VerticalSeekBar eq250HzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_250_hz);
		VerticalSeekBar eq500HzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_500_hz);
		VerticalSeekBar eq1KHzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_1_khz);
		VerticalSeekBar eq2KHzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_2_khz);
		VerticalSeekBar eq4KHzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_4_khz);
		VerticalSeekBar eq8KHzlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_8_khz);
		VerticalSeekBar eq16KHzSlider = (VerticalSeekBar) view.findViewById(R.id.equalizer_16_khz);
		
		OnSeekBarChangeListener masterGainChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.MASTER_GAIN);
		OnSeekBarChangeListener eq31HzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_31_HZ);
		OnSeekBarChangeListener eq62HzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_62_HZ);
		OnSeekBarChangeListener eq125HzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_125_HZ);
		OnSeekBarChangeListener eq250HzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_250_HZ);
		OnSeekBarChangeListener eq500HzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_500_HZ);
		OnSeekBarChangeListener eq1KHzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_1_KHZ);
		OnSeekBarChangeListener eq2KHzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_2_KHZ);
		OnSeekBarChangeListener eq4KHzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_4_KHZ);
		OnSeekBarChangeListener eq8KHzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_8_KHZ);
		OnSeekBarChangeListener eq16KHzChangeListener = new OnEqualizerSeekBarChangeListener(context, EqualizerType.EQ_16_KHZ);
		
		masterGainSlider.setOnSeekBarChangeListener(masterGainChangeListener);
		eq31HzSlider.setOnSeekBarChangeListener(eq31HzChangeListener);
		eq62HzSlider.setOnSeekBarChangeListener(eq62HzChangeListener);
		eq125HzSlider.setOnSeekBarChangeListener(eq125HzChangeListener);
		eq250HzSlider.setOnSeekBarChangeListener(eq250HzChangeListener);
		eq500HzSlider.setOnSeekBarChangeListener(eq500HzChangeListener);
		eq1KHzSlider.setOnSeekBarChangeListener(eq1KHzChangeListener);
		eq2KHzSlider.setOnSeekBarChangeListener(eq2KHzChangeListener);
		eq4KHzSlider.setOnSeekBarChangeListener(eq4KHzChangeListener);
		eq8KHzlider.setOnSeekBarChangeListener(eq8KHzChangeListener);
		eq16KHzSlider.setOnSeekBarChangeListener(eq16KHzChangeListener);
		
		viewGroup.addView(view);
		return view;
	}

}

