package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.data.AdvertisementViewHolder
import woowacourse.movie.view.data.MovieInfoViewHolder
import woowacourse.movie.view.data.MovieListViewType
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.MovieViewDatas

class MovieAdapter(private val movieViewDatas: MovieViewDatas) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (MovieListViewType.values()[viewType]) {
            MovieListViewType.MOVIE -> MovieInfoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie, parent, false
                )
            )
            MovieListViewType.ADVERTISEMENT -> AdvertisementViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_advertisement, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (MovieListViewType.values()[getItemViewType(position)]) {
            MovieListViewType.MOVIE -> (holder as MovieInfoViewHolder).bind(movieViewDatas.value[position] as MovieViewData)
            MovieListViewType.ADVERTISEMENT -> (holder as AdvertisementViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int =
        movieViewDatas.value[position].viewType.ordinal

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemCount(): Int = movieViewDatas.value.size
}
