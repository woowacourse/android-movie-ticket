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
        val startDate = movie.startDate.format(DATE_FORMAT)
        val endDate = movie.endDate.format(DATE_FORMAT)
        screeningDate.text = SCREENING_DATE_RANGE.format(startDate, endDate)
        runningTime.text = RUNNING_TIME.format(movie.runningTime)

        reserveButton.setOnClickListener {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            context.startActivity(intent)
        }

        return view
    }

    companion object {
        const val KEY_MOVIE = "movie"
        private const val SCREENING_DATE_RANGE = "%s ~ %S"
        private const val RUNNING_TIME = "%d분"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
