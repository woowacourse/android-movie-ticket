package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.viewholder.MovieItemViewHolder

class MoviesAdapter(
    private val movieModels: List<MovieModel>
) : RecyclerView.Adapter<MovieItemViewHolder>() {

    override fun getItemCount(): Int = movieModels.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val context: Context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)

        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movieModels[position])
    }
}
