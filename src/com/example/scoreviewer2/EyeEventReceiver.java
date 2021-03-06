package com.example.scoreviewer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class EyeEventReceiver extends BroadcastReceiver {
	private static final String TAG = "EyeEventReceiver";
	
	private static final String WINK = "WINK";
	private static final String DOUBLE_BLINK = "DOUBLE_BLINK";

	/**
	 * An interface for a listener to capture wink and double blinks
	 */
	public static interface EyeEventListener {
		public void onWink();
		public void onDoubleBlink();
	}

	private EyeEventListener mListener;

	public EyeEventReceiver(EyeEventListener listener) {
		mListener = listener;
	}

	public void setEyeEventListener(EyeEventListener listener) {
		mListener = listener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();

		String eyeEvent = extras.getString("gesture");

		Log.d(TAG, eyeEvent + " is detected here!");

		if (mListener != null) {
			if (eyeEvent.equals(WINK)) {
				mListener.onWink();
			} else if (eyeEvent.equals(DOUBLE_BLINK)) {
				mListener.onDoubleBlink();
			} else{
				Log.i(TAG, "Unknown Gesture: \"" + eyeEvent + "\"");
			}
		}
		abortBroadcast();
	}

}
