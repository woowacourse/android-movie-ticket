package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.utils.formatScreeningPeriod

class MovieAdapter(
    private val onMovieItemClick: (Long) -> (Unit),
    private val movies: List<Movie>,
) :
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
            view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as MovieViewHolder
        }

        val movie = movies[position]
        holder.bind(movie, onMovieItemClick)

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
        onMovieItemClick: (Long) -> Unit,
    ) {
        thumbnail.load(movie.thumbnailUrl)
        title.text = movie.title
        date.text = formatScreeningPeriod(movie.screenDateTime.map { it.dateTime })
        runningTime.text = "${movie.runningTime}"
        reservation.setOnClickListener {
            onMovieItemClick(movie.id)
        }
    }
}
