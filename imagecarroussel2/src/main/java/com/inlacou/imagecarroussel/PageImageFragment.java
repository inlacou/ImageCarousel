package com.inlacou.imagecarroussel;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by inlacou on 3/05/16.
 */
public class PageImageFragment extends Fragment {
	private static final String DEBUG_TAG = PageImageFragment.class.getName();
	private int mCurrentPage, maxPages;
	private ImageView image;
	private TextView position;
	private String url;
	private boolean showPageNumber;
	private View.OnClickListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/** Getting the arguments to the Bundle object */
		Bundle data = getArguments();

		/** Getting integer data of the key current_page from the bundle */
		maxPages = data.getInt("max_pages", 0);
		mCurrentPage = data.getInt("current_page", 0);
		url = data.getString("url", "");
		showPageNumber = data.getBoolean("pageNumber");

	}

	public void setOnClickListener(View.OnClickListener listener){
		mListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewpager_layout_page_image, container, false);
		image = v.findViewById(R.id.image);
		position = v.findViewById(R.id.position);
		if(showPageNumber){
			position.setVisibility(View.VISIBLE);
		}else{
			position.setVisibility(View.GONE);
		}
		image.setOnClickListener(mListener);

		position.setText((mCurrentPage+1) + "/" + maxPages);

		Point size = new Point();
		getActivity().getWindowManager().getDefaultDisplay().getSize(size);

		if(!url.isEmpty()) Picasso.get().load(url)
				//.resize(size.x, 500)
				.fit()
				.centerCrop()
				.into(image, new Callback() {
					@Override public void onSuccess() {
						Log.d(DEBUG_TAG, "onSuccess");
                        /*Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette palette) {
                                position.setTextColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
                            }
                        });*/
					}
					
					@Override
					public void onError(Exception e) {
					
					}
				});
		return v;
	}
}
