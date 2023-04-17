package woowacourse.movie.view.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.movieinfo.Movie
import java.time.format.DateTimeFormatter

class MovieListViewAdapter(
    private val movies: List<Movie>,
    private val onBookListener: OnBookListener
) : BaseAdapter() {
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

        itemView?.let {
            setMovieData(holder, item, itemView.context)
        }

        return itemView
    }

    private fun setMovieData(holder: MovieListViewHolder, item: Movie, context: Context) {
        holder.moviePoster.setImageResource(item.moviePoster)
        holder.movieTitle.text = item.title
        val startDate =
            item.runningDate.startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        val endDate =
            item.runningDate.endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        holder.screeningDate.text = context.getString(R.string.screen_date, startDate, endDate)
        holder.runningTime.text = context.getString(R.string.running_time, item.runningTime)
        holder.bookBtn.setOnClickListener {
            onBookListener.onClick(item)
        }
    }

    interface OnBookListener {
        fun onClick(movie: Movie)
    }
}
