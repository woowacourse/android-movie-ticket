package woowacourse.movie.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.util.ErrorUtils

class MovieListAdapter(
    context: Context,
    items: MutableList<MovieInfo>,
    val changeActivity: (MovieInfo) -> Unit,
    val onError: () -> Unit,
) : ArrayAdapter<MovieInfo>(context, 0, items) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.movie_list_item, parent, false)

        val item =
            getMovieInfoOrFinish(position) ?: run {
                onError()
                return view
            }
        val image = view.findViewById<ImageView>(R.id.movie_image)
        val title = view.findViewById<TextView>(R.id.title)
        val movieDate = view.findViewById<TextView>(R.id.movie_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)

        image.setImageResource(item.poster)
        title.text = item.title
        movieDate.text =
            context.resources.getString(
                R.string.movie_date,
                item.startDate,
                item.endDate,
            )
        runningTime.text =
            String.format(context.resources.getString(R.string.running_time), item.runningTime)

        val button = view.findViewById<Button>(R.id.reservation_button)
        button.setOnClickListener {
            changeActivity(item)
        }
        return view
    }

    private fun getMovieInfoOrFinish(position: Int): MovieInfo? {
        return getItem(position) ?: run {
            ErrorUtils.printError(context)
            return null
        }
    }
}
