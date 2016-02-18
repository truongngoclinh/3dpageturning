package com.smb.pager;

import com.smb.utils.Constant;
import com.smb.controller.MainActivity;
import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

@SuppressLint("NewApi")
public class MyPageTransformer implements ViewPager.PageTransformer {

	@Override
	public void transformPage(View view, float position) {
		if (!Constant.ANIMATION_STATE) {
			view.setRotationY(-Constant.ROTATION_DEGREE);
		}
		if (position < -1) {
			view.setAlpha(0f) ;
		} else if (position <= 0) {
			view.setAlpha(1f);
			if (!Constant.ANIMATION_STATE) {
				view.setRotationY(view.getRotationY() + position * 15);
			}
		} else if (position > 0) {
			if (Constant.ANIMATION_STATE) {
				float trX = MainActivity.mScreenWidth * position - position * 40;
				view.setTranslationX(-trX);
			} else {
				float scaleFactor = Constant.SCALE_FACTOR - 0.01f * (position);
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);
				view.setTranslationX(-Constant.PAGE_TRANSLATE_X_AFTER_ROTATING - position * (Constant.PAGE_TRANSLATE_X));
			}
			view.setAlpha(1f);
		}
	}
}
