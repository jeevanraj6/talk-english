package com.fx.listenghost.engine;

public abstract interface PlayerEngine {
	public abstract void forward(int paramInt);

	public abstract int getCurrentProgress();

	public abstract int getDuration();

	public abstract boolean isCompleteTrack();

	public abstract boolean isPlaying();

	public abstract void pause();

	public abstract void play();

	public abstract void rewind(int paramInt);

	public abstract void setLink(String paramString);

	public abstract void setListener(
			PlayerEngineListener paramPlayerEngineListener);

	public abstract void stop();
}
