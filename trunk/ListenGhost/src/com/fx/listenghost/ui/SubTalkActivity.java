package com.fx.listenghost.ui;

import java.util.List;

import com.fx.listenghost.R;
import com.fx.listenghost.adapter.MainTalkAdapter;
import com.fx.listenghost.model.TalkModel;
import com.fx.listenghost.utils.Utils;
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

public class SubTalkActivity extends Activity implements OnItemClickListener{

	private AdView adView;
	private ListView mListview;
	private MainTalkAdapter mMainTalkAdapter;
	private List<TalkModel> models;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_maintalk);
		String str1 = getIntent().getExtras().getString("SUBCATEGORY");
		setTitle(str1);
		this.models = Utils.setSubModel(this, str1);
		this.mListview = ((ListView) findViewById(R.id.listView1));
		this.mListview.setAdapter(this.mMainTalkAdapter);
		this.mListview.setOnItemClickListener(this);
		this.mMainTalkAdapter = new MainTalkAdapter(this, this.models);
		this.mListview.setAdapter(this.mMainTalkAdapter);
		this.adView = ((AdView) findViewById(R.id.maintalk_ad));
		this.adView.loadAd(new AdRequest());
	}

	public void onDestroy() {
		if (this.adView != null)
			this.adView.destroy();
		super.onDestroy();
	}

	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		TalkModel localTalkModel = (TalkModel) this.models.get(paramInt);
		startActivity(new Intent("android.intent.action.VIEW",
				Uri.parse("vnd.youtube:" + localTalkModel.getLinks())));
	}
}
