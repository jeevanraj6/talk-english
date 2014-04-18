package com.fx.bbcenglish.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.bbcenglish.R;
import com.fx.bbcenglish.adapter.MainMenuAdapter;
import com.fx.bbcenglish.model.MenuItem;
import com.fx.bbcenglish.utils.Constant;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainLearnActivity extends Activity implements OnItemClickListener {

	private AdView adView;
	private List<MenuItem> mainItems;
	private MainMenuAdapter mainMenuAdapter;
	private GridView menuGridview;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main_learn);
		this.mainMenuAdapter = new MainMenuAdapter(this, initMenus());
		this.menuGridview = ((GridView) findViewById(R.id.menu_grid));
		this.menuGridview.setSelector(new ColorDrawable(0));
		this.menuGridview.setOnItemClickListener(this);
		this.menuGridview.setAdapter(this.mainMenuAdapter);
		this.adView = ((AdView) findViewById(R.id.mainlearn_ad));
		this.adView.loadAd(new AdRequest());
	}

	private List<MenuItem> initMenus() {
		this.mainItems = new ArrayList();
		
		MenuItem item = new MenuItem();
		item.setName(Constant.FOOD);
		item.setIllustrationId(R.drawable.food);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.TRANS);
		item.setIllustrationId(R.drawable.transport);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.COLOR);
		item.setIllustrationId(R.drawable.color);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.SPORT);
		item.setIllustrationId(R.drawable.sport);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.BODY);
		item.setIllustrationId(R.drawable.body);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.ANIMAL);
		item.setIllustrationId(R.drawable.animal);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		return this.mainItems;
	}

	public void onDestroy() {
		if (this.adView != null)
			this.adView.destroy();
		super.onDestroy();
	}

	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		if (paramAdapterView == this.menuGridview) {
			MenuItem localMenuItem = (MenuItem) this.mainItems.get(paramInt);
			Intent localIntent = new Intent();
			localIntent.setClassName(this, localMenuItem.getClassName());
			localIntent.putExtra(Constant.CATEGORY, localMenuItem.getName());
			startActivity(localIntent);
		}
	}
}
