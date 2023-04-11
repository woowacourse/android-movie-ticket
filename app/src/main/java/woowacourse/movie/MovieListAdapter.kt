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
import woowacourse.movie.domain.Movie

class MovieListAdapter(private val context: Context, private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, null)
        val movie = getItem(position) as Movie
        val image = view.findViewById<ImageView>(R.id.img_movie)
        val title = view.findViewById<TextView>(R.id.text_title)
        val playingDate = view.findViewById<TextView>(R.id.text_playing_date)
        val runningTime = view.findViewById<TextView>(R.id.text_running_time)
        val ticketingButton = view.findViewById<Button>(R.id.btn_ticketing)

        image.setImageResource(movie.image)
        title.text = movie.title
        playingDate.text = context.getString(R.string.playing_time).format(movie.playingDate)
        runningTime.text = context.getString(R.string.playing_time).format(movie.runningTime)

        ticketingButton.setOnClickListener {
            val intent = Intent(context, TicketingActivity::class.java)
            intent.putExtra("movie", movie)
            context.startActivity(intent)
        }

        return view
    }
}
