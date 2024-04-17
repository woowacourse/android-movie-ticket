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

class ListViewAdapter(
    private val movies: List<Movie>,
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
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        bindData(view, position)
        return view!!
    }

    private fun bindData(
        view: View,
        position: Int,
    ) {
        val movie = movies[position]
        view.findViewById<ImageView>(R.id.img_poster).setImageResource(movie.poster)
        view.findViewById<TextView>(R.id.movie_title).text = movie.title
        view.findViewById<TextView>(R.id.opening_day).text = "상영일: ${movie.openingDay}"
        view.findViewById<TextView>(R.id.running_time).text = "러닝타임: ${movie.runningTime}분"
        view.findViewById<Button>(R.id.btn_reservation).setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }
}
