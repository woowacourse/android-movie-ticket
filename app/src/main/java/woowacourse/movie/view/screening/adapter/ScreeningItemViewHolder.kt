package woowacourse.movie.view.screening.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening

class ScreeningItemViewHolder(
    private val view: View,
) {
    fun bind(
        screening: Screening,
        onClickReserveButton: (Screening) -> Unit,
    ) {
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
            posterView.setImageResource(posterId)
            reserveButton.setOnClickListener {
                onClickReserveButton(
                    screening,
                )
            }
        }
    }

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

    companion object {
        private const val ERROR_MESSAGE_NO_VIEW_ID = "Can't find view id of %s"
    }
}
