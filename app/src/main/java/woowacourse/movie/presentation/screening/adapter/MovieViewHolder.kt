package woowacourse.movie.presentation.screening.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel

class AdViewHolder(view: View, onClickAd: () -> Unit) : RecyclerView.ViewHolder(view) {
    val adView: ImageView =
        view.findViewById<ImageView?>(R.id.iv_ad).apply {
            setOnClickListener { onClickAd() }
        }
}

class MovieViewHolder(
    view: View,
    private val onClickReservationButton: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val postView: ImageView = view.findViewById(R.id.iv_movie_post)
    private val titleView: TextView = view.findViewById(R.id.tv_movie_title)
    private val dateView: TextView = view.findViewById(R.id.tv_movie_running_date)
    private val runningTimeView: TextView = view.findViewById(R.id.tv_movie_running_time)
    private val reservationButton: Button = view.findViewById(R.id.btn_movie_reservation)

    init {
        reservationButton.setOnClickListener { clickReservation() }
    }

    fun bind(movie: ScreeningMovieUiModel) {
        val (_, title, imageRes, screenDate, runningTime) = movie
        postView.setImageResource(imageRes)
        titleView.text = title
        dateView.text = screenDate
        runningTimeView.text = runningTime
    }

    private fun clickReservation() {
        onClickReservationButton(absoluteAdapterPosition)
    }
}
