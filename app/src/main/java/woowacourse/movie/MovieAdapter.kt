package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.utils.formatTimestamp

class MovieAdapter(private val context: Context, private val viewInterface: ViewInterface, private val movies: List<Movie>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        var view = convertView
        val holder: MovieViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as MovieViewHolder
        }

        val movie = movies[position]
        holder.bind(movie, viewInterface)

        return view!!
    }
}

class MovieViewHolder(itemView: View) {
    private val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
    private val title: TextView = itemView.findViewById(R.id.movieTitle)
    private val date: TextView = itemView.findViewById(R.id.movieDate)
    private val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
    private val reservation: Button = itemView.findViewById(R.id.movieReservation)

    fun bind(
        movie: Movie,
        viewInterface: ViewInterface,
    ) {
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        date.text = formatTimestamp(movie.date)
        runningTime.text = "${movie.runningTime}분"
        reservation.setOnClickListener {
            viewInterface.onMovieItemClick(movie)
        }
    }
}
