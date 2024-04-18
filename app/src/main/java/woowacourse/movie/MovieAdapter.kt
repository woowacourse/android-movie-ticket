package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.MovieItem

class MovieAdapter(context: Context, private val movies: List<MovieItem>) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieItem = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view = convertView ?: inflater.inflate(R.layout.item_movie, null)

        val movie = getItem(position)
        val postImageView = view.findViewById<ImageView>(R.id.iv_movie_post)
        val title = view.findViewById<TextView>(R.id.tv_movie_title)
        val date = view.findViewById<TextView>(R.id.tv_movie_running_date)
        val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)

        postImageView.setImageResource(movie.imageRes)
        title.text = movie.title
        date.text = movie.screenDate
        runningTime.text = movie.runningTime

        view.findViewById<Button>(R.id.btn_movie_reservation).setOnClickListener {
            view.context.startActivity(Intent(view.context, DetailMovieActivity::class.java))
        }

        return view
    }
}
