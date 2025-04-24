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
        lateinit var view: View

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(context)
                    .inflate(R.layout.movie_list_item, parent, false)
            view.tag = ViewHolder.of(view)
        } else {
            view = convertView
        }

        val viewHolder = view.tag as ViewHolder

        val item =
            getMovieInfoOrPrintError(position) ?: run {
                onError()
                return view
            }

        with(viewHolder) {
            image.setImageResource(item.poster)
            title.text = item.title
            movieDate.text =
                context.getString(
                    R.string.movie_date,
                    item.startDate,
                    item.endDate,
                )
            runningTime.text =
                context.getString(R.string.running_time, item.runningTime)

            button.setOnClickListener {
                changeActivity(item)
            }
        }

        return view
    }

    private fun getMovieInfoOrPrintError(position: Int): MovieInfo? {
        return getItem(position) ?: run {
            ErrorUtils.printError(context)
            return null
        }
    }

    private class ViewHolder(
        val image: ImageView,
        val title: TextView,
        val movieDate: TextView,
        val runningTime: TextView,
        val button: Button,
    ) {
        companion object {
            fun of(view: View): ViewHolder =
                ViewHolder(
                    view.findViewById(R.id.movie_image),
                    view.findViewById(R.id.title),
                    view.findViewById(R.id.movie_date),
                    view.findViewById(R.id.running_time),
                    view.findViewById(R.id.reservation_button),
                )
        }
    }
}
