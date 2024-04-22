package woowacourse.movie.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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
    movieContents: List<MovieContent>,
) : BaseAdapter(), MovieContentListContract.View {
    private val presenter: MovieContentListContract.Presenter =
        MovieContentListPresenter(this, movieContents)

    override fun getCount(): Int = presenter.count()

    override fun getItem(position: Int): MovieContent = presenter.item(position)

    override fun getItemId(position: Int): Long = presenter.itemId(position)

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

        presenter.setUpMovieContent(position, movieViewHolder)

        movieViewHolder.reservationButton.setOnClickListener {
            presenter.moveMovieReservation(position)
        }

        return view
    }

    override fun setUpMovieContentUi(
        movieContent: MovieContent,
        movieViewHolder: MovieViewHolder,
    ) {
        with(movieViewHolder) {
            posterImage.setImageResource(movieContent.imageId)
            titleText.text = movieContent.title

            screeningDateText.text =
                context.resources
                    .getString(R.string.screening_date)
                    .format(dateFormatter(movieContent.screeningMovieDate))

            runningTimeText.text =
                context.resources.getString(R.string.running_time)
                    .format(movieContent.runningTime)
        }
    }

    private fun dateFormatter(movieDate: MovieDate): String {
        val screeningDate = LocalDate.of(movieDate.year, movieDate.month, movieDate.day)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return screeningDate.format(formatter)
    }

    override fun moveMovieReservationView(movieContentId: Long) {
        Intent(context, MovieReservationActivity::class.java).run {
            putExtra(MovieHomeKey.ID, movieContentId)
            startActivity(context, this, null)
        }
    }
}
