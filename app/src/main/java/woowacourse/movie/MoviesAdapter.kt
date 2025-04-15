package woowacourse.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = movies.count()

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        var itemView = convertView
        if (itemView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
        }

        val item: Movie = movies[position]

        initItemView(itemView!!, item)
        initReserveButton(itemView, parent!!, item)

        return itemView
    }

    private fun initItemView(
        itemView: View,
        item: Movie,
    ) {
        val poster = itemView.findViewById<ImageView>(R.id.iv_poster)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val screeningDate = itemView.findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_running_time)

        poster.setImageResource(item.imageUrl)
        title.text = item.title
        screeningDate.text = item.date
        runningTime.text = MINUTE.format(item.runningTime.time)
    }

    private fun initReserveButton(
        itemView: View,
        parent: ViewGroup,
        item: Movie,
    ) {
        val reserveBtn = itemView.findViewById<Button>(R.id.btn_reserve)
        reserveBtn.setOnClickListener {
            val intent = Intent(parent.context, ReserveActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("screeningDate", item.date)
            parent.context?.startActivity(intent)
        }
    }

    companion object {
        private const val MINUTE = "%d분"
    }
}
