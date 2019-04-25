# ImageCarousel

[![](https://jitpack.io/v/inlacou/ImageCarousel.svg)](https://jitpack.io/#inlacou/ImageCarousel)

Add to your layout xml:

``` xml
	<com.inlacou.imagecarroussel.ImageCarroussel
		android:id="@+id/imagecarroussel"
		android:layout_width="match_parent"
		android:layout_height="250dp"
		android:fitsSystemWindows="true"
		app:layout_collapseMode="parallax"/>
```

Configure it:

```kt
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
```
Parameters:
	fragmentManager: FragmentManager
	urls: List of Strings
	positionDisplay: optional, PositionDisplayMode (custom enum class, TEXT, CIRCLES or NONE), default NONE 
	showTopShadow: optional, Boolean, default false
	autoSwipe: optional, AutoSwipeMode (custom class)
	onItemClick: optional, callback
	onPageShown: optional, callback
	pagePaddingLeft: optional, left padding, default 0
	pagePaddingRight: optional, right padding, default 0
	pageMargin: optional, page margin, default 0

You can add it inside your AppBarLayout, for example:

``` xml
<android.support.design.widget.AppBarLayout
		android:id="@+id/app_bar"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:fitsSystemWindows="true"
		android:theme="@style/AppTheme.AppBarOverlay">
		
		<android.support.design.widget.CollapsingToolbarLayout
			android:id="@+id/toolbar_layout"
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			android:fitsSystemWindows="true"
			app:contentScrim="?attr/colorPrimary"
			app:layout_scrollFlags="scroll|exitUntilCollapsed">
			
			<com.inlacou.imagecarroussel.ImageCarroussel
				android:id="@+id/imagecarroussel"
				android:layout_width="match_parent"
				android:layout_height="250dp"
				android:fitsSystemWindows="true"
				app:layout_collapseMode="parallax"/>
			
			<android.support.v7.widget.Toolbar
				android:id="@+id/toolbar"
				android:layout_width="match_parent"
				android:layout_height="?attr/actionBarSize"
				app:layout_collapseMode="pin"
				app:popupTheme="@style/AppTheme.PopupOverlay"/>
		
		</android.support.design.widget.CollapsingToolbarLayout>
	</android.support.design.widget.AppBarLayout>
```
If so, consider using this too:

``` java
	CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
	collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
```


