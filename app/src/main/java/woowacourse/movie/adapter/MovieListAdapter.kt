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

    private fun getViewHolder(view: View): ViewHolder = ViewHolder(
        view.findViewById(R.id.img_movie),
        view.findViewById(R.id.text_title),
        view.findViewById(R.id.text_playing_date),
        view.findViewById(R.id.text_running_time),
        view.findViewById(R.id.btn_ticketing)
    )


    private fun setViewHolder(holder: ViewHolder, movie: Movie) {
        holder.image.setImageResource(movie.image)
        holder.title.text = movie.title
        holder.playingDate.text = getString(R.string.playing_time).format(
            Formatter.dateFormat(movie.playingTimes.startDate),
            Formatter.dateFormat(movie.playingTimes.endDate)
        )
        holder.runningTime.text = getString(R.string.running_time).format(movie.runningTime)
        holder.ticketingButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            context.startActivity(intent)
        }
    }

    private fun getString(string: Int): String = context.getString(string)
    private class ViewHolder(
        val image: ImageView,
        val title: TextView,
        val playingDate: TextView,
        val runningTime: TextView,
        val ticketingButton: Button
    )

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
