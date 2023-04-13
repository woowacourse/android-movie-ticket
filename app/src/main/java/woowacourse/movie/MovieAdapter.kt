package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies

class MovieAdapter(private val context: Context, private val movies: Movies) : BaseAdapter() {
    override fun getCount(): Int = movies.value.size

    override fun getItem(position: Int): Any = movies.value[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, null)
        MovieController(
            context,
            movies.value[position],
            view.findViewById(R.id.item_movie_poster),
            view.findViewById(R.id.item_movie_title),
            view.findViewById(R.id.item_movie_date),
            view.findViewById(R.id.item_movie_running_time),
            null
        ).render()

        view.findViewById<Button>(R.id.item_movie_reservation_button)
            .setOnClickListener { reserveMovie(movies.value[position]) }

        return view
    }

    private fun reserveMovie(movie: Movie) {
        val intent = Intent(context, MovieReservationActivity::class.java)
        intent.putExtra(context.getString(R.string.movie_extra_name), movie)
        context.startActivity(intent)
    }
}
