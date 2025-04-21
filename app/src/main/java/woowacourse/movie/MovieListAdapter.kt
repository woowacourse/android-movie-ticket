package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie


class MovieListAdapter(
    private val value: List<Movie>,
    private val movieListClick: MovieListClick,
) : BaseAdapter() {
    override fun getCount(): Int {
        return value.size
    }

    override fun getItem(position: Int): Movie {
        return value[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val movie = getItem(position)
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        } else {
            view = convertView
        }

        val viewHolder = ViewHolder(view)

        adaptMovie(viewHolder, movie)

        clickMovieItem(viewHolder, position)

        return view
    }

    private fun adaptMovie(
        viewHolder: ViewHolder,
        movie: Movie
    ) {
        viewHolder.title.text = movie.title
        viewHolder.poster.setImageResource(movie.poster)
        viewHolder.screeningDate.text = viewHolder.screeningDate.context.getString(
            R.string.movie_screening_date,
            movie.screeningPeriod.screeningStartDate.toDotFormat(),
            movie.screeningPeriod.screeningEndDate.toDotFormat()
        )
        viewHolder.runningTime.text = viewHolder.runningTime.context.getString(
            R.string.movie_running_time,
            movie.runningTime
        )
    }

    private fun clickMovieItem(viewHolder: ViewHolder, position: Int) {
        viewHolder.movieItem.setOnClickListener {
            if (viewHolder.movieItem.isClickable) {
                movieListClick.navigateToBook(value[position])
                viewHolder.movieItem.isClickable = false
            }

            viewHolder.movieItem.postDelayed({
                viewHolder.movieItem.isClickable = true
            }, 2000)

            return@setOnClickListener
        }
    }
}

private class ViewHolder(view: View) {
    val movieItem: View = view.findViewById(R.id.movie_item_root)
    val title: TextView = view.findViewById(R.id.movie_title)
    val poster: ImageView = view.findViewById(R.id.movie_poster)
    val screeningDate: TextView = view.findViewById(R.id.movie_date)
    val runningTime: TextView = view.findViewById(R.id.movie_running_time)
}
