package com.fx.adapter;

import java.util.List;

import com.fx.english.R;
import com.fx.model.TalkModel;
import com.fx.utils.Constant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainTalkAdapter extends BaseAdapter {

	private String cate;
	private Context context;
	private List<TalkModel> menuItems;

	public MainTalkAdapter(Context paramContext, List<TalkModel> paramList,
			String paramString) {
		this.menuItems = paramList;
		this.context = paramContext;
		this.cate = paramString;
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

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		TalkModel localTalkModel = (TalkModel) this.menuItems.get(paramInt);
		Viewholder localViewholder;
		if (paramView == null) {
			paramView = LayoutInflater.from(this.context).inflate(2130903044,
					null);
			localViewholder = new Viewholder();
			localViewholder.avatar = ((ImageView) paramView
					.findViewById(R.id.avatar));
			localViewholder.title = ((TextView) paramView
					.findViewById(R.id.title));
			paramView.setTag(localViewholder);
		} else {
			localViewholder = (Viewholder) paramView.getTag();
		}

		localViewholder.title.setText(localTalkModel.getTitle());
		if (cate.equals(Constant.LETSTALK)) {
			localViewholder.avatar.setImageResource(R.drawable.lettalk);
		} else if (this.cate.equals(Constant.EFFORTLESS)) {
			localViewholder.avatar.setImageResource(R.drawable.effortless);
		} else if (this.cate.equals(Constant.CONVERSATION)) {
			localViewholder.avatar
					.setImageResource(R.drawable.conversation);
		} else if (cate.equals(Constant.EXTRA)) {
			localViewholder.avatar.setImageResource(R.drawable.extra);
		} else if (cate.equals(Constant.FASTENGLISH)) {
			localViewholder.avatar.setImageResource(R.drawable.today);
		} else if (cate.equals(Constant.BUSSINESS)) {
			localViewholder.avatar.setImageResource(R.drawable.bussenglish);
		} else if (cate.equals(Constant.BEST_ENGLISH)) {
			localViewholder.avatar.setImageResource(R.drawable.bestenglish);
		}else if (cate.equals(Constant.DUNCAN)) {
			localViewholder.avatar.setImageResource(R.drawable.duncan);
		}else if (cate.equals(Constant.SPEAKING)) {
			localViewholder.avatar.setImageResource(R.drawable.speaking);
		}

		return paramView;
	}

	class Viewholder {
		public ImageView avatar;
		public TextView title;

		Viewholder() {
		}
	}
}
