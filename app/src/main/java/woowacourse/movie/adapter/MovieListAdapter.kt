package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.BookingActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.ui.MovieUiModel

class MovieListAdapter(private val context: Context, private val items: List<Movie>) :
    BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.movie_list_item, parent, false)
            viewHolder = MovieViewHolder(
                view.findViewById(R.id.movie_image),
                view.findViewById(R.id.title),
                view.findViewById(R.id.start_date),
                view.findViewById(R.id.end_date),
                view.findViewById(R.id.running_time),
                view.findViewById(R.id.reservation_button)
            )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieViewHolder
        }

        val item = getItem(position)

        val movieUiModel = MovieUiModel.fromDomain(item)

        viewHolder.apply {
            image.setImageResource(movieUiModel.poster)
            title.text = movieUiModel.title
            startDate.text = movieUiModel.startDate
            endDate.text = movieUiModel.endDate
            runningTime.text = movieUiModel.runningTime

            button.setOnClickListener {
                val intent = Intent(context, BookingActivity::class.java).apply {
                    putExtra("MOVIE_INFO", item)
                }
                context.startActivity(intent)
            }
        }

        return view
    }
}
