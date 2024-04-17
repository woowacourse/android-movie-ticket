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
import java.io.Serializable

class MovieListAdapter(
    private val context: Context,
    private val movieList: ArrayList<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.movie_item, null)

        val movie = movieList[position]

        val resourceId = context.resources.getIdentifier(movie.poster, "drawable", context.packageName)

        val title = view.findViewById<TextView>(R.id.movie_title)
        val poster = view.findViewById<ImageView>(R.id.movie_poster)
        val screeningDate = view.findViewById<TextView>(R.id.movie_screening_date)
        val runningTime = view.findViewById<TextView>(R.id.movie_running_date)

        title.text = movie.title
        poster.setImageResource(resourceId)
        screeningDate.text = "상영일: ${movie.screeningDate}"
        runningTime.text = "러닝타임: ${movie.runningTime}분"

        val movieReservationButton = view.findViewById<Button>(R.id.movie_reservation_button)

        movieReservationButton.setOnClickListener {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra("movie", getItem(position) as Serializable)
            context.startActivity(intent)
        }

        return view
    }
}
