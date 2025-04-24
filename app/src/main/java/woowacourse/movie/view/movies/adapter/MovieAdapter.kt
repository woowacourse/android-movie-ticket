package woowacourse.movie.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import woowacourse.movie.R
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.movies.MovieListContract
import woowacourse.movie.view.movies.viewholder.MovieViewHolder

class MovieAdapter(
    private val onClickBooking: (Int) -> Unit,
    private val itemsList: MovieListContract.MovieModel,
) : AbstractListViewAdapter<Movie, MovieViewHolder>() {
    override fun getCount(): Int = itemsList.size()

    override fun getItem(position: Int): Movie = itemsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(inflater, onClickBooking)
    }

    override fun onBindViewHolder(
        viewHolder: MovieViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        viewHolder.bind(item, position)
    }
}
