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
    private val onReserveListener: OnReserveListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnReserveListener {
        fun onClick(movie: MovieUiModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.movie_item -> MovieItemViewHolder(MovieItemBinding.bind(view))
            R.layout.movie_ad_item -> MovieAdViewHolder(MovieAdItemBinding.bind(view))
            else -> EmptyViewHolder(EmptyItemBinding.bind(view))
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(item as MovieUiModel, onReserveListener)
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
