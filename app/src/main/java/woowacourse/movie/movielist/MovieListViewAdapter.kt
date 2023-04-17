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
import woowacourse.movie.dto.MovieDto
import java.time.format.DateTimeFormatter

class MovieListViewAdapter(
    private val movies: List<MovieDto>,
    private val listener: OnMovieClickListener,
) :
    BaseAdapter() {

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): MovieDto {
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
        val item: MovieDto = movies[position]

        parent?.let { setMovieData(it.context, holder, item) }

        return itemView
    }

    private fun setMovieData(context: Context, holder: MovieListViewHolder, item: MovieDto) {
        holder.moviePoster.setImageResource(item.moviePoster)
        holder.movieTitle.text = item.title
        holder.movieDate.text = formatMovieRunningDate(context, item)
        holder.runningTime.text =
            context.getString(R.string.movie_running_time).format(item.runningTime)
        holder.bookButton.setOnClickListener {
            listener.onMovieClick(item)
        }
    }

    private fun formatMovieRunningDate(context: Context, item: MovieDto): String {
        val startDate =
            item.startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        val endDate =
            item.endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
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
