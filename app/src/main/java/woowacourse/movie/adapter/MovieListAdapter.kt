package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.model.Movie
import woowacourse.movie.util.Formatter

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
        if (convertView == null) view.tag = getViewHolder(view)

        val holder = view.tag as ViewHolder
        val movie = getItem(position) as Movie
        setViewHolder(holder, movie)
        return view
    }
    private fun getViewHolder(view: View): ViewHolder {
        val holder = ViewHolder()
        holder.image = view.findViewById(R.id.img_movie)
        holder.title = view.findViewById(R.id.text_title)
        holder.playingDate = view.findViewById(R.id.text_playing_date)
        holder.runningTime = view.findViewById(R.id.text_running_time)
        holder.ticketingButton = view.findViewById(R.id.btn_ticketing)

        return holder
    }

    private fun setViewHolder(holder: ViewHolder, movie: Movie) {
        holder.image?.setImageResource(movie.image)
        holder.title?.text = movie.title
        holder.playingDate?.text = context.getString(
            R.string.playing_time, Formatter.dateFormat(movie.playingTimes.startDate),
            Formatter.dateFormat(movie.playingTimes.endDate)
        )
        holder.runningTime?.text = context.getString(R.string.running_time, movie.runningTime)
        holder.ticketingButton?.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            context.startActivity(intent)
        }
    }

    private class ViewHolder {
        var image: ImageView? = null
        var title: TextView? = null
        var playingDate: TextView? = null
        var runningTime: TextView? = null
        var ticketingButton: Button? = null
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
