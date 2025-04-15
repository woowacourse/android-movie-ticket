package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import woowacourse.movie.model.Movie
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = 1

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_movie, parent, false)

        val titleTextView = view.findViewById<TextView>(R.id.tv_movie_title)
        titleTextView.text = movies[position].title

        val posterImageView = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val poster =
            AppCompatResources.getDrawable(
                context,
                movies[position].poster,
            )
        posterImageView.setImageDrawable(poster)

        val screeningDateTextView = view.findViewById<TextView>(R.id.tv_movie_screening_date)
        val screeningDate =
            movies[position].screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        screeningDateTextView.text =
            context.getString(R.string.movie_screening_date).format(screeningDate)

        val runningTimeTextView = view.findViewById<TextView>(R.id.tv_movie_running_time)
        val runningTime = movies[position].runningTime
        runningTimeTextView.text =
            context.getString(R.string.movie_running_time).format(runningTime)

        return view
    }
}
