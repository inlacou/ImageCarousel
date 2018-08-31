package com.inlacou.imagecarroussel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.etiennelawlor.imagegallery.library.activities.FullScreenImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.adapters.FullScreenImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.adapters.ImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by inlacoubyv on 6/11/15.
 */
public class ImageCarroussel extends FrameLayout {
	
	private static final String DEBUG_TAG = ImageCarroussel.class.getSimpleName();
	private Context context;
	private PaletteColorType paletteColorType;
	
	private Callbacks mCallbacks;
	private ViewPager viewPager;
	private PicturesFragmentPagerAdapter pagerAdapter;
	private FragmentManager fragmentManager;
	
	public ImageCarroussel(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	public ImageCarroussel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	
	public ImageCarroussel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}
	
	private void init(){
		getData();
		initialize();
		setListeners();
	}
	
	public void getData(){
	}
	
	public void initialize(View view) {
		viewPager = view.findViewById(R.id.pager);
	}
	
	protected void initialize() {
		View rootView = inflate(context, R.layout.view_imagecarroussel, this);
		initialize(rootView);
	}
	
	public void setFragmentManager(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}
	
	public void populate(final ArrayList<String> urls) {
		populate(urls, true);
	}
	
	public void populate(final ArrayList<String> urls, boolean showPageNumber) {
		/** Instantiating FragmentPagerAdapter */
		pagerAdapter = new PicturesFragmentPagerAdapter(fragmentManager, urls, showPageNumber, new PicturesFragmentPagerAdapter.Callbacks() {
			@Override
			public void onItemClick(int position) {
				if(mCallbacks==null || !mCallbacks.onItemClick()){
					if(urls.size()==0){
						return;
					}
					
					Intent intent = new Intent(context, ImageGalleryActivity.class);
					
					callbacks();
					
					intent.putStringArrayListExtra(ImageGalleryActivity.KEY_IMAGES, urls);
					
					context.startActivity(intent);
				}
			}
		});
		
		/** Setting the pagerAdapter to the pager object */
		viewPager.setAdapter(pagerAdapter);
	}
	
	private void callbacks() {
		Log.d(DEBUG_TAG+".callbacks", "callbacks");
		ImageGalleryActivity.setImageThumbnailLoader(new ImageGalleryAdapter.ImageThumbnailLoader() {
			@Override
			public void loadImageThumbnail(ImageView iv, String imageUrl, int dimension) {
				Log.d(DEBUG_TAG+".loadImageThumbnail", "imageUrl: " + imageUrl);
				if (!android.text.TextUtils.isEmpty(imageUrl)) {
					Picasso.get()
							.load(imageUrl)
							.resize(dimension, dimension)
							.centerCrop()
							.into(iv);
				} else {
					iv.setImageDrawable(null);
				}
			}
		});
		FullScreenImageGalleryActivity.setFullScreenImageLoader(new FullScreenImageGalleryAdapter.FullScreenImageLoader() {
			@Override
			public void loadFullScreenImage(final ImageView iv, String imageUrl, int width, final LinearLayout bgLinearLayout) {
				Log.d(DEBUG_TAG+".loadFullScreenImage", "imageUrl: " + imageUrl);
				if (!android.text.TextUtils.isEmpty(imageUrl)) {
					Picasso.get()
							.load(imageUrl)
							.resize(width, 0)
							.into(iv, new Callback() {
								@Override
								public void onSuccess() {
									Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
									Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
										public void onGenerated(Palette palette) {
											applyPalette(palette, bgLinearLayout);
										}
									});
								}
								
								@Override
								public void onError(Exception e) {
								
								}
							});
				} else {
					iv.setImageDrawable(null);
				}
			}
		});
	}
	
	private void applyPalette(Palette palette, View view){
		int bgColor = getBackgroundColor(palette);
		if (bgColor != -1)
			view.setBackgroundColor(bgColor);
	}
	
	private int getBackgroundColor(Palette palette) {
		int bgColor = -1;
		
		int vibrantColor = palette.getVibrantColor(0xFF0000);
		int lightVibrantColor = palette.getLightVibrantColor(0x00FF00);
		int darkVibrantColor = palette.getDarkVibrantColor(0x0000FF);
		
		int mutedColor = palette.getMutedColor(0xF0F000);
		int lightMutedColor = palette.getLightMutedColor(0x0F0F00);
		int darkMutedColor = palette.getDarkMutedColor(0x00F0F0);
		
		if (paletteColorType != null) {
			switch (paletteColorType) {
				case VIBRANT:
					if (vibrantColor != 0) { // primary option
						bgColor = vibrantColor;
					} else if (lightVibrantColor != 0) { // fallback options
						bgColor = lightVibrantColor;
					} else if (darkVibrantColor != 0) {
						bgColor = darkVibrantColor;
					} else if (mutedColor != 0) {
						bgColor = mutedColor;
					} else if (lightMutedColor != 0) {
						bgColor = lightMutedColor;
					} else if (darkMutedColor != 0) {
						bgColor = darkMutedColor;
					}
					break;
				case LIGHT_VIBRANT:
					if (lightVibrantColor != 0) { // primary option
						bgColor = lightVibrantColor;
					} else if (vibrantColor != 0) { // fallback options
						bgColor = vibrantColor;
					} else if (darkVibrantColor != 0) {
						bgColor = darkVibrantColor;
					} else if (mutedColor != 0) {
						bgColor = mutedColor;
					} else if (lightMutedColor != 0) {
						bgColor = lightMutedColor;
					} else if (darkMutedColor != 0) {
						bgColor = darkMutedColor;
					}
					break;
				case DARK_VIBRANT:
					if (darkVibrantColor != 0) { // primary option
						bgColor = darkVibrantColor;
					} else if (vibrantColor != 0) { // fallback options
						bgColor = vibrantColor;
					} else if (lightVibrantColor != 0) {
						bgColor = lightVibrantColor;
					} else if (mutedColor != 0) {
						bgColor = mutedColor;
					} else if (lightMutedColor != 0) {
						bgColor = lightMutedColor;
					} else if (darkMutedColor != 0) {
						bgColor = darkMutedColor;
					}
					break;
				case MUTED:
					if (mutedColor != 0) { // primary option
						bgColor = mutedColor;
					} else if (lightMutedColor != 0) { // fallback options
						bgColor = lightMutedColor;
					} else if (darkMutedColor != 0) {
						bgColor = darkMutedColor;
					} else if (vibrantColor != 0) {
						bgColor = vibrantColor;
					} else if (lightVibrantColor != 0) {
						bgColor = lightVibrantColor;
					} else if (darkVibrantColor != 0) {
						bgColor = darkVibrantColor;
					}
					break;
				case LIGHT_MUTED:
					if (lightMutedColor != 0) { // primary option
						bgColor = lightMutedColor;
					} else if (mutedColor != 0) { // fallback options
						bgColor = mutedColor;
					} else if (darkMutedColor != 0) {
						bgColor = darkMutedColor;
					} else if (vibrantColor != 0) {
						bgColor = vibrantColor;
					} else if (lightVibrantColor != 0) {
						bgColor = lightVibrantColor;
					} else if (darkVibrantColor != 0) {
						bgColor = darkVibrantColor;
					}
					break;
				case DARK_MUTED:
					if (darkMutedColor != 0) { // primary option
						bgColor = darkMutedColor;
					} else if (mutedColor != 0) { // fallback options
						bgColor = mutedColor;
					} else if (lightMutedColor != 0) {
						bgColor = lightMutedColor;
					} else if (vibrantColor != 0) {
						bgColor = vibrantColor;
					} else if (lightVibrantColor != 0) {
						bgColor = lightVibrantColor;
					} else if (darkVibrantColor != 0) {
						bgColor = darkVibrantColor;
					}
					break;
				default:
					break;
			}
		}
		
		return bgColor;
	}
	
	private void setListeners() {
	}
	
	public void setCallbacks(Callbacks mCallbacks) {
		this.mCallbacks = mCallbacks;
	}
	
	public interface Callbacks{
		boolean onItemClick();
	}
	
}
