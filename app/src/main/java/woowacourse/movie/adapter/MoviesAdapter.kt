package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import domain.movie.Movie
import woowacourse.movie.R

class MoviesAdapter(
    private val movies: List<Movie>,
    private val reservationEvent: (movie: Movie) -> Unit
) : BaseAdapter() {

    private val movieItemViewInitializer = MovieItemViewInitializer()

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: Movie = movies[position]
        val view =
            convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)

        movieItemViewInitializer.init(movie, view)
        initReservationButtonClickListener(view, movie)

        return view
    }

    private fun initReservationButtonClickListener(view: View, movie: Movie) {
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        reservationButton.setOnClickListener {
            reservationEvent(movie)
        }
    }
}
