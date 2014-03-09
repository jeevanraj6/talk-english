package com.fx.bbcenglish.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.bbcenglish.R;
import com.fx.bbcenglish.adapter.MainTalkAdapter;
import com.fx.bbcenglish.model.TalkModel;
import com.fx.bbcenglish.utils.Constant;
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

public class MainTalkActivity extends Activity implements OnItemClickListener {

	private AdView adView;
	private String category;
	private ListView mListview;
	private MainTalkAdapter mMainTalkAdapter;
	private List<TalkModel> mainItems;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_maintalk);
		this.category = getIntent().getExtras().getString(Constant.CATEGORY);
		this.mListview = ((ListView) findViewById(R.id.listView1));
		this.mListview.setAdapter(this.mMainTalkAdapter);
		this.mListview.setOnItemClickListener(this);
		this.mMainTalkAdapter = new MainTalkAdapter(this,initMenus(category), category);
		this.mListview.setAdapter(this.mMainTalkAdapter);
		setTitle(this.category);
		this.adView = ((AdView) findViewById(R.id.maintalk_ad));
		this.adView.loadAd(new AdRequest());
	}

	private List<TalkModel> initMenus(String paramString) {
		mainItems = new ArrayList();
		String[] arraytitles = null;
		String[] arraylinks = null;
		if(paramString.equals(Constant.BBCNEWS)){
			arraylinks = getResources().getStringArray(R.array.bbc_news_links);
			arraytitles = getResources().getStringArray(R.array.bbc_news_titles);
		}else if(paramString.equals(Constant.EXPRESS)){
			arraylinks = getResources().getStringArray(R.array.express_links);
			arraytitles = getResources().getStringArray(R.array.express_titles);
		}else if(paramString.equals(Constant.RINKU)){
			arraylinks = getResources().getStringArray(R.array.rinku_links);
			arraytitles = getResources().getStringArray(R.array.rinku_titles);
		}else if(paramString.equals(Constant.FLATMATE)){
			arraylinks = getResources().getStringArray(R.array.flatmate_links);
			arraytitles = getResources().getStringArray(R.array.flatmate_title);
		}else if(paramString.equals(Constant.IDIOM)){
			arraylinks = getResources().getStringArray(R.array.idioms_links);
			arraytitles = getResources().getStringArray(R.array.idioms_title);
		}else if(paramString.equals(Constant.PRONOUN)){
			arraylinks = getResources().getStringArray(R.array.pronoun_link);
			arraytitles = getResources().getStringArray(R.array.pronoun_title);
		}
		if (arraylinks != null && arraytitles != null) {
			int i = Math.min(arraylinks.length, arraytitles.length);
			for (int j = 0; j < i; j++) {
				TalkModel localTalkModel = new TalkModel();
				localTalkModel.setLinks(arraylinks[j]);
				localTalkModel.setTitle(arraytitles[j]);
				mainItems.add(localTalkModel);
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
		startActivity(new Intent("android.intent.action.VIEW",
				Uri.parse("vnd.youtube:" + localTalkModel.getLinks())));
	}
}
