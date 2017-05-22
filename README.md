ImageCarroussel

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
