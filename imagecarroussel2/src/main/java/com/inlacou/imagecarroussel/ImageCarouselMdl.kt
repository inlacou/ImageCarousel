package com.inlacou.imagecarroussel

import android.support.v4.app.FragmentManager

data class ImageCarouselMdl @JvmOverloads constructor(
		val fragmentManager: FragmentManager?,
		val urls: List<String>,
		val positionDisplay: PositionDisplayMode = PositionDisplayMode.NONE,
		val showTopShadow: Boolean = false,
		val autoSwipe: AutoSwipeMode = AutoSwipeMode(),
		/**
		 * @return true if handled, false if not.
		 */
		val onItemClick: ((Int) -> Boolean)? = null,
		val onPageShown: ((Int) -> Unit)? = null,
		val infinite: Boolean = false,
		val pagePaddingLeft: Int? = null,
		val pagePaddingRight: Int? = null,
		val pageMargin: Int? = null
)