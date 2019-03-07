package com.inlacou.imagecarrousselapp

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.inlacou.imagecarroussel.AutoSwipeMode

import com.inlacou.imagecarroussel.ImageCarousel
import com.inlacou.imagecarroussel.ImageCarouselMdl
import com.inlacou.imagecarroussel.PositionDisplayMode

import java.util.ArrayList

class NormalActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_normal)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		val imagecarroussel = findViewById<ImageCarousel>(R.id.imagecarroussel)

		val urls = ArrayList<String>()

		urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//739.png")
		urls.add("https://pro-rankedboost.netdna-ssl.com/wp-content/uploads/2016/08/Togepi-Pokemon-Go.png")
		urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//748.png")
		urls.add("https://vignette3.wikia.nocookie.net/pokemon/images/b/b4/393Piplup_Pokemon_Ranger_Guardian_Signs.png/revision/latest?cb=20150109224144")
		urls.add("https://vignette.wikia.nocookie.net/es.pokemon/images/4/4f/Torchic.png/revision/latest?cb=20140612153748")
		urls.add("https://vignette.wikia.nocookie.net/es.pokemon/images/4/43/Bulbasaur.png/revision/latest?cb=20170120032346")
		urls.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full//133.png")

		imagecarroussel.model = ImageCarouselMdl(
				fragmentManager = supportFragmentManager,
				urls = urls,
				positionDisplay = PositionDisplayMode.CIRCLES,
				autoSwipe = AutoSwipeMode(active = true),
				showTopShadow = false,
				pagePaddingRight = 60,
				pageMargin = 15)

		setSupportActionBar(toolbar)

		val fab = findViewById<FloatingActionButton>(R.id.fab)
		fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_scrolling, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		val id = item.itemId


		return if (id == R.id.action_settings) {
			true
		} else super.onOptionsItemSelected(item)
	}
}
