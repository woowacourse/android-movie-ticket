package woowacourse.movie.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    val context: Context,
    val movies: List<Movie>
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        }
        requireNotNull(convertView) { NULL_VIEW_ERROR }
        val movie = movies[position]
        convertView.findViewById<ImageView>(R.id.movie_poster)
            ?.setImageResource(movie.poster.resourceId)
        convertView.findViewById<TextView>(R.id.movie_title)?.text = movie.title
        convertView.findViewById<TextView>(R.id.movie_screening_date)?.text =
            context.resources.getString(R.string.screening_date)
                .format(movie.screeningDate.toString())
        convertView.findViewById<TextView>(R.id.movie_running_time)?.text =
            context.resources.getString(R.string.running_time)
                .format(movie.runningTime.value)

        return convertView
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    companion object {
        private const val NULL_VIEW_ERROR = "[ERROR] 뷰는 널일 수 없습니다."
    }
}
