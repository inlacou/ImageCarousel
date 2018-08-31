package com.inlacou.imagecarroussel

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.util.Log
import android.view.View
import com.etiennelawlor.imagegallery.library.activities.FullScreenImageGalleryActivity
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageCarrousselCtrl(val view: ImageCarroussel, var model: ImageCarrousselMdl) {

	private val paletteColorType: PaletteColorType? = null

	private fun weirdCallbacks() {
		ImageGalleryActivity.setImageThumbnailLoader { iv, imageUrl, dimension ->
			if (!android.text.TextUtils.isEmpty(imageUrl)) {
				Picasso.get()
						.load(imageUrl)
						.resize(dimension, dimension)
						.centerCrop()
						.into(iv)
			} else {
				iv.setImageDrawable(null)
			}
		}
		FullScreenImageGalleryActivity.setFullScreenImageLoader { iv, imageUrl, width, bgLinearLayout ->
			if (!android.text.TextUtils.isEmpty(imageUrl)) {
				Picasso.get()
						.load(imageUrl)
						.resize(width, 0)
						.into(iv, object : Callback {
							override fun onSuccess() {
								val bitmap = (iv.drawable as BitmapDrawable).bitmap
								Palette.from(bitmap).generate { palette -> applyPalette(palette, bgLinearLayout) }
							}

							override fun onError(e: Exception) {

							}
						})
			} else {
				iv.setImageDrawable(null)
			}
		}
	}

	private fun applyPalette(palette: Palette, view: View) {
		val bgColor = getBackgroundColor(palette)
		if (bgColor != -1)
			view.setBackgroundColor(bgColor)
	}

	private fun getBackgroundColor(palette: Palette): Int {
		var bgColor = -1

		val vibrantColor = palette.getVibrantColor(0xFF0000)
		val lightVibrantColor = palette.getLightVibrantColor(0x00FF00)
		val darkVibrantColor = palette.getDarkVibrantColor(0x0000FF)

		val mutedColor = palette.getMutedColor(0xF0F000)
		val lightMutedColor = palette.getLightMutedColor(0x0F0F00)
		val darkMutedColor = palette.getDarkMutedColor(0x00F0F0)

		if (paletteColorType != null) {
			when (paletteColorType) {
				PaletteColorType.VIBRANT -> if (vibrantColor != 0) { // primary option
					bgColor = vibrantColor
				} else if (lightVibrantColor != 0) { // fallback options
					bgColor = lightVibrantColor
				} else if (darkVibrantColor != 0) {
					bgColor = darkVibrantColor
				} else if (mutedColor != 0) {
					bgColor = mutedColor
				} else if (lightMutedColor != 0) {
					bgColor = lightMutedColor
				} else if (darkMutedColor != 0) {
					bgColor = darkMutedColor
				}
				PaletteColorType.LIGHT_VIBRANT -> if (lightVibrantColor != 0) { // primary option
					bgColor = lightVibrantColor
				} else if (vibrantColor != 0) { // fallback options
					bgColor = vibrantColor
				} else if (darkVibrantColor != 0) {
					bgColor = darkVibrantColor
				} else if (mutedColor != 0) {
					bgColor = mutedColor
				} else if (lightMutedColor != 0) {
					bgColor = lightMutedColor
				} else if (darkMutedColor != 0) {
					bgColor = darkMutedColor
				}
				PaletteColorType.DARK_VIBRANT -> if (darkVibrantColor != 0) { // primary option
					bgColor = darkVibrantColor
				} else if (vibrantColor != 0) { // fallback options
					bgColor = vibrantColor
				} else if (lightVibrantColor != 0) {
					bgColor = lightVibrantColor
				} else if (mutedColor != 0) {
					bgColor = mutedColor
				} else if (lightMutedColor != 0) {
					bgColor = lightMutedColor
				} else if (darkMutedColor != 0) {
					bgColor = darkMutedColor
				}
				PaletteColorType.MUTED -> if (mutedColor != 0) { // primary option
					bgColor = mutedColor
				} else if (lightMutedColor != 0) { // fallback options
					bgColor = lightMutedColor
				} else if (darkMutedColor != 0) {
					bgColor = darkMutedColor
				} else if (vibrantColor != 0) {
					bgColor = vibrantColor
				} else if (lightVibrantColor != 0) {
					bgColor = lightVibrantColor
				} else if (darkVibrantColor != 0) {
					bgColor = darkVibrantColor
				}
				PaletteColorType.LIGHT_MUTED -> if (lightMutedColor != 0) { // primary option
					bgColor = lightMutedColor
				} else if (mutedColor != 0) { // fallback options
					bgColor = mutedColor
				} else if (darkMutedColor != 0) {
					bgColor = darkMutedColor
				} else if (vibrantColor != 0) {
					bgColor = vibrantColor
				} else if (lightVibrantColor != 0) {
					bgColor = lightVibrantColor
				} else if (darkVibrantColor != 0) {
					bgColor = darkVibrantColor
				}
				PaletteColorType.DARK_MUTED -> if (darkMutedColor != 0) { // primary option
					bgColor = darkMutedColor
				} else if (mutedColor != 0) { // fallback options
					bgColor = mutedColor
				} else if (lightMutedColor != 0) {
					bgColor = lightMutedColor
				} else if (vibrantColor != 0) {
					bgColor = vibrantColor
				} else if (lightVibrantColor != 0) {
					bgColor = lightVibrantColor
				} else if (darkVibrantColor != 0) {
					bgColor = darkVibrantColor
				}
				else -> {
				}
			}
		}

		return bgColor
	}

	fun onClick(position: Int) {
		if(model.onItemClick?.invoke(position)!=true) {
			if (model.urls.isNotEmpty()) {
				val intent = Intent(view.context, ImageGalleryActivity::class.java)

				weirdCallbacks()

				intent.putStringArrayListExtra(ImageGalleryActivity.KEY_IMAGES, model.urls.toArrayList())

				view.context?.startActivity(intent)
			}
		}
	}

	private fun <T> List<T>.toArrayList(): ArrayList<T>{
		val result = ArrayList<T>()
		this.forEach { result.add(it) }
		return result
	}

}