package com.smb.pager;

import java.util.ArrayList;

import com.smb.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyPagerAdapter extends PagerAdapter {
	  private final Activity _activity;
	    private final ArrayList<Integer> _imagePaths;
	    private LayoutInflater inflater;

	    public MyPagerAdapter(Activity activity, ArrayList<Integer> imagePaths) {
	        this._activity = activity;
	        this._imagePaths = imagePaths;
	    }

	    @Override
	    public int getCount() {
	        return this._imagePaths.size();
	    }

	    @Override
	    public boolean isViewFromObject(View view, Object object) {
	        return view == ((RelativeLayout) object);
	    }

	    @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container, false);
	        
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	        ImageView imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
	        imgDisplay.setImageResource(_imagePaths.get(position));

	        ((ViewPager) container).addView(viewLayout);

	        return viewLayout;
	    }

	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        ((ViewPager) container).removeView((RelativeLayout) object);
	    }
}
