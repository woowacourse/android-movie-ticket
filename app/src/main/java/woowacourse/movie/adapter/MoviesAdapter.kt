package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import domain.Movie
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity

class MoviesAdapter(
    private val context: Context,
    private val movies: List<Movie>
) : BaseAdapter() {

    private val view: View by lazy { LayoutInflater.from(context).inflate(R.layout.item_movie, null) }
    private val movieImageView: ImageView by lazy { view.findViewById(R.id.movie_image_view) }
    private val screeningDateTextView: TextView by lazy { view.findViewById(R.id.movie_screening_date_text_view) }
    private val runningTimeTextView: TextView by lazy { view.findViewById(R.id.movie_running_time_text_view) }
    private val reservationButton: Button by lazy { view.findViewById(R.id.reservation_button) }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: Movie = movies[position]

        initReservationClickEvent(movie)
        initView(movie)

        return view
    }

    private fun initReservationClickEvent(movie: Movie) {
        reservationButton.setOnClickListener {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(context.getString(R.string.movie_key), movie)
            context.startActivity(intent)
        }
    }

    private fun initView(movie: Movie) {
        movie.posterImage?.let { movieImageView.setImageResource(it) }
        screeningDateTextView.text = context
            .getString(R.string.screening_date_form)
            .format(
                movie.screeningDate.year,
                movie.screeningDate.monthValue,
                movie.screeningDate.dayOfMonth
            )
        runningTimeTextView.text = context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
    }
}
