package com.inlacou.imagecarroussel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by inlacou on 3/05/16.
 */
public class PicturesFragmentPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {
	private static final String DEBUG_TAG = PicturesFragmentPagerAdapter.class.getName();
	private final Callbacks mCallbacks;
	private int pageCount;
	private final ArrayList<String> urls;

	/** Constructor of the class */
	public PicturesFragmentPagerAdapter(FragmentManager fm, ArrayList<String> urls, Callbacks callbacks) {
		super(fm);
		for (int i=urls.size()-1; i>=0; i--){
			if(urls.get(i)!=null && urls.get(i).isEmpty()) urls.remove(i);
		}
		this.urls = urls;
		pageCount = urls.size();
		mCallbacks = callbacks;
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(final int position) {
		PageImageFragment myFragment = new PageImageFragment();
		Bundle data = new Bundle();
		data.putInt("current_page", position);
		data.putInt("max_pages", pageCount);
		data.putString("url", urls.get(position));
		myFragment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCallbacks.onItemClick(position);
			}
		});
		myFragment.setArguments(data);
		return myFragment;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {
		return pageCount;
	}

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}

	public interface Callbacks {
		void onItemClick(int position);
	}
}
