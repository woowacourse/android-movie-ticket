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
    private val mainActivityPresenter: MovieListPresenter
) : ArrayAdapter<Theater>(context, 0, theaters) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.movie_list_item, parent, false)
        val theater: Theater? = getItem(position)

        listItemView.findViewById<TextView>(R.id.movie_title)?.text =
            theater?.movie?.title.toString()
        listItemView.findViewById<Button>(R.id.movie_details_button)?.setOnClickListener {
            theater?.let { mainActivityPresenter.onDetailButtonClicked(it) }
        }
        return listItemView
    }
}
