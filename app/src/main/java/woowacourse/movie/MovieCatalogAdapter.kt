package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import domain.Movie

class MovieCatalogAdapter(val context: Context, val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val convertView: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_movie_catalog, null)
        val title = convertView.findViewById<TextView>(R.id.text_view_title)
        val poster = convertView.findViewById<ImageView>(R.id.image_view_poster)
        val screeningDate = convertView.findViewById<TextView>(R.id.text_view_screening_date)
        val runningTime = convertView.findViewById<TextView>(R.id.text_view_running_time)

        val item: Movie = movies[position]
        title.text = item.title
        poster.setImageResource(item.poster)
        screeningDate.text = item.screeningDate
        runningTime.text = item.runningTime

        return convertView
    }
}
