package woowacourse.movie.view

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
import woowacourse.movie.conrtract.MovieContentListAdapterContract
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.model.MovieContent
import woowacourse.movie.presenter.MovieContentListAdapterPresenter
import woowacourse.movie.ui.DateUi

class MovieContentListAdapter(
    private val context: Context,
) : BaseAdapter(), MovieContentListAdapterContract.View {
    private val movieContentListAdapterPresenter: MovieContentListAdapterContract.Presenter = MovieContentListAdapterPresenter(this)
    private lateinit var posterImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var screeningDateText: TextView
    private lateinit var runningTimeText: TextView
    private lateinit var reservationButton: Button

    override fun getCount(): Int = movieContentListAdapterPresenter.count()

    override fun getItem(position: Int): MovieContent = movieContentListAdapterPresenter.item(position)

    override fun getItemId(position: Int): Long = movieContentListAdapterPresenter.itemId(position)

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

        movieContentListAdapterPresenter.setUpMovieContent(position)

        reservationButton.setOnClickListener {
            movieContentListAdapterPresenter.clickReservationButton(position)
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
        Intent(context, MovieReservationActivity::class.java).apply {
            putExtra(MovieContentKey.ID, movieContentId)
            startActivity(context, this, null)
        }
    }
}
