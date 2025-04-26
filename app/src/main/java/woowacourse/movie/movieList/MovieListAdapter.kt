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

class MovieListAdapter(
    context: Context,
    items: List<MovieInfo>,
    private val presenter: MovieListContract.Presenter,
) : ArrayAdapter<MovieInfo>(context, 0, items) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        lateinit var view: View

        if (position % 4 == 3) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_list_ad, parent, false)
            printAd(view)
            return view
        }

        if (convertView == null || convertView.tag == null) {
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
                presenter.onError()
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
                presenter.onButtonClicked(item)
            }
        }

        return view
    }

    fun printAd(view: View) {
        val adView = view.findViewById<ImageView>(R.id.ad)
        adView.setImageResource(R.drawable.ad_image)
    }

    private fun getMovieInfoOrPrintError(position: Int): MovieInfo? {
        return getItem(position) ?: run {
            presenter.onError()
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
