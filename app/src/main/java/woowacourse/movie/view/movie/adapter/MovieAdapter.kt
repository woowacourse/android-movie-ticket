package woowacourse.movie.view.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.movie.viewholder.MovieViewHolder

class MovieAdapter(
    private val onClickBooking: (Int) -> Unit,
    private val items: List<Movie>,
) : AbstractListViewAdapter<Movie, MovieViewHolder>() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

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
