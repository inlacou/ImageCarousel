package com.inlacou.imagecarroussel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import java.io.Serializable

import java.util.ArrayList

/**
 * Created by inlacou on 3/05/16.
 */
class PicturesFragmentPagerAdapter @JvmOverloads
constructor(
		fm: FragmentManager,
		private val urls: ArrayList<String>,
		private val positionDisplay: PositionDisplayMode,
		private val onClick: ((Int) -> Unit)? = null)
	: android.support.v4.app.FragmentStatePagerAdapter(fm) {

	private val pageCount: Int

	init {
		for (i in urls.indices.reversed()) {
			if (urls[i].isEmpty()) urls.removeAt(i)
		}
		pageCount = urls.size
	}

	/** This method will be invoked when a page is requested to create  */
	override fun getItem(position: Int): Fragment {
		val myFragment = PageImageFragment()
		val data = Bundle()
		data.putInt("current_page", position)
		data.putInt("max_pages", pageCount)
		data.putInt("positionDisplay", positionDisplay.ordinal)
		data.putString("url", urls[position])
		myFragment.onClickListener = { onClick?.invoke(position) }
		myFragment.arguments = data
		return myFragment
	}

	/** Returns the number of pages  */
	override fun getCount(): Int {
		return pageCount
	}

	override fun getItemPosition(`object`: Any): Int {
		return PagerAdapter.POSITION_NONE
	}
}
