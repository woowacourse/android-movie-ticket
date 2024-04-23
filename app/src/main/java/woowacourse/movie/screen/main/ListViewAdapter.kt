package woowacourse.movie.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class ListViewAdapter(
    private val movies: List<MovieModel>,
    private val itemClickListener: OnItemClickListener,
) : BaseAdapter() {
    fun interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.listview_item, parent, false).apply {
                    tag = ViewHolder(this)
                }
        val holder = view.tag as ViewHolder
        holder.bind(position)
        return view
    }

    inner class ViewHolder(val view: View) {
        private val poster: ImageView = view.findViewById(R.id.list_img_poster)
        private val title: TextView = view.findViewById(R.id.list_movie_title)
        private val openingDay: TextView = view.findViewById(R.id.list_opening_day)
        private val runningTime: TextView = view.findViewById(R.id.running_time)
        private val reservationButton: Button = view.findViewById(R.id.btn_reservation)

        fun bind(position: Int) {
            val movie = movies[position]
            val openingDayText = movie.getFormattedOpeningDay(view.context)
            val runningTimeText = movie.getFormattedRunningTime(view.context)
            with(this) {
                poster.setImageResource(movie.poster)
                title.text = movie.title
                openingDay.text = openingDayText
                runningTime.text = runningTimeText
                reservationButton.setOnClickListener {
                    itemClickListener.onItemClick(position)
                }
            }
        }
    }
}
