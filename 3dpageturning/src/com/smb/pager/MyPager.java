package com.smb.pager;

import java.lang.reflect.Field;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

public class MyPager extends ViewPager {

	private MyScroller mScroller = null;
	private Context mContext = null;
	
	public MyPager(Context context) {
		super(context);
		this.mContext = context;
		setScroller();
	}
	
	public MyPager(Context context, AttributeSet atts) {
		super(context, atts);
		this.mContext = context;
		setScroller();
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		super.setCurrentItem(item);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

	private void setScroller() {
		try {
			Class<?> viewpager = ViewPager.class;
			Field scroller = viewpager.getDeclaredField("mScroller");
			scroller.setAccessible(true);
			Field interpolator = viewpager.getDeclaredField("sInterpolator");
			interpolator.setAccessible(true);

			mScroller = new MyScroller(mContext,
					(Interpolator) interpolator.get(null));
			scroller.set(this, mScroller);
		} catch (Exception e) {
		}
	}

	public void setScrollDurationFactor(int scrollFactor) {
		if (mScroller != null) {
			mScroller.setScrollDurationFactor(scrollFactor);
		}
	}
}
