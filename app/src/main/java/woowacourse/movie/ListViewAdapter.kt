package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ListViewAdapter(private val movies: MutableList<Movie>, private val itemClickListener: OnItemClickListener) : BaseAdapter() {
    fun interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(p0: Int): Any {
        return movies[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(
        p0: Int,
        p1: View?,
        p2: ViewGroup?,
    ): View {
        var convertView = p1
        if (convertView == null) {
            convertView =
                LayoutInflater.from(p2?.context).inflate(R.layout.listview_item, p2, false)
        }
        convertView!!.findViewById<ImageView>(R.id.img_poster).setImageResource(movies[p0].poster)
        convertView!!.findViewById<TextView>(R.id.movie_title).text = movies[p0].title
        convertView!!.findViewById<TextView>(R.id.opening_day).text =
            "상영일: ${movies[p0].openingDay}"
        convertView!!.findViewById<TextView>(R.id.running_time).text =
            "러닝타임: ${movies[p0].runningTime}분"

        val reservationButton = convertView.findViewById<Button>(R.id.btn_reservation)
        reservationButton.setOnClickListener {
            itemClickListener.onItemClick(p0)
        }
        return convertView!!
    }
}
