package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieAdapter(private val movieViewDatas: MovieViewDatas) : BaseAdapter() {
    class ViewHolder(
        val poster: ImageView,
        val title: TextView,
        val date: TextView,
        val runningTime: TextView,
        val reservation: Button
    )

    override fun getCount(): Int = movieViewDatas.value.size

    override fun getItem(position: Int): Any = movieViewDatas.value[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (convertView == null) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
            val viewHolder = ViewHolder(
                view.findViewById(R.id.item_movie_poster),
                view.findViewById(R.id.item_movie_title),
                view.findViewById(R.id.item_movie_date),
                view.findViewById(R.id.item_movie_running_time),
                view.findViewById(R.id.item_movie_reservation_button)
            )
            view.tag = viewHolder
            view
        } else {
            convertView
        }

        renderMovie(
            getItem(position) as MovieViewData, view.tag as ViewHolder
        )

        (view.tag as ViewHolder).reservation.setOnClickListener {
            (parent as AdapterView<*>).performItemClick(it, position, getItemId(position))
        }

        return view
    }

    private fun renderMovie(
        movie: MovieViewData,
        viewHolder: ViewHolder
    ) {
        viewHolder.poster.setImageResource(movie.poster.resource)
        viewHolder.title.text = movie.title

        val dateFormat =
            DateTimeFormatter.ofPattern(viewHolder.date.context.getString(R.string.movie_date_format))
        viewHolder.date.text = viewHolder.date.context.getString(R.string.movie_date).format(
            dateFormat.format(movie.date.startDate), dateFormat.format(movie.date.endDate)
        )

        viewHolder.runningTime.text =
            viewHolder.runningTime.context.getString(R.string.movie_running_time)
                .format(movie.runningTime)
    }
}
