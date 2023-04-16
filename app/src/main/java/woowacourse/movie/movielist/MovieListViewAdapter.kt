package woowacourse.movie.movielist

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.movieinfo.Movie
import java.time.format.DateTimeFormatter

class MovieListViewAdapter(private val context: Context, private val movies: List<Movie>, private val listener: OnMovieClickListener) :
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

        return itemView
    }

    private fun setMovieData(holder: MovieListViewHolder, item: Movie) {
        holder.moviePoster.setImageResource(item.moviePoster)
        holder.movieTitle.text = item.title
        holder.movieDate.text = formatMovieRunningDate(item)
        holder.runningTime.text = context.getString(R.string.movie_running_time).format(item.runningTime)
        holder.bookButton.setOnClickListener {
            listener.onMovieClick(item)
        }
    }

    private fun formatMovieRunningDate(item: Movie): String {
        val startDate = item.runningDate.startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        val endDate = item.runningDate.endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        return context.getString(R.string.movie_running_date, startDate, endDate)
    }

    private class MovieListViewHolder(itemView: View) {
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieDate: TextView = itemView.findViewById(R.id.movie_date)
        val runningTime: TextView = itemView.findViewById(R.id.running_time)
        val bookButton: Button = itemView.findViewById(R.id.book_button)
    }
}
