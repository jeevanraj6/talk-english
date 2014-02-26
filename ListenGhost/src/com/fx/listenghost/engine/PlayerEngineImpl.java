package com.fx.listenghost.engine;

import java.io.IOException;



import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Handler;
import android.util.Log;

public class PlayerEngineImpl implements PlayerEngine {
	public final String PLAYER_ENGINE_TAG = "player engine";
	private String link;
	private InternalMediaPlayer mCurrentPlayer;
	private Handler mHandler = new Handler();
	private PlayerEngineListener mListener;
	private Runnable mUpdateTimerTask = new Runnable() {
		public void run() {
			if ((PlayerEngineImpl.this.mListener != null)
					&& (PlayerEngineImpl.this.mCurrentPlayer != null)) {
				PlayerEngineImpl.this.mListener
						.onTrackProgress(PlayerEngineImpl.this.mCurrentPlayer
								.getCurrentPosition() / 1000);
				PlayerEngineImpl.this.mHandler.postDelayed(this, 1000L);
			}
		}
	};

	private InternalMediaPlayer build(String path) {

		final InternalMediaPlayer player = new InternalMediaPlayer();

		try {

			player.setDataSource(path);
			player.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					Log.d("ON COMPLETE", "true");
					player.isCompleteTrack = true;
				
				}
			});
			player.setOnSeekCompleteListener(new OnSeekCompleteListener() {

				@Override
				public void onSeekComplete(MediaPlayer mp) {
					// TODO Auto-generated method stub
					Log.d("SEEK", "COMPLETE");
					player.isPreparing = false;
					play();

				}
			});
			player.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {

					mListener.onTrackStart();
					player.isPreparing = false;
					play();
				}
			});

			player.setOnErrorListener(new OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {

					if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {

						if (mListener != null) {

							mListener.onTrackStreamError();
						}
						
						stop();
						return true;
					}
					return false;
				}
			});

			player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

				@Override
				public void onBufferingUpdate(MediaPlayer mp, int percent) {

					if (mListener != null) {

						mListener.onTrackBuffering(percent);
					}
				}
			});
			player.prepareAsync();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		/* return null; */
		return player;
	}

	// ERROR //
	private void cleanUp() {
		// Byte code:
		// 0: aload_0
		// 1: getfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 4: ifnull +22 -> 26
		// 7: aload_0
		// 8: getfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 11: invokevirtual 111
		// com/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer:stop
		// ()V
		// 14: aload_0
		// 15: getfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 18: invokevirtual 114
		// com/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer:release
		// ()V
		// 21: aload_0
		// 22: aconst_null
		// 23: putfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 26: return
		// 27: astore_2
		// 28: aload_0
		// 29: getfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 32: invokevirtual 114
		// com/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer:release
		// ()V
		// 35: aload_0
		// 36: aconst_null
		// 37: putfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 40: return
		// 41: astore_1
		// 42: aload_0
		// 43: getfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 46: invokevirtual 114
		// com/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer:release
		// ()V
		// 49: aload_0
		// 50: aconst_null
		// 51: putfield 45
		// com/fx/listenghost/engine/PlayerEngineImpl:mCurrentPlayer
		// Lcom/fx/listenghost/engine/PlayerEngineImpl$InternalMediaPlayer;
		// 54: aload_1
		// 55: athrow
		//
		// Exception table:
		// from to target type
		// 7 14 27 java/lang/IllegalStateException
		// 7 14 41 finally
	}

	public void forward(int paramInt) {
		if (this.mCurrentPlayer != null) {
			this.mCurrentPlayer.pause();
			this.mCurrentPlayer.seekTo(paramInt);
			this.mCurrentPlayer.start();
		}
	}

	public int getCurrentProgress() {
		if ((this.mCurrentPlayer != null) && (!this.mCurrentPlayer.isPreparing))
			return this.mCurrentPlayer.getCurrentPosition();
		return 0;
	}

	public int getDuration() {
		if (this.mCurrentPlayer != null)
			return this.mCurrentPlayer.getDuration() / 1000;
		return 0;
	}

	public boolean isCompleteTrack() {
		return this.mCurrentPlayer == null;
	}

	public boolean isPlaying() {
		if (this.mCurrentPlayer == null)
			return false;
		return this.mCurrentPlayer.isPlaying();
	}

	public void pause() {
		if (this.mCurrentPlayer.isPlaying()) {
			this.mCurrentPlayer.pause();
			if (this.mListener != null)
				this.mListener.onTrackPause();
		}
	}

	public void play() {
		if (this.mCurrentPlayer == null)
			this.mCurrentPlayer = build(this.link);
		if (!this.mCurrentPlayer.isPreparing) {
			this.mCurrentPlayer.start();
			this.mHandler.removeCallbacks(this.mUpdateTimerTask);
			this.mHandler.postDelayed(this.mUpdateTimerTask, 1000L);
		}
	}

	public void rewind(int paramInt) {
		this.mCurrentPlayer.pause();
		this.mCurrentPlayer.seekTo(paramInt);
		this.mCurrentPlayer.start();
	}

	public void setLink(String paramString) {
		this.link = paramString;
	}

	public void setListener(PlayerEngineListener paramPlayerEngineListener) {
		this.mListener = paramPlayerEngineListener;
	}

	public void stop() {
		cleanUp();
		if (mListener != null) {
			mListener.onTrackStop();
		}
	}

	private class InternalMediaPlayer extends MediaPlayer {
		private int indexOfTrack = -1;
		private boolean isCompleteTrack = false;
		private boolean isPreparing = true;

		private InternalMediaPlayer() {
		}
	}
}
