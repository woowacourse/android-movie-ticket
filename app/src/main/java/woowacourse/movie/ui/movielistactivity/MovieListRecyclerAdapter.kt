package woowacourse.movie.ui.movielistactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel

class MovieListRecyclerAdapter(private val clickListener: (MovieUIModel) -> Unit) :
    ListAdapter<MovieUIModel, RecyclerView.ViewHolder>(movieListDiffUtil) {

    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        if ((position + 1) % 4 == 0) return MovieListItemType.ADVERTISE_ITEM.number
        return MovieListItemType.MOVIE_ITEM.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)

        return when (MovieListItemType.numberToMovieListItemType(viewType)) {
            MovieListItemType.MOVIE_ITEM -> {
                MovieViewHolder(
                    inflater.inflate(R.layout.movie_list_item, null),
                    ::getItem,
                    clickListener
                )
            }
            MovieListItemType.ADVERTISE_ITEM -> {
                AdvertiseViewHolder(inflater.inflate(R.layout.advertise_list_item, null))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) holder.setData(getItem(position))
    }

    companion object {
        private val movieListDiffUtil = object : DiffUtil.ItemCallback<MovieUIModel>() {
            override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean =
                oldItem == newItem
        }
    }

    enum class MovieListItemType(val number: Int) {
        MOVIE_ITEM(1),
        ADVERTISE_ITEM(2);

        companion object {
            fun numberToMovieListItemType(number: Int): MovieListItemType =
                MovieListItemType.values().find { it.number == number }
                    ?: throw IllegalStateException(WRONG_NUMBER_ERROR)

            private const val WRONG_NUMBER_ERROR = "타입의 값으로 들어온 숫자가 타입의 값중에 없습니다."
        }
    }
}
