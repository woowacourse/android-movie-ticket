package woowacourse.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Screening
import java.time.LocalDate

class MovieAdapter(
    screenings: List<Screening>,
    private val onClickReserveButton: (
        title: String,
        period: ClosedRange<LocalDate>,
        posterId: Int,
        runningTime: Int,
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
                .inflate(R.layout.item_movie, parent, false)
        val screening: Screening = screenings[position]
        initMovieItemView(view, screening)

        return view
    }

    private fun initMovieItemView(
        view: View,
        screening: Screening,
    ) {
        with(screening) {
            val titleView = view.findViewById<TextView>(R.id.tv_item_movie_title)
            titleView.text = screening.title

            val screeningDateView = view.findViewById<TextView>(R.id.tv_item_movie_screening_date)
            screeningDateView.text =
                view.context.getString(
                    R.string.screening_period,
                    period.start.year,
                    period.start.monthValue,
                    period.start.dayOfMonth,
                    period.endInclusive.year,
                    period.endInclusive.monthValue,
                    period.endInclusive.dayOfMonth,
                )

            val runningTimeView = view.findViewById<TextView>(R.id.tv_item_movie_running_time)
            runningTimeView.text =
                view.context.getString(R.string.running_time, runningTime)

            val posterView = view.findViewById<ImageView>(R.id.iv_item_movie_poster)
            posterView.setImageResource(posterId)

            val reserveButton = view.findViewById<Button>(R.id.btn_item_movie_reserve)
            reserveButton.setOnClickListener {
                onClickReserveButton(
                    title,
                    period,
                    posterId,
                    runningTime,
                )
            }
        }
    }
}
