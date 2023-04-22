package woowacourse.movie.viewholder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.activity.movieinformation.ReservationActivity
import woowacourse.movie.uimodel.MovieModel
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val moviePosterImageView: ImageView = view.findViewById(R.id.movie_poster_image_view)
    private val movieNameTextView: TextView = view.findViewById(R.id.movie_name_text_view)
    private val screeningDateTextView: TextView =
        view.findViewById(R.id.movie_screening_period_text_view)
    private val runningTimeTextView: TextView = view.findViewById(R.id.movie_running_time_text_view)
    private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun bind(movieModel: MovieModel) {
        setItemClickListener(movieModel)

        setPosterResource(movieModel)
        setMovieNameText(movieModel)
        setScreeningDateText(movieModel)
        setRunningTimeText(movieModel)
    }

    private fun setItemClickListener(movieModel: MovieModel) {
        itemView.setOnClickListener {
            val intent = Intent(view.context, ReservationActivity::class.java)
            intent.putExtra(MovieModel.MOVIE_INTENT_KEY, movieModel)
            view.context.startActivity(intent)
        }
    }

    private fun setPosterResource(movieModel: MovieModel) {
        movieModel.posterImage?.let { moviePosterImageView.setImageResource(it) }
    }

    private fun setMovieNameText(movieModel: MovieModel) {
        movieNameTextView.text = movieModel.name.value
    }

    private fun setScreeningDateText(movieModel: MovieModel) {
        screeningDateTextView.text =
            view.context?.getString(R.string.screening_period_form)?.format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeText(movieModel: MovieModel) {
        runningTimeTextView.text =
            view.context?.getString(R.string.running_time_form)
                ?.format(movieModel.runningTime)
    }
}
