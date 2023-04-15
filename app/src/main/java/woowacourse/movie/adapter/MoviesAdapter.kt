package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import domain.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

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
        val view =
            convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)

        initView(movie, view)
        initReservationButtonClickListener(view, movie)

        return view
    }

    private fun initReservationButtonClickListener(view: View, movie: Movie) {
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        reservationButton.setOnClickListener {
            reservationEvent(movie)
        }
    }

    private fun initView(movie: Movie, view: View) {
        val movieNameTextView: TextView = view.findViewById(R.id.movie_name_text_view)
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val movieImageView: ImageView = view.findViewById(R.id.movie_image_view)
        val screeningDateTextView: TextView = view.findViewById(R.id.movie_screening_date_text_view)
        val runningTimeTextView: TextView = view.findViewById(R.id.movie_running_time_text_view)

        movieNameTextView.text = movie.name.value
        movie.posterImage?.let { movieImageView.setImageResource(it) }
        screeningDateTextView.text = view.context
            .getString(R.string.screening_period_form)
            .format(
                movie.screeningPeriod.startDate.format(dateFormat),
                movie.screeningPeriod.endDate.format(dateFormat)
            )
        runningTimeTextView.text = view.context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
    }
}
