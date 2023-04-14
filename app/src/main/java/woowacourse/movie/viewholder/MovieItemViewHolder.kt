package woowacourse.movie.viewholder

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import domain.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(
    val moviePosterImageView: ImageView,
    val movieNameTextView: TextView,
    val screeningDateTextView: TextView,
    val runningTimeTextView: TextView,
    val reservationButton: Button
) {
    /*
        이 모델은 도메인모델이 아니라 뷰와 관련된 로직을 담당하는 모델인 것 같습니다.
        그러므로 뷰를 초기화하는 로직을 여기에 사용하여도 될까요?
     */

    private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun setPosterResource(movie: Movie) {
        movie.posterImage?.let { moviePosterImageView.setImageResource(it) }
    }

    fun setMovieNameText(movie: Movie) {
        movieNameTextView.text = movie.name.value
    }

    fun setScreeningDateText(context: Context?, movie: Movie) {
        screeningDateTextView.text =
            context?.getString(R.string.screening_period_form)?.format(
                movie.screeningPeriod.startDate.format(dateFormat),
                movie.screeningPeriod.endDate.format(dateFormat)
            )
    }

    fun setRunningTimeText(context: Context?, movie: Movie) {
        runningTimeTextView.text =
            context?.getString(R.string.running_time_form)
                ?.format(movie.runningTime)
    }

    fun setReservationButton(context: Context?, movie: Movie) {
        reservationButton.setOnClickListener {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(context?.getString(R.string.movie_key), movie)
            context?.startActivity(intent)
        }
    }
}
