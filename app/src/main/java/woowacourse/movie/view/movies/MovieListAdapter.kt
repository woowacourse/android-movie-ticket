package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.extension.toDateTimeFormatter

class MovieListAdapter(
    private val movies: List<Movie>,
    private val eventListener: OnMovieEventListener,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_movie, parent, false)

        val movie = getItem(position)

        setMoviePoster(view, movie)
        setMovieInfo(view, movie)
        setReservationButton(view, movie)
        return view
    }

    private fun setMoviePoster(
        view: View,
        movie: Movie,
    ) {
        val ivPoster = view.findViewById<ImageView>(R.id.iv_poster)
        movie.poster.toIntOrNull()?.let { ivPoster.setImageResource(it) }
    }

    private fun setMovieInfo(
        view: View,
        movie: Movie,
    ) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        tvTitle.text = movie.title

        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        tvDate.text =
            movie.screeningPeriod.run {
                view.context.getString(
                    R.string.movie_date,
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.startDate.format(formatter)
                    },
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.endDate.format(formatter)
                    },
                )
            }

        val tvRunningTime = view.findViewById<TextView>(R.id.tv_running_time)
        tvRunningTime.text =
            view.context.getString(R.string.running_time, movie.runningTime.toString())
    }

    private fun setReservationButton(
        view: View,
        movie: Movie,
    ) {
        val btnReservation = view.findViewById<Button>(R.id.btn_reservation)
        btnReservation.setOnClickListener {
            eventListener.onClick(movie)
        }
    }

    companion object {
        private const val MOVIE_SCREENING_PERIOD_FORMAT = "yyyy.M.d"
    }
}
