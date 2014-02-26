package com.fx.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.fx.english.R;
import com.fx.model.TalkModel;

public class Utils {

	public static List<TalkModel> setSubModel(Activity paramActivity,
			int paramInt) {
		ArrayList localArrayList = new ArrayList();
		int i = paramInt + 1;
		String[] arrayOfString1 = null;
		String[] arrayOfString2 = null;
		switch (i) {
		case 1:
			arrayOfString2 = paramActivity.getResources().getStringArray(
					R.array.talk_sub_title_1);
			arrayOfString1 = paramActivity.getResources().getStringArray(
					R.array.talk_sub_link1);
			break;
		case 2:
			arrayOfString2 = paramActivity.getResources().getStringArray(
					R.array.talk_sub_title_2);
			arrayOfString1 = paramActivity.getResources().getStringArray(
					R.array.talk_sub_link2);
		}
		
		int j=Math.min(arrayOfString1.length, arrayOfString2.length);
		
		for (int k = 0; k <j; k++) {
			TalkModel localTalkModel = new TalkModel();
			localTalkModel.setLinks(arrayOfString1[k]);
			localTalkModel.setTitle(arrayOfString2[k]);
			localArrayList.add(localTalkModel);
		}
		
		return localArrayList;
	}
}
