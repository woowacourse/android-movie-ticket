package woowacourse.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MoviesAdapter(
    private val context: Context,
    private val movies: List<Movie>,
) : BaseAdapter() {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        @SuppressLint("ViewHolder")
        val view: View =
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_movie, parent, false)

        val title = view.findViewById<TextView>(R.id.tv_movie_title)
        val poster = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val date = view.findViewById<TextView>(R.id.tv_movie_date)
        val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)

        val movie = movies[position]

        title.text = movie.title
        poster.setImageResource(movie.poster)
        date.text = movie.date.toString()
        runningTime.text = movie.runningTime.toString()

        return view
    }

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = movies.size
}
