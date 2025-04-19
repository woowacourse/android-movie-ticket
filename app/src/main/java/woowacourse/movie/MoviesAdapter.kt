package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MoviesAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val onBookingClick: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View

        when (convertView) {
            null -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
                movieViewHolder = MovieViewHolder(view)
                view.tag = movieViewHolder
            }

            else -> {
                view = convertView
                movieViewHolder = convertView.tag as MovieViewHolder
            }
        }

        val movie = movies[position]

        movieViewHolder.title.text = movie.title
        movieViewHolder.poster.setImageResource(movie.poster)
        movieViewHolder.date.text = context.getString(R.string.movies_movie_date_with_tilde, movie.startDate, movie.endDate)
        movieViewHolder.runningTime.text = context.getString(R.string.movies_movie_running_time, movie.runningTime)
        movieViewHolder.bookingButton.setOnClickListener {
            onBookingClick(movie)
        }

        return view
    }

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = movies.size

    private class MovieViewHolder(
        view: View,
    ) {
        val title: TextView = view.findViewById(R.id.tv_movie_title)
        val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
        val date: TextView = view.findViewById(R.id.tv_movie_date)
        val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
        val bookingButton: Button = view.findViewById(R.id.btn_movie_booking)
    }
}
