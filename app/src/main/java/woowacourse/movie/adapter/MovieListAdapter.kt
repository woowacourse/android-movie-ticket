package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ReservationActivity
import woowacourse.movie.model.Movie

class MovieListAdapter(
    val context: Context,
    val movies: ArrayList<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Movie {
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
        val view: View = LayoutInflater.from(context).inflate(R.layout.movie_item, null)

        val movieTitle = view.findViewById<TextView>(R.id.title)
        val movieScreenDate = view.findViewById<TextView>(R.id.screen_date)
        val movieRunningTime = view.findViewById<TextView>(R.id.running_time)
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        movieTitle.text = getItem(position).title
        movieScreenDate.text = getItem(position).screenDateToString()
        movieRunningTime.text = getItem(position).runningTime.toString()

        reservationButton.setOnClickListener {
            buttonClickListener(getItem(position))
        }

        return view
    }

    fun buttonClickListener(movie: Movie) {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("screenDate", movie.screenDateToString())
        intent.putExtra("runningTime", movie.runningTime.toString())
        intent.putExtra("description", movie.description)
        intent.putExtra("image", movie.img)

        context.startActivity(intent)
    }
}
