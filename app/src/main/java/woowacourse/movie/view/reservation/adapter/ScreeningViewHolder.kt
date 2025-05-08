package woowacourse.movie.view.reservation.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.domain.reservation.ScreeningContent
import woowacourse.movie.view.reservation.Poster.posterId

class ScreeningViewHolder(
    private val view: View,
    private val onClickReserveButton: (Screening) -> Unit,
) : ScreeningContentViewHolder(view) {
    private val titleView =
        view.findViewById<TextView>(R.id.tv_item_screening_title)
            ?: error(ERROR_MESSAGE_NO_VIEW_ID.format("tv_item_screening_title"))
    private val screeningDateView =
        view.findViewById<TextView>(R.id.tv_item_screening_date) ?: error(
            ERROR_MESSAGE_NO_VIEW_ID.format("tv_item_screening_date"),
        )
    private val runningTimeView =
        view.findViewById<TextView>(R.id.tv_item_screening_running_time) ?: error(
            ERROR_MESSAGE_NO_VIEW_ID.format("tv_item_screening_running_time"),
        )
    private val posterView =
        view.findViewById<ImageView>(R.id.iv_item_screening_poster) ?: error(
            ERROR_MESSAGE_NO_VIEW_ID.format("iv_item_screening_poster"),
        )
    private val reserveButton =
        view.findViewById<Button>(R.id.btn_item_screening_reserve) ?: error(
            ERROR_MESSAGE_NO_VIEW_ID.format("btn_item_screening_reserve"),
        )

    override fun bind(item: ScreeningContent) {
        val screening = item as? Screening ?: error("")
        screening.run {
            titleView.text = screening.title
            screeningDateView.text =
                view.context.getString(
                    R.string.screening_period,
                    startYear,
                    startMonth,
                    startDay,
                    endYear,
                    endMonth,
                    endDay,
                )
            runningTimeView.text =
                view.context.getString(R.string.running_time, runningTime)
            val posterResourceId = posterId()
            if (posterResourceId != null) posterView.setImageResource(posterResourceId)
            reserveButton.setOnClickListener {
                onClickReserveButton(
                    screening,
                )
            }
        }
    }

    companion object {
        private const val ERROR_MESSAGE_NO_VIEW_ID = "Can't find view id of %s"
    }
}
