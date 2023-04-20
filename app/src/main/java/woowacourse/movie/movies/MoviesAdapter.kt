package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.model.MovieInfo

class MoviesAdapter(
    private val movies: List<MovieInfo>,
    private val onReservationButtonClicked: (movie: MovieInfo) -> Unit
) : BaseAdapter() {

    private val viewHolderMap = mutableMapOf<View, MovieItemViewHolder>()

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieInfo = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie: MovieInfo = movies[position]
        lateinit var view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
            val movieItemViewHolder = MovieItemViewHolder(view)
            viewHolderMap[view] = movieItemViewHolder
        } else {
            view = convertView
        }
        viewHolderMap[view]?.setView(movie, onReservationButtonClicked)

        return view
    }
}
