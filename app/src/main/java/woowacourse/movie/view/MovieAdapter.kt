package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Advertisement
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.advertismentPolicy.AdvertisementPolicy
import woowacourse.movie.view.data.AdvertisementViewHolder
import woowacourse.movie.view.data.MovieInfoViewHolder
import woowacourse.movie.view.data.MovieListViewData
import woowacourse.movie.view.data.MovieListViewType
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.MovieViewDatas
import woowacourse.movie.view.mapper.AdvertisementMapper.toView
import woowacourse.movie.view.mapper.MovieMapper.toView

class MovieAdapter(
    movie: List<Movie>,
    advertisement: List<Advertisement>,
    advertisementPolicy: AdvertisementPolicy,
    val onClickItem: (data: MovieListViewData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movieViewDatas: MovieViewDatas =
        makeMovieListViewData(movie, advertisement, advertisementPolicy)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (MovieListViewType.values()[viewType]) {
            MovieListViewType.MOVIE -> MovieInfoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie, parent, false
                )
            ) {
                onClickItem(movieViewDatas.value[it])
            }

            MovieListViewType.ADVERTISEMENT -> AdvertisementViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_advertisement, parent, false
                )
            ) {
                onClickItem(movieViewDatas.value[it])
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (MovieListViewType.values()[getItemViewType(position)]) {
            MovieListViewType.MOVIE -> (holder as MovieInfoViewHolder).bind(movieViewDatas.value[position] as MovieViewData)
            MovieListViewType.ADVERTISEMENT -> (holder as AdvertisementViewHolder)
        }
    }

    override fun getItemViewType(position: Int): Int =
        movieViewDatas.value[position].viewType.ordinal

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemCount(): Int = movieViewDatas.value.size

    private fun updateMovieListViewData(
        movie: List<Movie>,
        advertisement: List<Advertisement>,
        advertisementPolicy: AdvertisementPolicy
    ) {
        movieViewDatas = makeMovieListViewData(movie, advertisement, advertisementPolicy)
        notifyDataSetChanged()
    }

    private fun makeMovieListViewData(
        movie: List<Movie>,
        advertisement: List<Advertisement>,
        advertisementPolicy: AdvertisementPolicy
    ): MovieViewDatas {
        return mutableListOf<MovieListViewData>().apply {
            for (index in movie.indices) {
                if (index > 0 && index % advertisementPolicy.movieCount == 0) add(advertisement[0].toView())
                add(movie[index].toView())
            }
        }.let {
            MovieViewDatas(it)
        }
    }
}
