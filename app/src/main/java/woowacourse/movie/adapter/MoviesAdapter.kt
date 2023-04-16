package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import woowacourse.movie.R
import woowacourse.movie.model.ActivityMovieModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MoviesAdapter(
    private val movies: List<ActivityMovieModel>,
    private val reservationEvent: (movie: ActivityMovieModel) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): ActivityMovieModel = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: ActivityMovieModel = movies[position]
        lateinit var view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
            val movieItemViewHolder = MovieItemViewHolder(
                movieMovieNameTextView = view.findViewById(R.id.movie_name_text_view),
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

    private fun initMovieItemView(
        movie: ActivityMovieModel,
        context: Context,
        viewHolder: MovieItemViewHolder
    ) {
        viewHolder.movieMovieNameTextView.text = movie.movieName
        movie.posterImage?.let { viewHolder.movieImageView.setImageResource(it) }
        viewHolder.screeningDateTextView.text = context
            .getString(R.string.screening_period_form)
            .format(
                movie.startDate.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                ),
                movie.endDate.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                )
            )
        viewHolder.runningTimeTextView.text = context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
    }

    private fun initReservationButtonClickListener(view: View, movie: ActivityMovieModel) {
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        reservationButton.setOnClickListener {
            reservationEvent(movie)
        }
    }
}
