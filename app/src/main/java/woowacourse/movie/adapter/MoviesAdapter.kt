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
            movieItemViewHolder = MovieItemViewHolder(
                moviePosterImageView = view.findViewById(R.id.movie_poster_image_view),
                movieNameTextView = view.findViewById(R.id.movie_name_text_view),
                screeningDateTextView = view.findViewById(R.id.movie_screening_period_text_view),
                runningTimeTextView = view.findViewById(R.id.movie_running_time_text_view),
            )
            view.tag = movieItemViewHolder
        } else {
            view = convertView
            movieItemViewHolder = view.tag as MovieItemViewHolder
        }

        movieItemViewHolder.setViewContents(context, movieModel)
        return view
    }
}
