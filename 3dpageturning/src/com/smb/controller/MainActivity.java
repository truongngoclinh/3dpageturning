package com.smb.controller;

import java.util.ArrayList;

import com.smb.R;
import com.smb.pager.MyPageTransformer;
import com.smb.pager.MyPager;
import com.smb.pager.MyPagerAdapter;
import com.smb.utils.Constant;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements OnTouchListener {

	private MyPager mViewPager;
	private FrameLayout mContainer;
	private Button mButtonCloseAnimation;
	private Context mContext;
	private VelocityTracker mVelocityTracker;

	private int mCurrentPage = 0;
	private int mStatusBarHeight = 0;
	private int mChildsCount = -1;
	private int mViewPagerWidth = 0;
	private float mPosX;
	private boolean mAreaMargin = false;
	
	public static int mScreenWidth = 0;
	public static int mScreenHeight = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		mContainer = (FrameLayout) findViewById(R.id.container);
		mButtonCloseAnimation = (Button) findViewById(R.id.btnCloseAnimation);

		mContainer.setOnTouchListener(this);
		mButtonCloseAnimation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exitAnimation();
			}
		});

		getScreenDimension();
		initPager();

	}

	private void getScreenDimension() {
		DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
		mScreenWidth = metrics.widthPixels;
		mScreenHeight = metrics.heightPixels;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
		}

	}

	private void initPager() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		items.add(R.drawable.pink);
		items.add(R.drawable.blue);
		items.add(R.drawable.yellow);
		items.add(R.drawable.red);
		items.add(R.drawable.blue);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);
		items.add(R.drawable.red);
		items.add(R.drawable.green);

		MyPagerAdapter imageAdapter = new MyPagerAdapter(this, items);
		mViewPager = (MyPager) findViewById(R.id.pager);
		mViewPager.setAdapter(imageAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOffscreenPageLimit(10);
		mViewPager.setPageTransformer(true, new MyPageTransformer());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
			}

			@Override
			public void onPageScrolled(int index, float arg1, int arg2) {
				mCurrentPage = index;
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void exitAnimation() {
		mCurrentPage = mViewPager.getCurrentItem();
		mChildsCount = mViewPager.getChildCount();
		for (int i = mChildsCount - 1; i >= 0; i--) {
			mViewPager.getChildAt(i).animate().rotationY(0).setDuration(1000)
					.setStartDelay(500);
			float trX;
			ObjectAnimator tx = null;
			if (i == mChildsCount - 1) {
				trX = 0;
				tx = ObjectAnimator.ofFloat(mViewPager.getChildAt(i),
						View.TRANSLATION_X, trX);
				tx.setStartDelay(0);
			} else if (i <= mCurrentPage) {
				trX = 0;
				tx = ObjectAnimator.ofFloat(mViewPager.getChildAt(i),
						View.TRANSLATION_X, trX);
				tx.setStartDelay((mChildsCount - 1 - mCurrentPage) * 100 + 250);
			} else {
				trX = 80;
				tx = ObjectAnimator.ofFloat(mViewPager.getChildAt(i),
						View.TRANSLATION_X, trX);
				tx.setStartDelay((mChildsCount - 1 - i) * 100 + 250);
			}
			tx.setDuration(500);
			tx.start();

			float scaleXFactor = mScreenWidth
					/ mViewPager.getChildAt(i).getWidth();
			float scaleYFactor = (mScreenHeight - mStatusBarHeight)
					/ mViewPager.getHeight();
			mViewPager.getChildAt(i).animate().scaleY(scaleXFactor)
					.setDuration(1000);
			mViewPager.getChildAt(i).animate().scaleX(scaleYFactor)
					.setDuration(1000);
		}

		mViewPager.setPageTransformer(true, new PageTransformer() {

			@Override
			public void transformPage(View arg0, float arg1) {
				// reset pageTransform
			}
		});
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void initAnimation() {
		Constant.ANIMATION_STATE = true;
		mViewPager.setPivotX(0);
		mViewPager.setPivotY(mScreenWidth / 2);
		mCurrentPage = mViewPager.getCurrentItem();
		mChildsCount = mViewPager.getChildCount();
		
		int translationXDuration = 250;
		ObjectAnimator trX;

		for (int i = 0; i < mChildsCount; i++) {
			
			mViewPager.getChildAt(i).animate().rotationY(-Constant.ROTATION_DEGREE).setDuration(250);
			
			if (i <= mCurrentPage) {
				trX = ObjectAnimator.ofFloat(mViewPager.getChildAt(i),
						View.TRANSLATION_X, -Constant.PAGE_TRANSLATE_X_AFTER_ROTATING);
				trX.setStartDelay(0);
				trX.setDuration(translationXDuration);
			} else {
				trX = ObjectAnimator.ofFloat(mViewPager.getChildAt(i),
						View.TRANSLATION_X, -Constant.PAGE_TRANSLATE_X_AFTER_ROTATING - (i - mCurrentPage)
								* (Constant.PAGE_TRANSLATE_X));
				trX.setStartDelay((i - mCurrentPage) * 100 + 150);
				trX.setDuration(translationXDuration);
			}
			trX.start();

			if (i <= mCurrentPage) {
				mViewPager.getChildAt(i).animate().scaleY(Constant.SCALE_FACTOR)
						.setDuration(translationXDuration).setStartDelay(translationXDuration);
				mViewPager.getChildAt(i).animate().scaleX(Constant.SCALE_FACTOR)
						.setDuration(translationXDuration).setStartDelay(translationXDuration);
			} else {
				
				// decrease page size based on index of page
				float nextPageScaleFactor = Constant.SCALE_FACTOR - 0.01f * (i - mCurrentPage);
				mViewPager.getChildAt(i).animate().scaleY(nextPageScaleFactor)
						.setDuration(translationXDuration).setStartDelay(translationXDuration);
				mViewPager.getChildAt(i).animate().scaleX(nextPageScaleFactor)
						.setDuration(translationXDuration).setStartDelay(translationXDuration);
			}

		}
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Constant.ANIMATION_STATE = false;
			}
		}, 250);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		if (Constant.ANIMATION_STATE) {
			initAnimation();
		}
		switch (action) {

		case MotionEvent.ACTION_DOWN:
			mAreaMargin = false;
			if (mVelocityTracker == null) {
				mVelocityTracker = VelocityTracker.obtain();
			} else {
				mVelocityTracker.clear();
			}
			mCurrentPage = mViewPager.getCurrentItem();
			mViewPagerWidth = mViewPager.getWidth();
			mVelocityTracker.addMovement(event);

			break;

		case MotionEvent.ACTION_MOVE:
			if (!Constant.ANIMATION_STATE) {
				mPosX = event.getX();
				mVelocityTracker.addMovement(event);
				mVelocityTracker.computeCurrentVelocity(100);
				// 1000 provides pixels per second

				float xVelocity = mVelocityTracker.getXVelocity();

				if (xVelocity > 10 && !mAreaMargin) {
					mViewPager.setScrollDurationFactor(3);
					mViewPager.setCurrentItem(mCurrentPage + 1, true);
				}

				if (xVelocity < -10 && !mAreaMargin) {
					mViewPager.setScrollDurationFactor(8);
					if (mCurrentPage > 0)
						mViewPager.setCurrentItem(mCurrentPage - 1, true);
					else
						mCurrentPage = 0;
				}

				// decrease scroll speed in left margin
				if (mPosX < mViewPagerWidth / 6) {
					mViewPager.setScrollDurationFactor(6);
					sendMessageIncreaseSpeed(5);
					if (mCurrentPage > 0) {
						mViewPager.setCurrentItem(mCurrentPage - 1, true);
					} else {
						mCurrentPage = 0;
					}
					mAreaMargin = true;
				} else {
					mAreaMargin = false;
				}

				// increase scroll speed in right margin
				if (mPosX > (mViewPagerWidth / 6) * 5) {
					mViewPager.setScrollDurationFactor(2);
					sendMessageIncreaseSpeed(1);
					mViewPager.setCurrentItem(mCurrentPage + 1, true);
					mAreaMargin = true;
				} else {
					mAreaMargin = false;
				}
			}
			break;
		}

		return true;
	}

	private void sendMessageIncreaseSpeed(int scroll) {
		if (mHandler.hasMessages(Constant.SCROLL_SPEED_INCREASING)) {
			mHandler.removeMessages(Constant.SCROLL_SPEED_INCREASING);
		}
		Message msg = mHandler.obtainMessage();
		msg.what = Constant.SCROLL_SPEED_INCREASING;
		msg.arg1 = scroll;
		mHandler.sendMessageDelayed(msg, 100);
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.SCROLL_SPEED_INCREASING:
				mViewPager.setScrollDurationFactor(msg.arg1);
			}
		}
	};

	public void onBackPressed() {
		Constant.ANIMATION_STATE = true;
		finish();
	};
}
