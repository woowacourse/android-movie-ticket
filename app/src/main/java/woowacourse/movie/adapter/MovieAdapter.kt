package woowacourse.movie.adapter

import android.content.Context
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
    private val onTicketingButtonClick: (Int) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val context = parent?.context ?: throw Exception() // TODO 예외 처리 방식 확정 필요
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
        assignInitialView(context, movies[position], movieViewHolder)
        return view
    }

    private fun assignInitialView(
        context: Context,
        movie: Movie,
        viewHolder: MovieViewHolder,
    ) {
        viewHolder.apply {
            title.text = movie.title
            date.text = context.getString(R.string.title_date, movie.date)
            runningTime.text = context.getString(R.string.title_running_time, movie.runningTime)
            ticketingButton.setOnClickListener { onTicketingButtonClick(movie.id) }
            thumbnail.setImageResource(movie.thumbnail)
        }
    }

    inner class MovieViewHolder(itemView: View) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
        val ticketingButton: Button = itemView.findViewById(R.id.btn_ticketing)
    }
}
