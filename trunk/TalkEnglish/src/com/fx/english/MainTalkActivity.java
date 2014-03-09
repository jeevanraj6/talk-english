package com.fx.english;

import java.util.ArrayList;
import java.util.List;

import com.fx.adapter.MainTalkAdapter;
import com.fx.model.TalkModel;
import com.fx.utils.Constant;
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

public class MainTalkActivity extends Activity implements OnItemClickListener{
	private AdView adView;
	private String category;
	private ListView mListview;
	private MainTalkAdapter mMainTalkAdapter;
	private List<TalkModel> mainItems;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(2130903043);
		this.category = getIntent().getExtras().getString(Constant.CATEGORY);
		this.mListview = ((ListView) findViewById(R.id.listView1));
		this.mListview.setAdapter(this.mMainTalkAdapter);
		this.mListview.setOnItemClickListener(this);
		this.mMainTalkAdapter = new MainTalkAdapter(this,
				initMenus(this.category), this.category);
		this.mListview.setAdapter(this.mMainTalkAdapter);
		setTitle(this.category);
		this.adView = ((AdView) findViewById(R.id.maintalk_ad));
		this.adView.loadAd(new AdRequest());
	}

	
	private List<TalkModel> initMenus(String paramString) {
		String[] arraylinks=null;
		String[] arraytitles=null;
		mainItems = new ArrayList<TalkModel>();
		if (paramString.equals(Constant.LETSTALK)) {
			arraylinks = getResources().getStringArray(R.array.talk_links);
			arraytitles = getResources().getStringArray(R.array.talk_titles);
		}else if(paramString.equals(Constant.EFFORTLESS)){
			arraylinks = getResources().getStringArray(R.array.effort_links);
			arraytitles = getResources().getStringArray(R.array.effort_titles);
		}else if(paramString.equals(Constant.CONVERSATION)){
			arraylinks = getResources().getStringArray(R.array.conv_links);
			arraytitles = getResources().getStringArray(R.array.conv_titles);
		}else if(paramString.equals(Constant.EXTRA)){
			arraylinks = getResources().getStringArray(R.array.extra_links);
			arraytitles = getResources().getStringArray(R.array.extra_titles);
		}else if(paramString.equals(Constant.FASTENGLISH)){
			arraylinks = getResources().getStringArray(R.array.fastenglish_links);
			arraytitles = getResources().getStringArray(R.array.fastenglish_titles);
		}else if(paramString.equals(Constant.BUSSINESS)){
			arraylinks = getResources().getStringArray(R.array.buss_links);
			arraytitles = getResources().getStringArray(R.array.buss_title);
		}else if(paramString.equals(Constant.BEST_ENGLISH)){
			arraylinks = getResources().getStringArray(R.array.bestenglish_link);
			arraytitles = getResources().getStringArray(R.array.bestenglish_title);
		}else if(paramString.equals(Constant.LESSON)){
			arraylinks = getResources().getStringArray(R.array.improve_links);
			arraytitles = getResources().getStringArray(R.array.improve_titles);
		}else if(paramString.equals(Constant.DUNCAN)){
			arraylinks = getResources().getStringArray(R.array.duncan_links);
			arraytitles = getResources().getStringArray(R.array.duncan_titles);
		}else if(paramString.equals(Constant.SPEAKING)){
			arraylinks = getResources().getStringArray(R.array.speaking_link);
			arraytitles = getResources().getStringArray(R.array.speaking_title);
		}
		if (arraylinks != null && arraytitles != null) {
			int i = Math.min(arraylinks.length, arraytitles.length);
			for (int j = 0; j < i; j++) {
				TalkModel localTalkModel = new TalkModel();
				localTalkModel.setLinks(arraylinks[j]);
				localTalkModel.setTitle(arraytitles[j]);
				this.mainItems.add(localTalkModel);
			}
		}
		return mainItems;
	}

	
	public void onDestroy() {
		if (this.adView != null)
			this.adView.destroy();
		super.onDestroy();
	}

	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		TalkModel localTalkModel = (TalkModel) this.mainItems.get(paramInt);
		if (localTalkModel.getLinks().equals("null")) {
			Intent localIntent = new Intent(this, SubTalkActivity.class);
			localIntent.putExtra("POSITION", paramInt);
			localIntent.putExtra("SUBCATEGORY", localTalkModel.getTitle());
			localIntent.putExtra("CATEGORY", this.category);
			startActivity(localIntent);
		}else{
			startActivity(new Intent("android.intent.action.VIEW",
				Uri.parse("vnd.youtube:" + localTalkModel.getLinks())));
		}
	}
}
