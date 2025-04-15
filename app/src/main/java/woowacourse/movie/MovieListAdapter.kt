package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies

class MovieListAdapter(private val movies: Movies, private val context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return movies.movies.size
    }

    // 특정 위치의 데이터를 반환
    override fun getItem(position: Int): Any {
        return movies.movies.values.elementAt(position)
    }

    // 특정 위치의 아이템 ID를 반환 (일반적으로 position을 사용)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val currentItem = getItem(position) as Movie
        holder.nameText.text = currentItem.title
        holder.runningTime.text = currentItem.runningTime.toString()
        holder.screeningDate.text = currentItem.startDateTime.toString()

        return view
    }
}

private class ViewHolder(view: View) {
    val nameText: TextView = view.findViewById(R.id.movieTitle)
    val runningTime: TextView = view.findViewById(R.id.running_time)
    val screeningDate: TextView = view.findViewById(R.id.screening_date)

}