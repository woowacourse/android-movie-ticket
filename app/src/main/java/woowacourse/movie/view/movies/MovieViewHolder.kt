package woowacourse.movie.view.movies

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.view.mapper.Formatter.localDateToUI

class MovieViewHolder(
    view: View,
    private val context: Context,
    private val movieClickListener: MovieClickListener,
) : RecyclerView.ViewHolder(view) {
    private val titleTextView: TextView by lazy { view.findViewById(R.id.tv_movie_title) }
    private val posterImageView: ImageView by lazy { view.findViewById(R.id.iv_movie_poster) }
    private val screeningDateTextView: TextView by lazy { view.findViewById(R.id.tv_movie_screening_date) }
    private val runningTimeTextView: TextView by lazy { view.findViewById(R.id.tv_movie_running_time) }
    val button: Button by lazy { view.findViewById(R.id.btn_movie_reservation) }

    fun bind(item: Movie) {
        setupTitle(item)
        setupPoster(item)
        setupScreeningDate(item)
        setupRunningTime(item)
        setupButtonClick(item)
    }

    private fun setupTitle(item: Movie) {
        titleTextView.text = item.title
    }

    private fun setupPoster(item: Movie) {
        val poster = AppCompatResources.getDrawable(context, item.poster)
        posterImageView.setImageDrawable(poster)
        titleTextView.text = item.title
    }

    private fun setupScreeningDate(item: Movie) {
        val startDate = localDateToUI(item.startDate)
        val endDate = localDateToUI(item.endDate)
        screeningDateTextView.text =
            context.getString(R.string.movie_screening_date, startDate, endDate)
    }

    private fun setupRunningTime(item: Movie) {
        val runningTime: Int = item.runningTime
        runningTimeTextView.text =
            context.getString(R.string.movie_running_time, runningTime)
    }

    private fun setupButtonClick(item: Movie) {
        button.setOnClickListener {
            movieClickListener.onReservationClick(item.id)
        }
    }
}
