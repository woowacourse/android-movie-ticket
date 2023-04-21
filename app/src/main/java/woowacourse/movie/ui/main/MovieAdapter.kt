package woowacourse.movie.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieState

class MovieAdapter(
    movie: List<MovieState>,
    var clickListener: ReservationClickListener? = null
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val _movie: List<MovieState> = movie.toList()
    val movie: List<MovieState>
        get() = _movie.toList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)

        return MovieViewHolder(itemView) { clickListener?.onClick(it) }
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bind(_movie[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = _movie.size

    interface ReservationClickListener {
        fun onClick(position: Int)
    }
}
