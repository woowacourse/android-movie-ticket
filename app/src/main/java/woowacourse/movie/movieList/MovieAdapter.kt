package woowacourse.movie.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData

class MovieAdapter(
    context: Context,
    movieDisplayData: List<MovieDisplayData>,
    private val presenter: MovieListPresenter
) : ArrayAdapter<MovieDisplayData>(context, R.layout.movie_list_item, movieDisplayData) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.movie_list_item, parent, false)
        val movieData = getItem(position)

        listItemView.findViewById<TextView>(R.id.movie_title).text = movieData?.title
        listItemView.findViewById<TextView>(R.id.movie_release_date).text = movieData?.releaseDate
        listItemView.findViewById<TextView>(R.id.movie_duration).text = movieData?.duration

        listItemView.findViewById<Button>(R.id.movie_details_button).setOnClickListener {
            movieData?.let {
                presenter.onDetailButtonClicked(position)
            }
        }
        return listItemView
    }
}
