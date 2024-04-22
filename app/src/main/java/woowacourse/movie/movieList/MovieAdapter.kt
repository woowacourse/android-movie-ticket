package woowacourse.movie.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.theater.Theater

class MovieAdapter(
    context: Context,
    theaters: List<Theater>,
    private val presenter: MovieListPresenter
) : ArrayAdapter<Theater>(context, R.layout.movie_list_item, theaters) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.movie_list_item, parent, false)
        val movie = getItem(position) ?: return listItemView
        listItemView.findViewById<TextView>(R.id.movie_title)?.text =
            movie.movie.title.toString()
        listItemView.findViewById<TextView>(R.id.movie_release_date)?.text =
            movie.movie.releaseDate.toString()
        listItemView.findViewById<TextView>(R.id.movie_duration)?.text =
            movie.movie.runningTime.toString()

        listItemView.findViewById<Button>(R.id.movie_details_button)?.setOnClickListener {
            presenter.onDetailButtonClicked(position)
        }
        return listItemView
    }
}
