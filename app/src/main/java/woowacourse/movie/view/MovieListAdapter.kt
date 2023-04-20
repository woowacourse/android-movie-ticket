package woowacourse.movie.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.EmptyItemBinding
import woowacourse.movie.databinding.MovieAdItemBinding
import woowacourse.movie.databinding.MovieItemBinding
import woowacourse.movie.util.DATE_FORMATTER
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
                bindMovieItemViewHolder(holder.binding.root.context, holder, item as MovieUiModel)
            }
            is MovieAdViewHolder -> {
                holder.banner.setImageResource((item as MovieAdModel).banner)
            }
        }
    }

    private fun bindMovieItemViewHolder(
        context: Context,
        viewHolder: MovieItemViewHolder,
        movie: MovieUiModel
    ): MovieItemViewHolder {
        return viewHolder.apply {
            poster.setImageResource(movie.posterResourceId)
            title.text = movie.title
            screeningStartDate.text =
                context.resources.getString(R.string.screening_date_format).format(
                    movie.screeningStartDate.format(DATE_FORMATTER),
                    movie.screeningEndDate.format(DATE_FORMATTER)
                )
            runningTime.text = context.resources.getString(R.string.running_time_format)
                .format(movie.runningTime)
            reserveNow.setOnClickListener {
                onReserveListener.onClick(movie)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (dataList[position] is MovieAdModel) {
            return R.layout.movie_ad_item
        }
        return R.layout.movie_item
    }
}
