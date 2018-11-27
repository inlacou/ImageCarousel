package com.inlacou.imagecarroussel

import android.support.v4.app.FragmentManager

data class ImageCarouselMdl @JvmOverloads constructor(
		val fragmentManager: FragmentManager?,
		val urls: List<String>,
		val positionDisplay: PositionDisplayMode = PositionDisplayMode.NONE,
		/**
		 * @return true if handled, false if not
		 */
		val onItemClick: ((Int) -> Boolean)? = null,
		val onPageShown: ((Int) -> Unit)? = null
)