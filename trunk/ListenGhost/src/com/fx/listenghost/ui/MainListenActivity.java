package com.fx.listenghost.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.listenghost.R;
import com.fx.listenghost.adapter.MainTalkAdapter;
import com.fx.listenghost.model.TalkModel;
import com.fx.listenghost.utils.Constant;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainListenActivity extends Activity implements OnItemClickListener{

	private AdView adView;
	  private String category;
	  private ListView mListview;
	  private MainTalkAdapter mMainTalkAdapter;
	  private List<TalkModel> mainItems=new ArrayList<TalkModel>();
	 
	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.activity_maintalk);
	    this.category = getIntent().getExtras().getString(Constant.CATEGORY);
	    this.mListview = ((ListView)findViewById(R.id.listView1));
	    this.mListview.setAdapter(this.mMainTalkAdapter);
	    this.mListview.setOnItemClickListener(this);
	    this.mMainTalkAdapter = new MainTalkAdapter(this, initMenus(this.category), this.category);
	    this.mListview.setAdapter(this.mMainTalkAdapter);
	    setTitle(this.category);
	    this.adView = ((AdView)findViewById(R.id.maintalk_ad));
	    this.adView.loadAd(new AdRequest());
	  }
	  
	  private List<TalkModel> initMenus(String paramString)
	  {
	    String[] arrayTitles=null;
	    String[] arrayLinks=null;
	    if (paramString.equals(Constant.NNNGAN))
	    {
	    	arrayTitles = getResources().getStringArray(R.array.nnn_title);
	    	arrayLinks = getResources().getStringArray(R.array.nnn_link);
	    }else if(paramString.equals(Constant.OTHERSTORY)){
	    	arrayTitles = getResources().getStringArray(R.array.other_title);
	    	arrayLinks = getResources().getStringArray(R.array.other_link);
	    }
	    if (arrayLinks != null && arrayTitles != null) {
			int i = Math.min(arrayLinks.length, arrayTitles.length);
			for (int j = 0; j < i; j++) {
				TalkModel localTalkModel = new TalkModel();
				localTalkModel.setLinks(arrayLinks[j]);
				localTalkModel.setTitle(arrayTitles[j]);
				mainItems.add(localTalkModel);
			}
		}
	   return mainItems;
	  }

	  public void onDestroy()
	  {
	    if (this.adView != null)
	      this.adView.destroy();
	    super.onDestroy();
	  }

	  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
	  {
	    TalkModel localTalkModel = (TalkModel)this.mainItems.get(paramInt);
	    if (localTalkModel.getLinks().contains("http"))
	    {
	      Intent localIntent = new Intent(this, ListenDetailActivity.class);
	      localIntent.putExtra("TITLE", localTalkModel.getTitle());
	      localIntent.putExtra("LINK", localTalkModel.getLinks());
	      startActivity(localIntent);
	      return;
	    }
	    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + localTalkModel.getLinks())));
	  }
}
