package woowacourse.movie.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.MovieDate
import woowacourse.movie.ui.main.MovieHomeKey
import woowacourse.movie.ui.reservation.MovieReservationActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieContentListAdapter(
    private val context: Context,
    private val movieContents: List<MovieContent>,
) : BaseAdapter() {
    override fun getCount(): Int = movieContents.size

    override fun getItem(position: Int): MovieContent = movieContents[position]

    override fun getItemId(position: Int): Long = movieContents[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie_content, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        movieViewHolder.setUpContentUi(getItem(position))

        return view
    }

    class MovieViewHolder(private val view: View) {
        private val posterImage: ImageView = view.findViewById(R.id.poster_image)
        private val titleText: TextView = view.findViewById(R.id.title_text)
        private val screeningDateText: TextView = view.findViewById(R.id.screening_date_text)
        private val runningTimeText: TextView = view.findViewById(R.id.running_time_text)
        private val reservationButton: Button = view.findViewById(R.id.reservation_button)

        fun setUpContentUi(movieContent: MovieContent) {
            posterImage.setImageResource(movieContent.imageId)
            titleText.text = movieContent.title
            screeningDateText.text =
                view.context.resources
                    .getString(R.string.screening_date)
                    .format(dateFormatter(movieContent.screeningMovieDate))
            runningTimeText.text =
                view.context.resources.getString(R.string.running_time)
                    .format(movieContent.runningTime)
            reservationButton.setOnClickListener {
                Intent(view.context, MovieReservationActivity::class.java).run {
                    putExtra(MovieHomeKey.ID, movieContent.id)
                    startActivity(view.context, this, null)
                }
            }
        }

        private fun dateFormatter(movieDate: MovieDate): String {
            val screeningDate = LocalDate.of(movieDate.year, movieDate.month, movieDate.day)
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            return screeningDate.format(formatter)
        }
    }
}
