package woowacourse.movie.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onReservationButtonClick: (Long) -> Unit,
) : BaseAdapter() {
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
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as MovieViewHolder
        }

        holder.bind(movies[position], onReservationButtonClick)
        return view!!
    }
}

class MovieViewHolder(view: View) {
    private val thumbnail: ImageView = view.findViewById(R.id.movieThumbnail)
    private val title: TextView = view.findViewById(R.id.movieTitle)
    private val date: TextView = view.findViewById(R.id.movieDate)
    private val runningTime: TextView = view.findViewById(R.id.movieRunningTime)
    private val reservationButton: Button = view.findViewById(R.id.movieReservationBtn)

    fun bind(
        movie: Movie,
        onReservationButtonClick: (Long) -> Unit,
    ) {
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        date.text = "${movie.date.startLocalDate} ~ ${movie.date.endLocalDate}".replace('-', '.')
        runningTime.text = movie.runningTime.toString()
        reservationButton.setOnClickListener {
            onReservationButtonClick(movie.id)
        }
    }
}
