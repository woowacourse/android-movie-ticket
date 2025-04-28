package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    movies: List<Movie>,
    private val clickListener: ClickListener,
) : BaseAdapter() {

    private val items: List<ItemType> = createAdsWithMovies(movies)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ItemType = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movieListViewHolder: MovieListViewHolder
        val adViewHolder: AdViewHolder
        val item = items[position]

        if (convertView == null) {
            view = when (item) {
                ItemType.ADType -> {
                    val adView = LayoutInflater.from(parent?.context)
                        .inflate(R.layout.ad_item, parent, false)
                    adViewHolder = AdViewHolder(adView)
                    adView.tag = adViewHolder
                    adView
                }
                is ItemType.MovieType -> {
                    val movieView = LayoutInflater.from(parent?.context)
                        .inflate(R.layout.movie_item, parent, false)
                    movieListViewHolder = MovieListViewHolder(movieView)
                    movieListViewHolder.setItem(item.movie)
                    movieListViewHolder.reserveButton.setOnClickListener {
                        clickListener.onReserveClick(item.movie)
                    }
                    movieView.tag = movieListViewHolder
                    movieView
                }
            }
        } else {
            view = convertView
            when (item) {
                ItemType.ADType -> {
                    adViewHolder = view.tag as AdViewHolder
                    adViewHolder.adImageView
                }
                is ItemType.MovieType -> {
                    movieListViewHolder = view.tag as MovieListViewHolder

                    movieListViewHolder.setItem(item.movie)
                    movieListViewHolder.reserveButton.setOnClickListener {
                        clickListener.onReserveClick(item.movie)
                    }
                }
            }
        }

        return view
    }

    private fun createAdsWithMovies(movies: List<Movie>): List<ItemType> {
        val result = mutableListOf<ItemType>()
        for (i in movies.indices) {
            result.add(ItemType.MovieType(movies[i]))
            if ((i + 1) % 3 == 0) {
                result.add(ItemType.ADType)
            }
        }
        return result.toList()
    }
}
