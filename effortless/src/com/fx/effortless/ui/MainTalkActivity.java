package com.fx.effortless.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.effortless.R;
import com.fx.effortless.adapter.MainTalkAdapter;
import com.fx.effortless.model.TalkModel;
import com.fx.effortless.utils.Constant;
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
		String[] arrayTitles = null;
		String[] arrayLinks = null;
		if (paramString.equals(Constant.SEVEN_RULES)) {
			arrayLinks = getResources().getStringArray(R.array.seven_link);
			arrayTitles = getResources().getStringArray(R.array.seven_title);
		} else if (paramString.equals(Constant.ENGINE)) {
			arrayLinks = getResources().getStringArray(R.array.engine_link);
			arrayTitles = getResources().getStringArray(R.array.engine_title);
		}else if (paramString.equals(Constant.SHOW)) {
			arrayLinks = getResources().getStringArray(R.array.show_link);
			arrayTitles = getResources().getStringArray(R.array.show_title);
		}else if (paramString.equals(Constant.PRESENTATION)) {
			arrayLinks = getResources().getStringArray(R.array.presentation_link);
			arrayTitles = getResources().getStringArray(R.array.presentation_title);
		}else if (paramString.equals(Constant.SPEAKING)) {
			arrayLinks = getResources().getStringArray(R.array.success_link);
			arrayTitles = getResources().getStringArray(R.array.success_title);
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
