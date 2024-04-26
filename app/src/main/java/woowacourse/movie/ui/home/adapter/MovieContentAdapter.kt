package woowacourse.movie.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.MovieContent

class MovieContentAdapter(
    private val movieContents: List<MovieContent>,
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_content, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieContents.size

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        holder.bind(movieContents[position])
    }
}
