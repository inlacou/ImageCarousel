package com.inlacou.imagecarroussel

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.view.View
import com.etiennelawlor.imagegallery.library.activities.FullScreenImageGalleryActivity
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageCarouselCtrl(val view: ImageCarousel, var model: ImageCarouselMdl) {

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
				PaletteColorType.VIBRANT -> when {
					vibrantColor != 0 -> bgColor = vibrantColor // primary option
					lightVibrantColor != 0 -> bgColor = lightVibrantColor // fallback options
					darkVibrantColor != 0 -> bgColor = darkVibrantColor
					mutedColor != 0 -> bgColor = mutedColor
					lightMutedColor != 0 -> bgColor = lightMutedColor
					darkMutedColor != 0 -> bgColor = darkMutedColor
				}
				PaletteColorType.LIGHT_VIBRANT -> when {
					lightVibrantColor != 0 -> bgColor = lightVibrantColor // primary option
					vibrantColor != 0 -> bgColor = vibrantColor // fallback options
					darkVibrantColor != 0 -> bgColor = darkVibrantColor
					mutedColor != 0 -> bgColor = mutedColor
					lightMutedColor != 0 -> bgColor = lightMutedColor
					darkMutedColor != 0 -> bgColor = darkMutedColor
				}
				PaletteColorType.DARK_VIBRANT -> when {
					darkVibrantColor != 0 -> bgColor = darkVibrantColor // primary option
					vibrantColor != 0 -> bgColor = vibrantColor // fallback options
					lightVibrantColor != 0 -> bgColor = lightVibrantColor
					mutedColor != 0 -> bgColor = mutedColor
					lightMutedColor != 0 -> bgColor = lightMutedColor
					darkMutedColor != 0 -> bgColor = darkMutedColor
				}
				PaletteColorType.MUTED -> when {
					mutedColor != 0 -> bgColor = mutedColor // primary option
					lightMutedColor != 0 -> bgColor = lightMutedColor // fallback options
					darkMutedColor != 0 -> bgColor = darkMutedColor
					vibrantColor != 0 -> bgColor = vibrantColor
					lightVibrantColor != 0 -> bgColor = lightVibrantColor
					darkVibrantColor != 0 -> bgColor = darkVibrantColor
				}
				PaletteColorType.LIGHT_MUTED -> when {
					lightMutedColor != 0 -> bgColor = lightMutedColor // primary option
					mutedColor != 0 -> bgColor = mutedColor // fallback options
					darkMutedColor != 0 -> bgColor = darkMutedColor
					vibrantColor != 0 -> bgColor = vibrantColor
					lightVibrantColor != 0 -> bgColor = lightVibrantColor
					darkVibrantColor != 0 -> bgColor = darkVibrantColor
				}
				PaletteColorType.DARK_MUTED -> when {
					darkMutedColor != 0 -> bgColor = darkMutedColor // primary option
					mutedColor != 0 -> bgColor = mutedColor // fallback options
					lightMutedColor != 0 -> bgColor = lightMutedColor
					vibrantColor != 0 -> bgColor = vibrantColor
					lightVibrantColor != 0 -> bgColor = lightVibrantColor
					darkVibrantColor != 0 -> bgColor = darkVibrantColor
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