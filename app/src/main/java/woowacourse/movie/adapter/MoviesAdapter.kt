package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.viewholder.MovieItemViewHolder

class MoviesAdapter(
    private val movieModels: List<MovieModel>
) : BaseAdapter() {

    override fun getCount(): Int = movieModels.size

    override fun getItem(position: Int): MovieModel = movieModels[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context: Context? = parent?.context
        val movieModel: MovieModel = movieModels[position]

        val movieItemViewHolder: MovieItemViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, null)
            movieItemViewHolder = MovieItemViewHolder(view)
            view.tag = movieItemViewHolder
        } else {
            view = convertView
            movieItemViewHolder = view.tag as MovieItemViewHolder
        }

        movieItemViewHolder.setViewContents(movieModel)
        return view
    }
}
