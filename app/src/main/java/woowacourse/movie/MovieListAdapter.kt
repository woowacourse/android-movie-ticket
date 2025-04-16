package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieListAdapter(private val movies: List<Movie>) : BaseAdapter() {

    override fun getCount(): Int {
        return movies.size
    }

    // 특정 위치의 데이터를 반환
    override fun getItem(position: Int): Any {
        return movies[position]
    }

    // 특정 위치의 아이템 ID를 반환 (일반적으로 position을 사용)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val currentItem = getItem(position) as? Movie ?: return View(parent.context)
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        val holder = (view.tag as? ViewHolder) ?: ViewHolder(view).also { view.tag = it }

        holder.titleTextView.text = currentItem.title
        holder.runningTimeTextView.text = holder.runningTimeTextView.context.getString(
            R.string.movie_running_time,
            currentItem.runningTime.inWholeMinutes
        )
        holder.screeningDateTextView.text = holder.screeningDateTextView.context.getString(
            R.string.movie_screening_date,
            DateTimeFormatter.ofPattern("yyyy.MM.dd").format(currentItem.startDateTime)
        )
        holder.posterImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                parent.context,
                currentItem.posterUrl
            )
        )
        parent.context
        return view
    }
}

private class ViewHolder(view: View) {
    val titleTextView: TextView = view.findViewById<TextView>(R.id.movie_title)
    val runningTimeTextView: TextView = view.findViewById<TextView>(R.id.movie_running)
    val screeningDateTextView: TextView = view.findViewById<TextView>(R.id.movie_date)
    val posterImageView: ImageView = view.findViewById<ImageView>(R.id.movie_poster)
}