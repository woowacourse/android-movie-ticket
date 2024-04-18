package woowacourse.movie.view.adapter

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
import woowacourse.movie.conrtract.MovieContentListContract
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.presenter.MovieContentListPresenter
import woowacourse.movie.view.activity.MovieReservationActivity
import woowacourse.movie.view.ui.DateUi

class MovieContentListAdapter(
    private val context: Context,
) : BaseAdapter(), MovieContentListContract.View {
    private val presenter: MovieContentListContract.Presenter = MovieContentListPresenter(this)
    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var screeningDateText: TextView
    private lateinit var runningTimeText: TextView
    private lateinit var reservationButton: Button

    override fun getCount(): Int = presenter.count()

    override fun getItem(position: Int): MovieContent = presenter.item(position)

    override fun getItemId(position: Int): Long = presenter.itemId(position)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_movie_content, parent, false)

        posterImage = view.findViewById(R.id.poster_image)
        titleText = view.findViewById(R.id.title_text)
        screeningDateText = view.findViewById(R.id.screening_date_text)
        runningTimeText = view.findViewById(R.id.running_time_text)
        reservationButton = view.findViewById(R.id.reservation_button)

        presenter.setUpMovieContent(position)

        reservationButton.setOnClickListener {
            presenter.clickReservationButton(position)
        }

        return view
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text = DateUi.screeningDateMessage(screeningDate, context)
            runningTimeText.text =
                context.resources.getString(R.string.running_time).format(runningTime)
        }
    }

    override fun moveMovieReservationView(movieContentId: Long) {
        Intent(context, MovieReservationActivity::class.java).run {
            putExtra(MovieContentKey.ID, movieContentId)
            startActivity(context, this, null)
        }
    }
}
