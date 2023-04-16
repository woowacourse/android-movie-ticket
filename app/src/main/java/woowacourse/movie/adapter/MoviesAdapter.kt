package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import domain.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MoviesAdapter(
    private val movies: List<Movie>,
    private val reservationEvent: (movie: Movie) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: Movie = movies[position]
        lateinit var view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
            val movieItemViewHolder = MovieItemViewHolder(
                movieNameTextView = view.findViewById(R.id.movie_name_text_view),
                dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM),
                movieImageView = view.findViewById(R.id.movie_image_view),
                screeningDateTextView = view.findViewById(R.id.movie_screening_date_text_view),
                runningTimeTextView = view.findViewById(R.id.movie_running_time_text_view)
            )
            view.tag = movieItemViewHolder
        } else {
            view = convertView
        }

        initMovieItemView(movie, view.context, view.tag as MovieItemViewHolder)
        initReservationButtonClickListener(view, movie)

        return view
    }

    private fun initMovieItemView(movie: Movie, context: Context, viewHolder: MovieItemViewHolder) {
        viewHolder.movieNameTextView.text = movie.name.value
        movie.posterImage?.let { viewHolder.movieImageView.setImageResource(it) }
        viewHolder.screeningDateTextView.text = context
            .getString(R.string.screening_period_form)
            .format(
                movie.screeningPeriod.startDate.value.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                ),
                movie.screeningPeriod.endDate.value.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                )
            )
        viewHolder.runningTimeTextView.text = context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
    }

    private fun initReservationButtonClickListener(view: View, movie: Movie) {
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        reservationButton.setOnClickListener {
            reservationEvent(movie)
        }
    }
}
