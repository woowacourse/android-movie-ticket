package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.databinding.ItemMovieBinding
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onBookBtnClick: (Movie) -> Unit = {},
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        val context = binding.root.context

        with(binding) {
            with(item) {
                ivPoster.setImageResource(thumbnail)
                tvTitle.text = title
                tvDate.text = context.getString(
                    R.string.movie_release_date,
                    startDate.formattedDate,
                    endDate.formattedDate
                )
                tvRunningTime.text =
                    context.getString(R.string.movie_running_time, runningTime)
                btnBook.setOnClickListener { onBookBtnClick(item) }
            }
        }
    }
}
