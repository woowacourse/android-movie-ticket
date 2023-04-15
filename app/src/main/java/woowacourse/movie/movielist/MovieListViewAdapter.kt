package woowacourse.movie.movielist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.moviedetail.MovieDetailActivity
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var itemView = convertView
        val holder: MovieListViewHolder

        if (itemView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            holder = MovieListViewHolder(itemView)
            itemView.tag = holder
        } else {
            holder = itemView.tag as MovieListViewHolder
        }
        val item: Movie = movies[position]

        setMovieData(holder, item)
        buttonSetOnclickListener(holder, item)

        return itemView
    }

    private fun setMovieData(holder: MovieListViewHolder, item: Movie) {
        holder.moviePoster.setImageResource(item.moviePoster)
        holder.movieTitle.text = item.title
        holder.screeningStartDate.text
        item.runningDate.startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        holder.screeningEndDate.text =
            item.runningDate.endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        holder.runningTime.text = item.runningTime.toString()
    }

    private fun buttonSetOnclickListener(holder: MovieListViewHolder, item: Movie) {
        holder.bookButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, item)
            context.startActivity(intent)
        }
    }

    private class MovieListViewHolder(itemView: View) {
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val screeningStartDate: TextView = itemView.findViewById(R.id.screening_start_date)
        val screeningEndDate: TextView = itemView.findViewById(R.id.screening_end_date)
        val runningTime: TextView = itemView.findViewById(R.id.running_time)
        val bookButton: Button = itemView.findViewById(R.id.book_button)
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
