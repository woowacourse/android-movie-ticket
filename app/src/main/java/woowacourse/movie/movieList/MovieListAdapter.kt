package woowacourse.movie.movieList

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
        val (viewHolder: ViewHolder, view) = getViewHolder(convertView, parent)

        with(movieScheduleUi[position]) {
            viewHolder.posterView.setImageResource(poster)
            viewHolder.titleView.text = title
            viewHolder.releaseDateView.text = DateUtil(view.context).getDateRange(startDate, endDate)
            viewHolder.runningTimeView.text = view.context.getString(R.string.movie_running_time).format(runningTime)
            viewHolder.reservationButton.setOnClickListener { onReservationClickListener(this) }
        }

        return view
    }

    private fun getViewHolder(
        convertView: View?,
        parent: ViewGroup?,
    ): Pair<ViewHolder, View> {
        val viewHolder: ViewHolder

        val view = if (convertView != null) {
            convertView.also { viewHolder = it.tag as ViewHolder }
        } else {
            val v = View.inflate(
                parent?.context,
                R.layout.include_movie_list_item,
                null,
            )
            viewHolder = ViewHolder(
                v.findViewById(R.id.movie_poster),
                v.findViewById(R.id.movie_title),
                v.findViewById(R.id.movie_release_date),
                v.findViewById(R.id.movie_running_time),
                v.findViewById(R.id.movie_reservation_button),
            )
            v.tag = viewHolder
            v
        }
        return Pair(viewHolder, view)
    }

    class ViewHolder(
        val posterView: ImageView,
        val titleView: TextView,
        val releaseDateView: TextView,
        val runningTimeView: TextView,
        val reservationButton: Button,
    )
}
