package com.fx.effortless.ui;


import com.fx.effortless.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends Activity {

	 protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.activity_loading);
	    new Thread(new WelcomeThread(this)).start();
	  }

	  class WelcomeThread
	    implements Runnable
	  {
	    private int wait = 0;
	    private int welcomeScreenDisplay = 2000;

	    public WelcomeThread(Context arg2)
	    {
	    }

	    public void run()
	    {
	      try
	      {
	        while (true)
	        {
	          int i = this.wait;
	          int j = this.welcomeScreenDisplay;
	          if (i >= j)
	            return;
	          Thread.sleep(1000L);
	          this.wait = (1000 + this.wait);
	        }
	      }
	      catch (Exception localException)
	      {
	        System.out.println("EXc=" + localException);
	        return;
	      }
	      finally
	      {
	        LoadingActivity.this.startActivity(new Intent(LoadingActivity.this, MainActivity.class));
	        LoadingActivity.this.finish();
	      }
	      
	    }
	  }
}

