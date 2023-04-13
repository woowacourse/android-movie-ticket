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

class MovieListViewAdapter(private val context: Context, private val movies: List<Movie>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.movie_item, parent, false)

        val moviePoster = view.findViewById<ImageView>(R.id.movie_poster)
        val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        val screeningStartDate = view.findViewById<TextView>(R.id.screening_start_date)
        val screeningEndDate = view.findViewById<TextView>(R.id.screening_end_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)
        val bookButton = view.findViewById<Button>(R.id.book_button)
        val item: Movie = movies[position]

        moviePoster.setImageResource(item.moviePoster)
        movieTitle.text = item.title
        screeningStartDate.text = item.runningDate.startDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        screeningEndDate.text = item.runningDate.endDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        runningTime.text = item.runningTime.toString()

        bookButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", item)
            context.startActivity(intent)
        }

        return view
    }
}
