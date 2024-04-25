package woowacourse.movie.presentation.screen.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieListAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    val movie: (Movie) -> Unit,
) : BaseAdapter() {
    private lateinit var view: View

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val viewHolder: ViewHolder
        val item = movies[position]

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        with(viewHolder) {
            image.setImageResource(item.img)
            title.text = item.title
            screenDate.text = item.screenDateToString()
            runningTime.text = item.runningTime.toString()
            reservationButton.setOnClickListener { movie(item) }
        }

        return view
    }
}
