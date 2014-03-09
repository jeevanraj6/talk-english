package com.fx.anyone.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.anyone.R;
import com.fx.anyone.adapter.MainMenuAdapter;
import com.fx.anyone.model.MenuItem;
import com.fx.anyone.utils.Constant;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

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

	private List<MenuItem> initMenus() {
		this.mainItems = new ArrayList();
		MenuItem item = new MenuItem();
		item.setName(Constant.SEVEN_RULES);
		item.setIllustrationId(R.drawable.rules);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.SPEAKING);
		item.setIllustrationId(R.drawable.success);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.SHOW);
		item.setIllustrationId(R.drawable.show);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.ENGINE);
		item.setIllustrationId(R.drawable.engine);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);
		
		item = new MenuItem();
		item.setName(Constant.PRESENTATION);
		item.setIllustrationId(R.drawable.presentation);
		item.setClassName(MainTalkActivity.class.getName());
		this.mainItems.add(item);

		item = new MenuItem();
		item.setName(Constant.STEVEFORD);
		item.setIllustrationId(R.drawable.steveford);
		item.setClassName(Constant.STEVEFORD_APP);
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
			if (localMenuItem.getName().equals(Constant.STEVEFORD)){
				openOtherApp(Constant.STEVEFORD_APP);
			}else{
				Intent localIntent = new Intent();
				localIntent.setClassName(this, localMenuItem.getClassName());
				localIntent.putExtra(Constant.CATEGORY, localMenuItem.getName());
				startActivity(localIntent);
			}
		}else{
			return;
		}
		
		
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

}
