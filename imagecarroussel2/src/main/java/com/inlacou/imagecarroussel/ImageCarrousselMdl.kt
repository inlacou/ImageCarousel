package com.inlacou.imagecarroussel

import android.support.v4.app.FragmentManager

data class ImageCarrousselMdl @JvmOverloads constructor(
		val fragmentManager: FragmentManager?,
		val urls: List<String>,
		val positionDisplay: PositionDisplay = PositionDisplay(true),
		/**
		 * @return true if handled, false if not
		 */
		val onItemClick: ((Int) -> Boolean)? = null
){
	data class PositionDisplay @JvmOverloads constructor(val show: Boolean, val mode: Mode = Mode.TEXT){
		enum class Mode {
			TEXT, CIRCLES
		}
	}
}