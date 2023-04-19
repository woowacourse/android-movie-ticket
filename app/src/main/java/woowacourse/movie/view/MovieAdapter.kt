package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import woowacourse.movie.R
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.MovieViewDatas
import woowacourse.movie.view.widget.MovieController
import woowacourse.movie.view.widget.MovieView

class MovieAdapter(private val movieViewDatas: MovieViewDatas) : BaseAdapter() {
    class ViewHolder(
        val movieView: MovieView,
        val reservation: Button
    )

    override fun getCount(): Int = movieViewDatas.value.size

    override fun getItem(position: Int): Any = movieViewDatas.value[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (convertView == null) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
            val viewHolder = ViewHolder(
                MovieView(
                    poster = view.findViewById(R.id.item_movie_poster),
                    title = view.findViewById(R.id.item_movie_title),
                    date = view.findViewById(R.id.item_movie_date),
                    runningTime = view.findViewById(R.id.item_movie_running_time)
                ),
                view.findViewById(R.id.item_movie_reservation_button)
            )
            view.tag = viewHolder
            view
        } else {
            convertView
        }

        MovieController(
            getItem(position) as MovieViewData, (view.tag as ViewHolder).movieView
        ).render()

        (view.tag as ViewHolder).reservation.setOnClickListener {
            (parent as AdapterView<*>).performItemClick(it, position, getItemId(position))
        }

        return view
    }
}
