package woowacourse.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class HomeAdapter(
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MovieViewHolder>() {
    private val movieInfo: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_item,
            parent,
            false,
        )
        return MovieViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieInfo[position])
    }

    override fun getItemCount(): Int = movieInfo.size

    fun setData(items: List<Movie>) {
        movieInfo.clear()
        movieInfo.addAll(items)
        notifyDataSetChanged()
    }
}
