package woowacourse.movie

import android.content.Context
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
            convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        val posterImage = view.findViewById<ImageView>(R.id.posterImage)
        val title = view.findViewById<TextView>(R.id.title)
        val screeningDate = view.findViewById<TextView>(R.id.screeningDate)
        val runningTime = view.findViewById<TextView>(R.id.runningTime)
        val resourceID =
            context.resources.getIdentifier(
                movieList[index].posterImage,
                "drawable",
                context.packageName,
            )

        posterImage.setImageResource(resourceID)
        title.text = movieList[index].title
        screeningDate.text =
            context.getString(R.string.screening_date_format, movieList[index].screeningDate)
        runningTime.text =
            context.getString(R.string.running_time_format, movieList[index].runningTime)

        return view
    }
}
