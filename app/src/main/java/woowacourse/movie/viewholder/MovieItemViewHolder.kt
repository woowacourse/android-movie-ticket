package woowacourse.movie.viewholder

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModel
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

    fun setViewContents(context: Context?, movieModel: MovieModel) {
        setPosterResource(movieModel)
        setMovieNameText(movieModel)
        setScreeningDateText(context, movieModel)
        setRunningTimeText(context, movieModel)
    }

    private fun setPosterResource(movieModel: MovieModel) {
        movieModel.posterImage?.let { moviePosterImageView.setImageResource(it) }
    }

    private fun setMovieNameText(movieModel: MovieModel) {
        movieNameTextView.text = movieModel.name.value
    }

    private fun setScreeningDateText(context: Context?, movieModel: MovieModel) {
        screeningDateTextView.text =
            context?.getString(R.string.screening_period_form)?.format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeText(context: Context?, movieModel: MovieModel) {
        runningTimeTextView.text =
            context?.getString(R.string.running_time_form)
                ?.format(movieModel.runningTime)
    }
}
