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
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
        presenter.assignInitialView(movies[position], view)
        return view
    }

    override fun assignInitialView(
        movie: Movie,
        itemView: View,
    ) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
        val thumbnail = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_running_time)
        val ticketingButton = itemView.findViewById<Button>(R.id.btn_ticketing)

        title.text = movie.title
        date.text = itemView.context?.getString(R.string.title_date, movie.date)
        runningTime.text =
            itemView.context?.getString(R.string.title_running_time, movie.runningTime)
        ticketingButton.setOnClickListener { onTicketingButtonClick(movie.id) }
        thumbnail.setImageResource(movie.thumbnail)
    }
}
