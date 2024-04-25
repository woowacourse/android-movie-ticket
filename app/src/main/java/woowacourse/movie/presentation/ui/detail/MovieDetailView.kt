package woowacourse.movie.presentation.ui.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.utils.toDrawableIdByName

class MovieDetailView(private val view: View) {
    private val posterImage: ImageView = view.findViewById(R.id.posterImage)
    private val titleTextView: TextView = view.findViewById(R.id.title)
    private val screeningDateTextView: TextView = view.findViewById(R.id.screeningDate)
    private val runningTimeTextView: TextView = view.findViewById(R.id.runningTime)
    private val summaryTextView: TextView = view.findViewById(R.id.summary)

    fun bindDetails(movieUiModel: MovieUiModel) {
        val imageResource = movieUiModel.posterName.toDrawableIdByName(view.context)
        imageResource?.let { posterImage.setImageResource(it) }
        titleTextView.text = movieUiModel.title
        screeningDateTextView.text =
            view.context.getString(R.string.screening_date_format, movieUiModel.screeningDate)
        runningTimeTextView.text =
            view.context.getString(R.string.running_time_format, movieUiModel.runningTime)
        summaryTextView.text = movieUiModel.summary
    }
}
