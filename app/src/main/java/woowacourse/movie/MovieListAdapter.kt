package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class MovieListAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: View.inflate(parent?.context, R.layout.include_movie_list_item, null)

        val poster = view.findViewById<ImageView>(R.id.movie_poster)
        val title = view.findViewById<TextView>(R.id.movie_title)
        val releaseDate = view.findViewById<TextView>(R.id.movie_release_date)
        val runningTime = view.findViewById<TextView>(R.id.movie_running_time)
        val reservationButton = view.findViewById<Button>(R.id.movie_reservation_button)

        with(movieList[position]) {
            poster.setImageResource(this.poster)
            title.text = this.title
            releaseDate.text = this.releaseDate
            runningTime.text = this.runningTime
            reservationButton.setOnClickListener {
                val intent = Intent(context, MovieReservationActivity::class.java)
                intent.putExtra("movie", this)
                startActivity(context, intent, null)
            }
        }

        return view
    }
}
