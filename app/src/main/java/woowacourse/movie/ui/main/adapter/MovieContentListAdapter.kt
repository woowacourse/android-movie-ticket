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
import woowacourse.movie.ui.DateUi
import woowacourse.movie.ui.main.constants.MainMovieContentKey
import woowacourse.movie.ui.reservation.MovieReservationActivity

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
                DateUi.screeningDateMessage(movieContent.screeningDate, context)
            runningTimeText.text =
                context.resources.getString(R.string.running_time)
                    .format(movieContent.runningTime)
        }
    }

    override fun moveMovieReservationView(movieContentId: Long) {
        Intent(context, MovieReservationActivity::class.java).run {
            putExtra(MainMovieContentKey.ID, movieContentId)
            startActivity(context, this, null)
        }
    }
}
