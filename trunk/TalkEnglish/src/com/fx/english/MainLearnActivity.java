package com.fx.english;

import java.util.ArrayList;
import java.util.List;

import com.fx.adapter.MainMenuAdapter;
import com.fx.model.MenuItem;
import com.fx.utils.Constant;
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
		item.setName(Constant.BBCNEWS);
		item.setIllustrationId(R.drawable.bbcmain);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.PRONOUN);
		item.setIllustrationId(R.drawable.pronunciation);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.EXPRESS);
		item.setIllustrationId(R.drawable.express);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.FLATMATE);
		item.setIllustrationId(R.drawable.flatmate);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.RINKU);
		item.setIllustrationId(R.drawable.rinku);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.IDIOM);
		item.setIllustrationId(R.drawable.food);
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
