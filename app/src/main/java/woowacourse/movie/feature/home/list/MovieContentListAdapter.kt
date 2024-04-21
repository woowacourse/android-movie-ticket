package woowacourse.movie.feature.home.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.feature.reservation.MovieReservationActivity
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.ui.DateUi

class MovieContentListAdapter(private val context: Context) :
    BaseAdapter(),
    MovieContentListContract.View {
    private lateinit var viewHolder: MovieContentViewHolder
    private val presenter: MovieContentListContract.Presenter =
        MovieContentListPresenter(this, MovieContentsImpl)

    private class MovieContentViewHolder(view: View) {
        val posterImage: ImageView by lazy { view.findViewById(R.id.poster_image) }
        val titleText: TextView by lazy { view.findViewById(R.id.title_text) }
        val screeningDateText: TextView by lazy { view.findViewById(R.id.screening_date_text) }
        val runningTimeText: TextView by lazy { view.findViewById(R.id.running_time_text) }
        val reservationButton: Button by lazy { view.findViewById(R.id.reservation_button) }
    }

    override fun getCount(): Int = presenter.count()

    override fun getItem(position: Int): MovieContent = presenter.item(position)

    override fun getItemId(position: Int): Long = presenter.itemId(position)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie_content, parent, false)
            viewHolder = MovieContentViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieContentViewHolder
        }

        presenter.setUpMovieContent(position)
        viewHolder.reservationButton.setOnClickListener {
            presenter.clickReservationButton(position)
        }

        return view
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        viewHolder.run {
            posterImage.setImageResource(movieContent.imageId)
            titleText.text = movieContent.title
            screeningDateText.text = DateUi.screeningDateMessage(movieContent.screeningDate, context)
            runningTimeText.text =
                context.resources.getString(R.string.running_time).format(movieContent.runningTime)
        }
    }

    override fun moveMovieReservationView(movieContentId: Long) {
        MovieReservationActivity.startActivity(context, movieContentId)
    }
}
