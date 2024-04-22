package woowacourse.movie.adapter

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
    private val onTicketingButtonClick: (Long) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val context = parent.context
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        movieViewHolder.bind(movies[position])
        return view
    }

    inner class MovieViewHolder(private val itemView: View) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val date: TextView = itemView.findViewById(R.id.tv_date)
        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        private val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
        private val ticketingButton: Button = itemView.findViewById(R.id.btn_ticketing)

        fun bind(movie: Movie) {
            title.text = movie.title
            date.text = itemView.context.getString(R.string.title_date, movie.date)
            runningTime.text = itemView.context.getString(R.string.title_running_time, movie.runningTime)
            ticketingButton.setOnClickListener { onTicketingButtonClick(movie.id) }
            thumbnail.setImageResource(movie.thumbnailResourceId)
        }
    }
}
