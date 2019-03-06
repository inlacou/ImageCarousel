package com.inlacou.imagecarroussel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter

import java.util.ArrayList

/**
 * Created by inlacou on 3/05/16.
 */
class PicturesFragmentPagerAdapter @JvmOverloads
constructor(
		fragmentManager: FragmentManager,
		private val urls: ArrayList<String>,
		private val positionDisplay: PositionDisplayMode,
		private val showTopShadow: Boolean,
		private val infinite: Boolean,
		private val onClick: ((Int) -> Unit)? = null)
	: android.support.v4.app.FragmentStatePagerAdapter(fragmentManager) {

	private val pageCount: Int

	companion object {
		const val MAX_VALUE = 1000
	}

	init {
		for (i in urls.indices.reversed()) {
			if (urls[i].isEmpty()) urls.removeAt(i)
		}
		pageCount = urls.size
	}

	/** This method will be invoked when a page is requested to create  */
	override fun getItem(position: Int): Fragment {
		val virtualPos = if(infinite) position%pageCount else position
		val myFragment = PageImageFragment()
		val data = Bundle()
		data.putInt("current_page", virtualPos)
		data.putInt("max_pages", pageCount)
		data.putBoolean("showTopShadow", showTopShadow)
		data.putInt("positionDisplay", positionDisplay.ordinal)
		data.putString("url", urls[virtualPos])
		myFragment.onClickListener = { onClick?.invoke(virtualPos) }
		myFragment.arguments = data
		return myFragment
	}

	/** Returns the number of pages  */
	override fun getCount(): Int {
		return if(infinite) {
			if(pageCount==0) pageCount else MAX_VALUE
		}else {
			pageCount
		}
	}

	override fun getItemPosition(`object`: Any): Int {
		return PagerAdapter.POSITION_NONE
	}
}
