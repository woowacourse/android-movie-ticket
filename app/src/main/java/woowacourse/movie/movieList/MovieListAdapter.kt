package woowacourse.movie.movieList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.uimodel.MovieScheduleUi
import woowacourse.movie.utils.DateUtil

class MovieListAdapter(
    private val movieScheduleUi: List<MovieScheduleUi>,
    private val onReservationClickListener: (MovieScheduleUi) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movieScheduleUi.size
    }

    override fun getItem(position: Int): MovieScheduleUi {
        return movieScheduleUi[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder

        val view = if (convertView != null) {
            convertView.also { viewHolder = it.tag as ViewHolder }
        } else {
            val v = View.inflate(
                parent?.context,
                R.layout.include_movie_list_item,
                null,
            )
            viewHolder = makeViewHolder(v)
            v.tag = viewHolder
            v
        }

        viewHolder.bind(view.context, movieScheduleUi[position])
        return view
    }

    private fun makeViewHolder(view: View): ViewHolder {
        return ViewHolder(
            view.findViewById(R.id.movie_poster),
            view.findViewById(R.id.movie_title),
            view.findViewById(R.id.movie_release_date),
            view.findViewById(R.id.movie_running_time),
            view.findViewById(R.id.movie_reservation_button),
        )
    }

    private inner class ViewHolder(
        val posterView: ImageView,
        val titleView: TextView,
        val releaseDateView: TextView,
        val runningTimeView: TextView,
        val reservationButton: Button,
    ) {

        fun bind(context: Context, movieScheduleUi: MovieScheduleUi) {
            posterView.setImageResource(movieScheduleUi.poster)
            titleView.text = movieScheduleUi.title
            releaseDateView.text = DateUtil(context).getDateRange(movieScheduleUi.startDate, movieScheduleUi.endDate)
            runningTimeView.text = context.getString(R.string.movie_running_time).format(movieScheduleUi.runningTime)
            reservationButton.setOnClickListener { onReservationClickListener(movieScheduleUi) }
        }
    }
}
