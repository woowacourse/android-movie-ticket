package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import domain.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity
import java.time.format.DateTimeFormatter

class MoviesAdapter(private val movies: List<Movie>) : BaseAdapter() {

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: Movie = movies[position]
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)

        initReservationClickEvent(movie, view)
        initView(movie, view)

        return view
    }

    private fun initReservationClickEvent(movie: Movie, view: View) {
        val reservationButton: Button = view.findViewById(R.id.reservation_button)

        reservationButton.setOnClickListener {
            val intent = Intent(view.context, ReservationActivity::class.java)
            intent.putExtra(view.context.getString(R.string.movie_key), movie)
            view.context.startActivity(intent)
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
