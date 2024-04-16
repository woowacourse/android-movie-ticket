package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.Movie

class MovieListAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int = movieList.size

    override fun getItem(index: Int): Movie = movieList[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.movie_item, parent, false)
        val movie = movieList[index]

        val posterImage = view.findViewById<ImageView>(R.id.posterImage)
        val title = view.findViewById<TextView>(R.id.title)
        val screeningDate = view.findViewById<TextView>(R.id.screeningDate)
        val runningTime = view.findViewById<TextView>(R.id.runningTime)
        val button = view.findViewById<TextView>(R.id.reserveButton)

        posterImage.setImageResource(movie.posterImageId)
        title.text = movie.title
        screeningDate.text =
            context.getString(R.string.screening_date_format, movie.screeningDate)
        runningTime.text =
            context.getString(R.string.running_time_format, movie.runningTime)

        button.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("posterImageId", movie.posterImageId)
            intent.putExtra("title", movie.title)
            intent.putExtra("screeningDate", movie.screeningDate)
            intent.putExtra("runningTime", movie.runningTime)
            intent.putExtra("summary", movie.summary)
            context.startActivity(intent)
        }

        return view
    }
}
