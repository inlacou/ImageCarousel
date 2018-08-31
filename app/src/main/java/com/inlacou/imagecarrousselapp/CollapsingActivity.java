package com.inlacou.imagecarrousselapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.inlacou.imagecarroussel.ImageCarousel;
import com.inlacou.imagecarroussel.ImageCarouselMdl;
import com.inlacou.imagecarroussel.PositionDisplayMode;

import java.util.ArrayList;

public class CollapsingActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collapsing_toolbar);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
		collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
		collapsingToolbarLayout.setTitleEnabled(true);
		collapsingToolbarLayout.setTitle("Collapsing");
		ImageCarousel imagecarroussel = findViewById(R.id.imagecarroussel);
		
		ArrayList<String> urls = new ArrayList<>();
		
		urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//739.png");
		urls.add("https://pro-rankedboost.netdna-ssl.com/wp-content/uploads/2016/08/Togepi-Pokemon-Go.png");
		urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//748.png");
		urls.add("https://vignette3.wikia.nocookie.net/pokemon/images/b/b4/393Piplup_Pokemon_Ranger_Guardian_Signs.png/revision/latest?cb=20150109224144");
		
		imagecarroussel.setModel(new ImageCarouselMdl(getSupportFragmentManager(), urls, PositionDisplayMode.TEXT, null));
		
		setSupportActionBar(toolbar);
		
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_scrolling, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
