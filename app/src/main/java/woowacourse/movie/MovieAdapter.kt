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
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(p0: Int): Movie = movies[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

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
        screeningDate.text = movie.screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        runningTime.text = "${movie.runningTime}분"

        reserveButton.setOnClickListener {
            val intent = Intent(context, MovieReservationCompletionActivity::class.java)
            intent.putExtra("movie", movie)
            context.startActivity(intent)
        }

        return view
    }
}
