package woowacourse.movie.presentation.movieList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.movieList.MovieListClickListener
import woowacourse.movie.utils.formatMovieDate

class MovieAdapter(
    private var movies: List<Movie> = emptyList(),
    private val listener: MovieListClickListener,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.bind(movies[position])
        return view
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) {
        private val title = view.findViewById<TextView>(R.id.tv_title)
        private val date = view.findViewById<TextView>(R.id.tv_date)
        private val thumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)
        private val runningTime = view.findViewById<TextView>(R.id.tv_running_time)
        private val ticketingButton = view.findViewById<Button>(R.id.btn_ticketing)

        fun bind(movie: Movie) {
            title.text = movie.title
            date.text =
                date.context.getString(
                    R.string.title_date,
                    formatMovieDate(movie.screeningDates.startDate),
                    formatMovieDate(movie.screeningDates.endDate),
                )
            runningTime.text =
                runningTime.context.getString(R.string.title_running_time, movie.runningTime)
            ticketingButton.setOnClickListener { listener.ticketingButtonClick(movie.id) }
            thumbnail.setImageResource(movie.thumbnail)
        }
    }
}
