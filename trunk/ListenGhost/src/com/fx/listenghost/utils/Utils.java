package com.fx.listenghost.utils;

import java.util.ArrayList;
import java.util.List;

import com.fx.listenghost.R;
import com.fx.listenghost.model.TalkModel;

import android.app.Activity;



public class Utils {
	
	private static final String bongnguoi = "Bóng người dưới trăng";
	private static final String coiam = "Cõi âm";
	private static final String bongma = "Bóng ma bên cửa";
	private static final String tiengqua = "Tiếng quạ réo vong hồn";
	private static final String ngoimo = "Ngôi mộ mới đắp";
	private static final String demtrong = "Đêm trong căn nhà hoang";
	private static final String baidat = "Bãi đất hoang sau nhà";
	
	public static List<TalkModel> setSubModel(Activity paramActivity,
			String title) {
		ArrayList localArrayList = new ArrayList();
		String[] arraylink = null;
		String[] arraytitle = null;
		if(title.equals(bongnguoi)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.bongnguoi_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.bongnguoi_title);
		}else if(title.equals(bongma)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.bongma_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.bongma_tile);
		}else if(title.equals(coiam)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.coiam_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.coiam_title);
		}else if(title.equals(tiengqua)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.tiengqua_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.tiengqua_title);
		}else if(title.equals(ngoimo)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.ngoimo_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.ngoimo_title);
		}else if(title.equals(demtrong)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.demtrong_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.demtrong_tile);
		}else if(title.equals(baidat)){
			arraylink = paramActivity.getResources().getStringArray(
					R.array.baidat_link);
			arraytitle = paramActivity.getResources().getStringArray(
					R.array.baidat_title);
		}
		
		int j=Math.min(arraylink.length, arraytitle.length);
		
		for (int k = 0; k <j; k++) {
			TalkModel localTalkModel = new TalkModel();
			localTalkModel.setLinks(arraylink[k]);
			localTalkModel.setTitle(arraytitle[k]);
			localArrayList.add(localTalkModel);
		}
		
		return localArrayList;
	}
}
