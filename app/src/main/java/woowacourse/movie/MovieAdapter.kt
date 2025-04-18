package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        container: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_movie, container, false)

        val poster = view.findViewById<ImageView>(R.id.poster)
        val title = view.findViewById<TextView>(R.id.movie_title)
        val screeningDate = view.findViewById<TextView>(R.id.screening_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)
        val reserveButton = view.findViewById<Button>(R.id.reserve_button)

        val movie = getItem(position)
        poster.setImageResource(movie.poster)
        title.text = movie.title

        val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.date_format_pattern))
        val startDate = movie.startDate.format(formatter)
        val endDate = movie.endDate.format(formatter)

        screeningDate.text = context.getString(R.string.screening_date_range_template, startDate, endDate)
        runningTime.text = context.getString(R.string.running_time_template, movie.runningTime)

        reserveButton.setOnClickListener {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            context.startActivity(intent)
        }

        return view
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}
