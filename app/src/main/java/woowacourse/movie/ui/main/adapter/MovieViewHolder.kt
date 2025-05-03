package woowacourse.movie.ui.main.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieViewHolder(
    private val view: View,
    private val onClick: (MovieUiModel) -> Unit,
) : BaseViewHolder<MovieUiModel>(view) {
    private val posterView: ImageView = view.findViewById(R.id.iv_poster)
    private val titleView: TextView = view.findViewById(R.id.tv_title)
    private val screeningDateView: TextView = view.findViewById(R.id.tv_screening_date)
    private val runningTimeView: TextView = view.findViewById(R.id.tv_running_time)
    private val reserveBtn = view.findViewById<Button>(R.id.btn_reserve)

    override fun bind(item: MovieUiModel) {
        with(item) {
            val formattedScreeningDate = formatScreeningDate(view, startDate, endDate)
            posterView.setImageResource(poster)
            titleView.text = title
            screeningDateView.text = formattedScreeningDate
            runningTimeView.text =
                view.context.getString(R.string.formatted_minute, runningMinute)
            reserveBtn.setOnClickListener {
                onClick(this)
            }
        }
    }

    private fun formatScreeningDate(
        view: View,
        startDate: LocalDate,
        endDate: LocalDate,
    ): String {
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format))
        val start = startDate.format(formatter)
        val end = endDate.format(formatter)
        return view.context.getString(R.string.formatted_screening_date, start, end)
    }
}
