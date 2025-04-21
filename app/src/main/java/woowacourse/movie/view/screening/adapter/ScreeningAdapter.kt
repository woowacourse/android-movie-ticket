package woowacourse.movie.view.screening.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.reservation.model.Screening

class ScreeningAdapter(
    screenings: List<Screening>,
    private val onClickReserveButton: (
        Screening,
    ) -> Unit,
) : BaseAdapter() {
    private val screenings: List<Screening> = screenings.toList()

    override fun getCount(): Int = screenings.size

    override fun getItem(position: Int): Screening = screenings[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_screening, parent, false)
        val screening: Screening = screenings[position]
        initMovieItemView(view, screening)

        return view
    }

    private fun initMovieItemView(
        view: View,
        screening: Screening,
    ) {
        with(screening) {
            val titleView = view.findViewById<TextView>(R.id.tv_item_screening_title)
            titleView.text = screening.title

            val screeningDateView = view.findViewById<TextView>(R.id.tv_item_screening_date)
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

            val runningTimeView = view.findViewById<TextView>(R.id.tv_item_screening_running_time)
            runningTimeView.text =
                view.context.getString(R.string.running_time, runningTime)

            val posterView = view.findViewById<ImageView>(R.id.iv_item_screening_poster)
            posterView.setImageResource(posterId)

            val reserveButton = view.findViewById<Button>(R.id.btn_item_screening_reserve)
            reserveButton.setOnClickListener {
                onClickReserveButton(
                    screening,
                )
            }
        }
    }
}
