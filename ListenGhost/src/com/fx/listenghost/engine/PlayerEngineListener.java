package com.fx.listenghost.engine;

public abstract interface PlayerEngineListener {
	public abstract void onTrackBuffering(int paramInt);

	public abstract void onTrackPause();

	public abstract void onTrackProgress(int paramInt);

	public abstract boolean onTrackStart();

	public abstract void onTrackStop();

	public abstract void onTrackStreamError();
}
