package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieListAdapter(private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_movie_list, parent, false)
        val movie = movies[position]

        setMovieData(view, movie)

        return view
    }

    private fun setMovieData(view: View, movie: Movie) {
        view.findViewById<ImageView>(R.id.iv_movie_poster).setImageResource(movie.poster)
        view.findViewById<TextView>(R.id.tv_movie_title).text = movie.title
        view.findViewById<TextView>(R.id.tv_movie_release_date).text = movie.releaseDate
        view.findViewById<TextView>(R.id.tv_movie_running_time).text = movie.runningTime
    }
}
