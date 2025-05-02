package woowacourse.movie.view.movie.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.MovieUiModel
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    private val view: View,
    listener: ClickListener,
) : BaseViewHolder<MovieListItem.MovieItem>(view) {
    private lateinit var currentItem: MovieUiModel

    private val poster: ImageView = view.findViewById(R.id.poster)
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val screeningDate: TextView = view.findViewById(R.id.screening_date)
    private val runningTime: TextView = view.findViewById(R.id.running_time)
    private val reserveButton: Button = view.findViewById(R.id.reserve_button)

    init {
        reserveButton.setOnClickListener {
            listener.onClickMovie(currentItem)
        }
    }

    override fun bind(item: MovieListItem.MovieItem) {
        val movie = item.movie
        currentItem = movie
        poster.setImageResource(movie.posterResId)
        title.text = movie.title
        screeningDate.text =
            view.context.getString(
                R.string.screening_date,
                movie.startDate.format(DATE_FORMAT),
                movie.endDate.format(DATE_FORMAT),
            )
        runningTime.text =
            view.context.getString(R.string.running_time, movie.runningTime)
    }

    interface ClickListener {
        fun onClickMovie(item: MovieUiModel)
    }

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
