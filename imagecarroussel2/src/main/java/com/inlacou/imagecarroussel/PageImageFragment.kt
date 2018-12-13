package com.inlacou.imagecarroussel

import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.inlacou.imagecarroussel.PositionDisplayMode.*
import com.inlacou.imagecarroussel.R.id.shadow

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by inlacou on 3/05/16.
 */
class PageImageFragment : Fragment() {

	private var llIndicator: LinearLayout? = null
	private var shadow: ImageView? = null
	private var image: ImageView? = null
	private var positionText: TextView? = null
	var onClickListener: ((Int) -> Unit)? = null

	private var mCurrentPage: Int = 0
	private var maxPages: Int = 0
	private var url: String = "something"
	private var showTopShadow: Boolean = true
	private var positionDisplay: PositionDisplayMode = NONE

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		/** Getting the arguments to the Bundle object  */
		val data = arguments

		/** Getting integer data of the key current_page from the bundle  */
		maxPages = data!!.getInt("max_pages", 0)
		mCurrentPage = data.getInt("current_page", 0)
		url = data.getString("url", "something")
		showTopShadow = data.getBoolean("showTopShadow", true)
		positionDisplay = values()[data.getInt("positionDisplay")]
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val v = inflater.inflate(R.layout.viewpager_layout_page_image, container, false)
		image = v.findViewById(R.id.image)
		shadow = v.findViewById(R.id.shadow)
		positionText = v.findViewById(R.id.position_text)
		llIndicator = v.findViewById(R.id.indicator)

		when(positionDisplay){
			TEXT -> {
				llIndicator?.visibility = View.GONE
				positionText?.visibility = View.VISIBLE
				positionText?.text = "${mCurrentPage + 1}/$maxPages"
			}
			CIRCLES -> {
				positionText?.visibility = View.VISIBLE
				positionText?.visibility = View.GONE
				setCurrentPageIndicator(mCurrentPage)
			}
			NONE -> {
				llIndicator?.visibility = View.GONE
				positionText?.visibility = View.GONE
			}
		}

		shadow?.visibility = if(showTopShadow){
			View.VISIBLE
		}else{
			View.GONE
		}

		image?.setOnClickListener { onClickListener?.invoke(mCurrentPage) }

		val size = Point()
		activity?.windowManager?.defaultDisplay?.getSize(size)

		if (url.isNotEmpty())
			Picasso.get().load(url)
					//.resize(size.x, 500)
					.fit()
					.centerCrop()
					.into(image, object : Callback {
						override fun onSuccess() {
							/*Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
		                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
		                        public void onGenerated(Palette palette) {
		                            positionText.setTextColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
		                        }
		                    });*/
						}

						override fun onError(e: Exception) {

						}
					})
		return v
	}


	private fun setCurrentPageIndicator(position: Int){
		llIndicator?.removeAllViews()
		(0 until position)
				.forEach { _ -> llIndicator?.addView(newLightCircle()) }
		llIndicator?.addView(newDarkCircle())
		(position+1 until maxPages)
				.forEach { _ -> llIndicator?.addView(newLightCircle()) }
	}

	private fun newDarkCircle(): ImageView {
		return newCircle(R.drawable.carousel_indicator_active)
	}

	private fun newLightCircle(): ImageView {
		return newCircle(R.drawable.carousel_indicator_inactive)
	}

	private fun newCircle(drawableResId: Int): ImageView {
		val iv = ImageView(context)
		iv.layoutParams = LinearLayout.LayoutParams(12.dpToPx(), 12.dpToPx())
		(iv.layoutParams as ViewGroup.MarginLayoutParams).setMargins(4.dpToPx(),0,4.dpToPx(),0)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			iv.setImageDrawable(context?.resources?.getDrawable(drawableResId, null))
		}else{
			iv.setImageDrawable(context?.resources?.getDrawable(drawableResId))
		}
		return iv
	}

	private fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
}
