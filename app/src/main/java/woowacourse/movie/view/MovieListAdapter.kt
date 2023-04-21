package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.EmptyItemBinding
import woowacourse.movie.databinding.MovieAdItemBinding
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.view.model.MovieListModel
import woowacourse.movie.view.model.MovieListModel.MovieAdModel
import woowacourse.movie.view.model.MovieListModel.MovieUiModel

class MovieListAdapter(
    private val dataList: List<MovieListModel>,
    private val onItemClick: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClick {
        fun onMovieClick(movie: MovieUiModel)

        fun onAdClick(ad: MovieAdModel)
    }

    private val onMovieViewClick: (Int) -> Unit = {
        onItemClick.onMovieClick(dataList[it] as MovieUiModel)
    }

    private val onAdViewClick: (Int) -> Unit = {
        onItemClick.onAdClick(dataList[it] as MovieAdModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.movie_item -> MovieItemViewHolder(MovieItemBinding.bind(view)) {
                onMovieViewClick(it)
            }
            R.layout.movie_ad_item -> MovieAdViewHolder(MovieAdItemBinding.bind(view)) {
                onAdViewClick(it)
            }
            else -> EmptyViewHolder(EmptyItemBinding.bind(view))
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(item as MovieUiModel)
            }
            is MovieAdViewHolder -> {
                holder.bind(item as MovieAdModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (dataList[position]) {
        is MovieUiModel -> R.layout.movie_item
        is MovieAdModel -> R.layout.movie_ad_item
    }
}
