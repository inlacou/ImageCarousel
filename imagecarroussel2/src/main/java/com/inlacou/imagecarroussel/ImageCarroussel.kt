package com.inlacou.imagecarroussel

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class ImageCarroussel @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
	: FrameLayout(context, attrs, defStyleAttr) {

	private var viewPager: ViewPager? = null
	private var pagerAdapter: PicturesFragmentPagerAdapter? = null

	var model: ImageCarrousselMdl = ImageCarrousselMdl(null, listOf())
		set(value) {
			field = value
			controller.model = value
			populate()
		}
	private lateinit var controller: ImageCarrousselCtrl

	init {
		this.initialize()
		setListeners()
		populate()
	}

	protected fun initialize() {
		val rootView = View.inflate(context, R.layout.view_imagecarroussel, this)
		viewPager = findViewById(R.id.pager)
		initialize(rootView)
	}

	fun initialize(view: View) {
		controller = ImageCarrousselCtrl(view = this, model = model)
	}

	fun populate() {
		/** Instantiating FragmentPagerAdapter  */
		model.fragmentManager?.let { fragmentManager ->
			pagerAdapter = PicturesFragmentPagerAdapter(fragmentManager, model.urls.toArrayList(), model.positionDisplay.show) {
				controller.onClick(it)
			}

			/** Setting the pagerAdapter to the pager object  */
			viewPager?.adapter = pagerAdapter
		}
	}

	private fun setListeners() {
	}

	private fun <T> List<T>.toArrayList(): ArrayList<T>{
		val result = ArrayList<T>()
		this.forEach { result.add(it) }
		return result
	}

}