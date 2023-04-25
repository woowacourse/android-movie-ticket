package woowacourse.movie.view.model

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

data class MovieUiModel(
    @DrawableRes val picture: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : UiModel, Serializable {
    fun getDateList(): List<LocalDate> {
        return (0..Period.between(startDate, endDate).days).map { startDate.plusDays(it.toLong()) }
    }

    fun renderMovie(
        posterImageView: ImageView? = null,
        titleTextView: TextView? = null,
        dateTextView: TextView? = null,
        runningTimeTextView: TextView? = null,
        descriptionTextView: TextView? = null
    ) {
        posterImageView?.setImageResource(picture)
        titleTextView?.text = title

        val dateFormat =
            DateTimeFormatter.ofPattern(titleTextView?.context?.getString(R.string.movie_date_format))
        dateTextView?.text = dateTextView?.context?.getString(R.string.movie_date)?.format(
            dateFormat.format(startDate), dateFormat.format(endDate)
        )
        runningTimeTextView?.text =
            runningTimeTextView?.context?.getString(R.string.movie_running_time)
                ?.format(runningTime)
        descriptionTextView?.text = description
    }
}
