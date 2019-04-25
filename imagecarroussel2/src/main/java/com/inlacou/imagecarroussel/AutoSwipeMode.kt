package com.inlacou.imagecarroussel

data class AutoSwipeMode(
		var active: Boolean = false,
		val interval: Double = 3.3,
		val continuous: Boolean = true
)