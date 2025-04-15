package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(
    movies: List<Movie>,
) : BaseAdapter() {
    private val movies: List<Movie> = movies.toList()

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        val movie = movies[position]
        prepareView(view, movie)

        return view
    }

    private fun prepareView(
        view: View,
        movie: Movie,
    ) {
        with(movie) {
            val titleView = view.findViewById<TextView>(R.id.tv_item_movie_title)
            titleView.text = title
            val screeningDateView = view.findViewById<TextView>(R.id.tv_item_movie_screening_date)
            screeningDateView.text =
                view.context.getString(
                    R.string.item_movie_screening_date,
                    screeningYear,
                    screeningMonth,
                    screeningDay,
                )
            val runningTimeView = view.findViewById<TextView>(R.id.tv_item_movie_running_time)
            runningTimeView.text =
                view.context.getString(R.string.item_movie_running_time, runningTime)
            val posterView = view.findViewById<ImageView>(R.id.iv_item_movie_poster)
            posterView.setImageResource(posterId)
        }
    }
}
