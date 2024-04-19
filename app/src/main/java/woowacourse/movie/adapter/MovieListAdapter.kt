package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.MovieReservationActivity
import java.io.Serializable
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val context: Context,
    private val movieList: ArrayList<Movie>,
) : BaseAdapter() {
    private lateinit var movieViewHolder: MovieViewHolder

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movie = movieList[position]
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, null)
            makeViewHolder(view)
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }
        setViewHolder(movie)
        setClickListener(movie)
        return view
    }

    private fun makeViewHolder(view: View) {
        movieViewHolder =
            MovieViewHolder(
                view.findViewById(R.id.movie_title),
                view.findViewById(R.id.movie_poster),
                view.findViewById(R.id.movie_screening_date),
                view.findViewById(R.id.movie_running_time),
                view.findViewById(R.id.movie_reservation_button),
            )
        view.tag = movieViewHolder
    }

    private fun setViewHolder(movie: Movie) {
        movieViewHolder.title.text = movie.title
        movieViewHolder.poster.setImageResource(movie.posterResourceId)

        val formattedScreeningDate =
            movie.screeningDate
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        movieViewHolder.screeningDate.text = formattedScreeningDate
        movieViewHolder.runningTime.text = movie.runningTime.toString()
    }

    private fun setClickListener(movie: Movie) {
        movieViewHolder.movieReservationButton.setOnClickListener {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra(Movie.KEY_NAME_MOVIE, movie as Serializable)
            context.startActivity(intent)
        }
    }
}
