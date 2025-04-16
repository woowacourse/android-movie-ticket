package woowacourse.movie.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val eventListener: OnMovieEventListener
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.item_movie, parent, false)

        val movie = getItem(position)

        setMoviePoster(view, movie)
        setMovieInfo(view, movie)
        setReservationButton(view, movie)
        return view
    }

    private fun setMoviePoster(view: View, movie: Movie) {
        val ivPoster = view.findViewById<ImageView>(R.id.iv_poster)
        ivPoster.setImageResource(movie.posterResId)
    }

    private fun setMovieInfo(view: View, movie: Movie) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        tvTitle.text = movie.title
        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        tvDate.text = movie.date
        val tvRunningTime = view.findViewById<TextView>(R.id.tv_running_time)
        tvRunningTime.text = movie.runningTime
    }

    private fun setReservationButton(view: View, movie: Movie) {
        val btnReservation = view.findViewById<Button>(R.id.btn_reservation)
        btnReservation.setOnClickListener {
            eventListener.onClick(movie)
        }
    }

}