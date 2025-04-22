package woowacourse.movie.view.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val movieClickListener: MovieClickListener,
) : BaseAdapter() {
    private lateinit var movieViewHolder: MovieViewHolder

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view, context, movieClickListener)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }
        movieViewHolder.bind(movies[position])

        return view
    }
}
