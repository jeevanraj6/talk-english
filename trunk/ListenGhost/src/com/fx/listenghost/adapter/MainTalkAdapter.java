package com.fx.listenghost.adapter;

import java.util.List;

import com.fx.listenghost.R;
import com.fx.listenghost.model.TalkModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainTalkAdapter extends BaseAdapter{

	 private String cate;
	  private Context context;
	  private List<TalkModel> menuItems;
	  
	  public MainTalkAdapter(Context paramContext, List<TalkModel> paramList, String paramString)
	  {
	    this.menuItems = paramList;
	    this.context = paramContext;
	    this.cate = paramString;
	  }
	  
	  public int getCount()
	  {
	    if (this.menuItems != null)
	      return this.menuItems.size();
	    return 0;
	  }

	  public Object getItem(int paramInt)
	  {
	    return null;
	  }

	  public long getItemId(int paramInt)
	  {
	    return 0L;
	  }

	  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	  {
	    TalkModel localTalkModel = (TalkModel)this.menuItems.get(paramInt);
	    Viewholder localViewholder;
	    if (paramView == null)
	    {
	      paramView = LayoutInflater.from(this.context).inflate(R.layout.activity_talk_item, null);
	      localViewholder = new Viewholder();
	      localViewholder.title = ((TextView)paramView.findViewById(R.id.title));
	      paramView.setTag(localViewholder);
	    }else{
	    	localViewholder = (Viewholder)paramView.getTag();
	    }
	    localViewholder.title.setText(localTalkModel.getTitle());
	    return paramView;
	  }

	  class Viewholder
	  {
	    public TextView title;

	    Viewholder()
	    {
	    }
	  }
}