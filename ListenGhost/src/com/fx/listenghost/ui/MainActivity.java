package com.fx.listenghost.ui;

import java.util.ArrayList;
import java.util.List;

import com.fx.listenghost.R;
import com.fx.listenghost.adapter.MainMenuAdapter;
import com.fx.listenghost.model.MenuItem;
import com.fx.listenghost.utils.Constant;
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

public class MainActivity extends Activity implements OnItemClickListener{
	 private AdView adView;
	  private List<MenuItem> mainItems;
	  private MainMenuAdapter mainMenuAdapter;
	  private GridView menuGridview;
	  
	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.activity_main);
	    this.mainMenuAdapter = new MainMenuAdapter(this, initMenus());
	    this.menuGridview = ((GridView)findViewById(R.id.menu_grid));
	    this.menuGridview.setSelector(new ColorDrawable(0));
	    this.menuGridview.setOnItemClickListener(this);
	    this.menuGridview.setAdapter(this.mainMenuAdapter);
	    this.adView = ((AdView)findViewById(R.id.main_ad));
	    this.adView.loadAd(new AdRequest());
	  }
	  
	  private List<MenuItem> initMenus()
	  {
	    this.mainItems = new ArrayList();
	    MenuItem localMenuItem1 = new MenuItem();
	    localMenuItem1.setName(Constant.NNNGAN);
	    localMenuItem1.setIllustrationId(R.drawable.nnn);
	    localMenuItem1.setClassName(MainListenActivity.class.getName());
	    this.mainItems.add(localMenuItem1);
	    MenuItem localMenuItem2 = new MenuItem();
	    localMenuItem2.setName(Constant.OTHERSTORY);
	    localMenuItem2.setIllustrationId(R.drawable.other);
	    localMenuItem2.setClassName(MainListenActivity.class.getName());
	    this.mainItems.add(localMenuItem2);
	    return this.mainItems;
	  }
	  
	  public void onDestroy()
	  {
	    if (this.adView != null)
	      this.adView.destroy();
	    super.onDestroy();
	  }

	  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
	  {
	    if (paramAdapterView == this.menuGridview)
	    {
	      MenuItem localMenuItem = (MenuItem)this.mainItems.get(paramInt);
	      Intent localIntent = new Intent();
	      localIntent.setClassName(this, localMenuItem.getClassName());
	      localIntent.putExtra(Constant.CATEGORY, localMenuItem.getName());
	      startActivity(localIntent);
	    }
	  }
}
