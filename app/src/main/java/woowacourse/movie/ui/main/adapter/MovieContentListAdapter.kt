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
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.ui.DateUi
import woowacourse.movie.ui.main.constants.MainMovieContentKey
import woowacourse.movie.ui.reservation.MovieReservationActivity

class MovieContentListAdapter(
    private val context: Context,
) : BaseAdapter(), MovieContentListContract.View {
    private val presenter: MovieContentListContract.Presenter =
        MovieContentListPresenter(this, MovieContentsImpl)

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
            view.tag = movieViewHolder // 뭐지
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        presenter.setUpMovieContent(position, MovieViewHolder(view))

        movieViewHolder.reservationButton.setOnClickListener {
            presenter.moveMovieReservation(position)
        }

        return view
    }

    override fun setUpMovieContentUi(
        movieContent: MovieContent,
        movieViewHolder: MovieViewHolder,
    ) {
        movieViewHolder.posterImage.setImageResource(movieContent.imageId)
        movieViewHolder.titleText.text = movieContent.title
        movieViewHolder.screeningDateText.text =
            DateUi.screeningDateMessage(movieContent.screeningDate, context)
        movieViewHolder.runningTimeText.text =
            context.resources.getString(R.string.running_time).format(movieContent.runningTime)
    }

    override fun moveMovieReservationView(movieContentId: Long) {
        Intent(context, MovieReservationActivity::class.java).run {
            putExtra(MainMovieContentKey.ID, movieContentId)
            startActivity(context, this, null)
        }
    }
}
