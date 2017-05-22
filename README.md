# ImageCarroussel

[![](https://jitpack.io/v/byvapps/ImageCarroussel.svg)](https://jitpack.io/#byvapps/ImageCarroussel)

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

```java
	imagecarroussel.setFragmentManager(getSupportFragmentManager());

	ArrayList<String> urls = new ArrayList<>();

	urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//739.png");
	urls.add("https://pro-rankedboost.netdna-ssl.com/wp-content/uploads/2016/08/Togepi-Pokemon-Go.png");
	urls.add("http://assets.pokemon.com/assets/cms2/img/pokedex/full//748.png");
	urls.add("https://vignette3.wikia.nocookie.net/pokemon/images/b/b4/393Piplup_Pokemon_Ranger_Guardian_Signs.png/revision/latest?cb=20150109224144");

	imagecarroussel.populate(urls);
```
You can add it inside your CollaspsingToolbarLayout, for example:

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


