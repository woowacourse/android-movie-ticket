package woowacourse.movie.view.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.MovieClickListener
import woowacourse.movie.view.ReservationUiFormatter

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val clickListener: MovieClickListener,
) : BaseAdapter() {
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    private class ViewHolder(
        val titleTextView: TextView,
        val posterImageView: ImageView,
        val screeningDateTextView: TextView,
        val runningTimeTextView: TextView,
        val reservationButton: Button,
    )

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            viewHolder =
                ViewHolder(
                    view.findViewById(R.id.tv_movie_title),
                    view.findViewById(R.id.iv_movie_poster),
                    view.findViewById(R.id.tv_movie_screening_date),
                    view.findViewById(R.id.tv_movie_running_time),
                    view.findViewById(R.id.btn_movie_reservation),
                )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val movie = movies[position]
        setupTitle(viewHolder, movie)
        setupPoster(viewHolder, movie)
        setupScreeningDate(viewHolder, movie)
        setupRunningTime(viewHolder, movie)
        setupReservationButtonClick(viewHolder, movie)

        return view
    }

    private fun setupTitle(
        viewHolder: ViewHolder,
        movie: Movie,
    ) {
        viewHolder.titleTextView.text = movie.title
    }

    private fun setupPoster(
        viewHolder: ViewHolder,
        movie: Movie,
    ) {
        val poster = AppCompatResources.getDrawable(context, movie.poster)
        viewHolder.posterImageView.setImageDrawable(poster)
    }

    private fun setupScreeningDate(
        viewHolder: ViewHolder,
        movie: Movie,
    ) {
        val startDate = reservationUiFormatter.localDateToUI(movie.startDate)
        val endDate = reservationUiFormatter.localDateToUI(movie.endDate)
        viewHolder.screeningDateTextView.text =
            context.getString(R.string.movie_screening_date, startDate, endDate)
    }

    private fun setupRunningTime(
        viewHolder: ViewHolder,
        movie: Movie,
    ) {
        viewHolder.runningTimeTextView.text =
            context.getString(R.string.movie_running_time, movie.runningTime)
    }

    private fun setupReservationButtonClick(
        viewHolder: ViewHolder,
        movie: Movie,
    ) {
        viewHolder.reservationButton.setOnClickListener {
            clickListener.onReservationClick(movie)
        }
    }
}
