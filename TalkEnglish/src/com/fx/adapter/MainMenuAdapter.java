package com.fx.adapter;

import java.util.List;

import com.fx.english.R;
import com.fx.model.MenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuAdapter extends BaseAdapter {

	private Context mContext;
	private List<MenuItem> menuItems;

	public MainMenuAdapter(Context paramContext, List<MenuItem> paramList) {
		this.menuItems = paramList;
		this.mContext = paramContext;
	}

	public int getCount() {
		if (this.menuItems != null)
			return this.menuItems.size();
		return 0;
	}

	public Object getItem(int paramInt) {
		return null;
	}

	public long getItemId(int paramInt) {
		return 0L;
	}

	@Override
	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		// TODO Auto-generated method stub
		MenuItem localMenuItem = (MenuItem) this.menuItems.get(paramInt);
		Viewholder localViewholder = new Viewholder();
		if (paramView == null) {
			paramView = LayoutInflater.from(this.mContext).inflate(
					R.layout.menu_grid_item, null);
			localViewholder.itemimage = ((ImageView) paramView
					.findViewById(R.id.grid_item_imageview));
			localViewholder.title = ((TextView) paramView
					.findViewById(R.id.grid_item_textview));

			paramView.setTag(localViewholder);
		} else {
			localViewholder = (Viewholder) paramView.getTag();
		}

		localViewholder.itemimage.setImageResource(localMenuItem
				.getIllustrationId());
		localViewholder.title.setText(localMenuItem.getName());
		
		return paramView;

	}

	class Viewholder {
		public ImageView itemimage;
		public TextView title;

		Viewholder() {
		}
	}

}
