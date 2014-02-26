package com.fx.listenghost.ui;

import com.fx.listenghost.R;
import com.fx.listenghost.engine.PlayerEngine;
import com.fx.listenghost.engine.PlayerEngineImpl;
import com.fx.listenghost.engine.PlayerEngineListener;
import com.fx.listenghost.engine.PlayerEngineListenerStub;
import com.fx.listenghost.model.TalkModel;
import com.fx.listenghost.utils.Constant;
import com.google.ads.AdRequest;
import com.google.ads.AdView;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ListenDetailActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	private AdView adView;
	private AudioManager audioManager;
	private Bitmap btnPause;
	private Bitmap btnPlay;
	private ImageButton controlBtn;
	private TextView durationView;
	private TalkModel mdata;
	private PlayerEngine playerEngine;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_detail_listen);
		this.mdata = new TalkModel();
		Bundle localBundle = getIntent().getExtras();
		this.mdata.setTitle(localBundle.getString(Constant.TITLE));
		this.mdata.setLinks(localBundle.getString(Constant.LINK));
		TextView localTextView = (TextView) findViewById(R.id.txtTitle);
		this.txtloading = ((TextView) findViewById(R.id.txtloading));
		this.txtloading.setVisibility(4);
		localTextView.setText(this.mdata.getTitle());
		setTitle(this.mdata.getTitle());
		this.adView = ((AdView) findViewById(R.id.detail_ad));
		this.adView.loadAd(new AdRequest());
		initPlayer();
	}

	private PlayerEngineListener playerListener = new PlayerEngineListenerStub() {
		public void onTrackBuffering(int paramInt) {
			super.onTrackBuffering(paramInt);
			ListenDetailActivity.this.txtloading.setVisibility(4);
		}

		public void onTrackPause() {
			super.onTrackPause();
			ListenDetailActivity.this.controlBtn
					.setImageBitmap(ListenDetailActivity.this.btnPlay);
		}

		public void onTrackProgress(int paramInt) {
			super.onTrackProgress(paramInt);
			ListenDetailActivity.this.progressSongBar.setProgress(paramInt);
			ListenDetailActivity.this.setDurationView(paramInt);
		}

		public boolean onTrackStart() {
			ListenDetailActivity.this.progressSongBar
					.setMax(ListenDetailActivity.this.playerEngine
							.getDuration());
			return super.onTrackStart();
		}

		public void onTrackStop() {
			super.onTrackStop();
			ListenDetailActivity.this.controlBtn
					.setImageBitmap(ListenDetailActivity.this.btnPlay);
		}

		public void onTrackStreamError() {
			super.onTrackStreamError();
			ListenDetailActivity.this.txtloading.setVisibility(4);
			AlertDialog.Builder localBuilder = new AlertDialog.Builder(
					ListenDetailActivity.this);
			localBuilder
					.setMessage("Not connect internet, let's check it")
					.setTitle("Alert")
					.setCancelable(false)
					.setNegativeButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
									paramDialogInterface.cancel();
								}
							});
			localBuilder.create().show();
		}
	};
	
	private SeekBar progressSongBar;
	private StringBuilder stringBuilder = new StringBuilder();
	private TextView txtloading;
	private SeekBar volumnBar;

	public static String formatSongTime(int time) {

		StringBuffer timeStr = new StringBuffer();
		int seconds = time;
		int minutes = seconds / 60;
		seconds = seconds % 60;
		if (minutes < 10) {

			timeStr.append('0').append(Integer.toString(minutes)).append(":");
		} else {

			timeStr.append(Integer.toString(minutes)).append(":");
		}

		if (seconds < 10) {

			timeStr.append('0').append(Integer.toString(seconds));
		} else {

			timeStr.append(Integer.toString(seconds));
		}
		return timeStr.toString();
	}

	private void initPlayer() {
		this.btnPlay = BitmapFactory.decodeResource(getResources(), R.drawable.nutplay_muitenchay);
		this.btnPause = BitmapFactory
				.decodeResource(getResources(), R.drawable.nutplay_stop);
		this.controlBtn = ((ImageButton) findViewById(R.id.md_control_btn));
		this.progressSongBar = ((SeekBar) findViewById(R.id.md_seek_bar));
		this.volumnBar = ((SeekBar) findViewById(R.id.md_volumn));
		this.progressSongBar.setOnSeekBarChangeListener(this);
		this.volumnBar.setOnSeekBarChangeListener(this);
		setVolumnManager();
		this.controlBtn.setImageBitmap(this.btnPlay);
		this.controlBtn.setOnClickListener(this);
		this.durationView = ((TextView) findViewById(R.id.md_duation));
		startPlayerEngine();
	}

	private void setDurationView(int paramInt) {
		this.stringBuilder.setLength(0);
		this.stringBuilder.append(formatSongTime(paramInt));
		this.stringBuilder.append('|');
		this.stringBuilder.append(formatSongTime(this.playerEngine
				.getDuration()));
		this.durationView.setText(this.stringBuilder);
	}

	private void setProgressSongFromUser(int paramInt) {
		int i = this.playerEngine.getCurrentProgress() / 1000;
		this.progressSongBar.setProgress(paramInt);
		setDurationView(paramInt);
		if (i > paramInt) {
			this.playerEngine.rewind(paramInt * 1000);
			return;
		}
		this.playerEngine.forward(paramInt * 1000);
	}

	private void setStateControlBtn() {
		if (this.playerEngine.isPlaying()) {
			this.controlBtn.setImageBitmap(this.btnPlay);
			this.playerEngine.pause();
			this.txtloading.setVisibility(4);
			return;
		}
		this.controlBtn.setImageBitmap(this.btnPause);
		if (this.playerEngine.isCompleteTrack()) {
			this.progressSongBar.setProgress(0);
			this.durationView.setText("--:--|--:--");
		}
		this.txtloading.setVisibility(0);
		this.playerEngine.play();
	}

	private void setVolumnFromUser(int paramInt) {
		this.audioManager.setStreamVolume(3, paramInt, 0);
	}

	private void setVolumnManager() {
		this.audioManager = ((AudioManager) getSystemService("audio"));
		this.volumnBar.setMax(this.audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		this.volumnBar.setProgress(this.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
		this.volumnBar.setOnSeekBarChangeListener(this);
	}

	private void startPlayerEngine() {
		if (this.playerEngine == null) {
			this.playerEngine = new PlayerEngineImpl();
			this.playerEngine.setListener(this.playerListener);
		}
		this.playerEngine.setLink(this.mdata.getLinks());
		setDurationView(this.playerEngine.getDuration());
		setStateControlBtn();
	}

	public void onClick(View paramView) {
		if (paramView.getId() == R.id.md_control_btn)
			setStateControlBtn();
	}

	public void onDestroy() {
		if (this.adView != null){
			this.adView.destroy();
		}
		this.playerEngine.stop();
		super.onDestroy();
	}
	
	protected void onStop() {
		if(playerEngine!=null){
			this.playerEngine.pause();
		}
		super.onStop();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		switch (seekBar.getId()) {
		case R.id.md_seek_bar:

			if (fromUser) {
				Log.d("progress", "" + progress);
				/*
				 * int secondaryPosition = seekBar.getSecondaryProgress(); if
				 * (progress > secondaryPosition) {
				 * seekBar.setProgress(secondaryPosition); } else {
				 */

				setProgressSongFromUser(progress);
				// }
			}
			break;

		case R.id.md_volumn:

			setVolumnFromUser(progress);
			break;
		}
	}

	public void onStartTrackingTouch(SeekBar paramSeekBar) {
	}

	

	public void onStopTrackingTouch(SeekBar paramSeekBar) {
	}

}
