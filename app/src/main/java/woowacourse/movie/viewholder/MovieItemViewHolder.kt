package woowacourse.movie.viewholder

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import domain.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(
    val moviePosterImageView: ImageView,
    val movieNameTextView: TextView,
    val screeningDateTextView: TextView,
    val runningTimeTextView: TextView,
) {
    /*
        이 모델은 도메인모델이 아니라 뷰와 관련된 로직을 담당하는 모델인 것 같습니다.
        그러므로 뷰를 초기화하는 로직을 여기에 사용하여도 될까요?
     */

    private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun setViewContents(context: Context?, movie: Movie) {
        setPosterResource(movie)
        setMovieNameText(movie)
        setScreeningDateText(context, movie)
        setRunningTimeText(context, movie)
    }

    private fun setPosterResource(movie: Movie) {
        movie.posterImage?.let { moviePosterImageView.setImageResource(it) }
    }

    private fun setMovieNameText(movie: Movie) {
        movieNameTextView.text = movie.name.value
    }

    private fun setScreeningDateText(context: Context?, movie: Movie) {
        screeningDateTextView.text =
            context?.getString(R.string.screening_period_form)?.format(
                movie.screeningPeriod.startDate.format(dateFormat),
                movie.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeText(context: Context?, movie: Movie) {
        runningTimeTextView.text =
            context?.getString(R.string.running_time_form)
                ?.format(movie.runningTime)
    }
}
