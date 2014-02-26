package com.fx.english;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.fx.adapter.MainMenuAdapter;
import com.fx.model.MenuItem;
import com.fx.utils.Constant;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends Activity implements OnItemClickListener {

	private AdView adView;
	private List<MenuItem> mainItems;
	private MainMenuAdapter mainMenuAdapter;
	private GridView menuGridview;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main);
		this.mainMenuAdapter = new MainMenuAdapter(this, initMenus());
		this.menuGridview = ((GridView) findViewById(R.id.menu_grid));
		this.menuGridview.setSelector(new ColorDrawable(0));
		this.menuGridview.setOnItemClickListener(this);
		this.menuGridview.setAdapter(this.mainMenuAdapter);
		this.adView = ((AdView) findViewById(R.id.main_ad));
		this.adView.loadAd(new AdRequest());
	}

	private boolean appInstalledOrNot(String paramString) {
		PackageManager localPackageManager = getPackageManager();
		try {
			localPackageManager.getPackageInfo(paramString, 1);
			return true;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
		}
		return false;
	}

	private List<MenuItem> initMenus() {
		this.mainItems = new ArrayList();
		MenuItem item = new MenuItem();
		item.setName(Constant.LETSTALK);
		item.setIllustrationId(R.drawable.lettalk);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.BBC_ENGLISH);
		item.setIllustrationId(R.drawable.bbc);
		item.setClassName(MainLearnActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.EFFORTLESS);
		item.setIllustrationId(R.drawable.effortless);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.CONVERSATION);
		item.setIllustrationId(R.drawable.conversation);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.EXTRA);
		item.setIllustrationId(R.drawable.extra);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.BEST_ENGLISH);
		item.setIllustrationId(R.drawable.bestenglish);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.BUSSINESS);
		item.setIllustrationId(R.drawable.bussenglish);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.DUNCAN);
		item.setIllustrationId(R.drawable.duncan);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.CNN);
		item.setIllustrationId(R.drawable.cnn);
		item.setClassName(Constant.CNN_APP);
		this.mainItems.add(item);
		return this.mainItems;
	}

	private void openOtherApp(String paramString) {
		if (appInstalledOrNot(paramString)) {
			startActivity(getPackageManager().getLaunchIntentForPackage(
					paramString));
			return;
		}
		try {
			startActivity(new Intent("android.intent.action.VIEW",
					Uri.parse("market://details?id=" + paramString)));
			return;
		} catch (ActivityNotFoundException localActivityNotFoundException) {
			startActivity(new Intent("android.intent.action.VIEW",
					Uri.parse("http://play.google.com/store/apps/details?id="
							+ paramString)));
		}
	}

	public void onDestroy() {
		if (this.adView != null)
			this.adView.destroy();
		super.onDestroy();
	}

	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong) {
		MenuItem localMenuItem;
		if (paramAdapterView == this.menuGridview) {
			localMenuItem = (MenuItem) this.mainItems.get(paramInt);
			if (localMenuItem.getName().equals(Constant.CNN)){
				openOtherApp(localMenuItem.getClassName());
			}else{
				Intent localIntent = new Intent();
				localIntent.setClassName(this, localMenuItem.getClassName());
				localIntent.putExtra(Constant.CATEGORY, localMenuItem.getName());
				startActivity(localIntent);
			}
		} else {
			return;
		}
		
	}
}
