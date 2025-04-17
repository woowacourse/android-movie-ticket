package woowacourse.movie.view

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

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val onReservationClick: (selectedMovie: Movie) -> Unit,
) : BaseAdapter() {
    private val formatter: Formatter by lazy { Formatter() }

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

        setupTitle(view, position)
        setupPoster(view, position)
        setupScreeningDate(view, position)
        setupRunningTime(view, position)
        setupReservationButtonClick(view, position)

        return view
    }

    private fun setupTitle(
        view: View,
        position: Int,
    ) {
        val titleTextView = view.findViewById<TextView>(R.id.tv_movie_title)
        titleTextView.text = movies[position].title
    }

    private fun setupPoster(
        view: View,
        position: Int,
    ) {
        val posterImageView = view.findViewById<ImageView>(R.id.iv_movie_poster)
        val poster =
            AppCompatResources.getDrawable(
                context,
                movies[position].poster,
            )
        posterImageView.setImageDrawable(poster)
    }

    private fun setupScreeningDate(
        view: View,
        position: Int,
    ) {
        val screeningDateTextView = view.findViewById<TextView>(R.id.tv_movie_screening_date)
        val startDate = formatter.localDateToUI(movies[position].startDate)
        val endDate = formatter.localDateToUI(movies[position].endDate)
        screeningDateTextView.text =
            context.getString(R.string.movie_screening_date, startDate, endDate)
    }

    private fun setupRunningTime(
        view: View,
        position: Int,
    ) {
        val runningTimeTextView = view.findViewById<TextView>(R.id.tv_movie_running_time)
        val runningTime = movies[position].runningTime
        runningTimeTextView.text =
            context.getString(R.string.movie_running_time, runningTime)
    }

    private fun setupReservationButtonClick(
        view: View,
        position: Int,
    ) {
        val button = view.findViewById<Button>(R.id.btn_movie_reservation)
        button.setOnClickListener {
            onReservationClick.invoke(movies[position])
        }
    }
}
