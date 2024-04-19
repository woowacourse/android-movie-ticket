package woowacourse.movie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MovieAdapterPresenter
import woowacourse.movie.presenter.contract.MovieAdapterContract

class MovieAdapter(
    private val movies: List<Movie>,
    private val onTicketingButtonClick: (Int) -> Unit,
) : BaseAdapter(), MovieAdapterContract {
    private val presenter = MovieAdapterPresenter(this)

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            Log.d("adapter", "new")
            view =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        presenter.assignInitialView(movies[position], movieViewHolder)
        return view
    }

    override fun assignInitialView(
        movie: Movie,
        itemView: MovieViewHolder,
    ) {
        itemView.title.text = movie.title
        itemView.date.text = itemView.date.context?.getString(R.string.title_date, movie.date)
        itemView.runningTime.text =
            itemView.runningTime.context?.getString(R.string.title_running_time, movie.runningTime)
        itemView.ticketingButton.setOnClickListener { onTicketingButtonClick(movie.id) }
        itemView.thumbnail.setImageResource(movie.thumbnail)
    }

    inner class MovieViewHolder(itemView: View) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
        val ticketingButton: Button = itemView.findViewById(R.id.btn_ticketing)
    }
}
