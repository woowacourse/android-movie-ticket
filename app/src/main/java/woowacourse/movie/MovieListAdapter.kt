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
import java.time.format.DateTimeFormatter

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
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, null)

        if (convertView == null) {
            val holder = ViewHolder()
            holder.image = view.findViewById(R.id.img_movie)
            holder.title = view.findViewById(R.id.text_title)
            holder.playingDate = view.findViewById(R.id.text_playing_date)
            holder.runningTime = view.findViewById(R.id.text_running_time)
            holder.ticketingButton = view.findViewById(R.id.btn_ticketing)

            view.tag = holder
        }

        val holder = view.tag as ViewHolder
        val movie = getItem(position) as Movie

        holder.image?.setImageResource(movie.image)
        holder.title?.text = movie.title
        val dateFormatter = DateTimeFormatter.ofPattern("YYYY.M.d")
        holder.playingDate?.text = context.getString(R.string.playing_time).format(dateFormatter.format(movie.playingTimes.startDate), dateFormatter.format(movie.playingTimes.endDate))
        holder.runningTime?.text = context.getString(R.string.running_time).format(movie.runningTime)

        holder.ticketingButton?.setOnClickListener {
            val intent = Intent(context, TicketingActivity::class.java)
            intent.putExtra("movie", movie)
            context.startActivity(intent)
        }

        return view
    }

    private class ViewHolder {
        var image: ImageView? = null
        var title: TextView? = null
        var playingDate: TextView? = null
        var runningTime: TextView? = null
        var ticketingButton: Button? = null
    }
}
