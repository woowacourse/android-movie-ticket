package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movies
import java.text.SimpleDateFormat
import java.util.Locale

class MovieAdapter(private val context: Context, private val movies: Movies) : BaseAdapter() {
    override fun getCount(): Int = movies.value.size

    override fun getItem(position: Int): Any = movies.value[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, null)
        view.findViewById<ImageView>(R.id.item_movie_poster).setImageResource(movies.value[position].picture)
        view.findViewById<TextView>(R.id.item_movie_title).text = movies.value[position].title

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        view.findViewById<TextView>(R.id.item_movie_date).text = "상영일: %s".format(dateFormat.format(movies.value[position].date))

        view.findViewById<TextView>(R.id.item_movie_running_time).text = "러닝타임: %d분".format(movies.value[position].runningTime)
        return view
    }
}
