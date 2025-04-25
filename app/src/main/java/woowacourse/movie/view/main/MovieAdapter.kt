package woowacourse.movie.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ResourceMapper
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.setPoster

class MovieAdapter(
    screenings: List<Screening>,
    private val onClickReserveButton: (
        screeningData: ScreeningData,
    ) -> Unit,
) : BaseAdapter() {
    private val screenings: List<Screening> = screenings.toList()

    private class ViewHolder(
        view: View,
    ) {
        val titleView: TextView = view.findViewById(R.id.tv_item_movie_title)
        val screeningDateView: TextView = view.findViewById(R.id.tv_item_movie_screening_date)
        val runningTimeView: TextView = view.findViewById(R.id.tv_item_movie_running_time)
        val posterView: ImageView = view.findViewById(R.id.iv_item_movie_poster)
        val reserveButton: Button = view.findViewById(R.id.btn_item_movie_reserve)
    }

    override fun getCount(): Int = screenings.size

    override fun getItem(position: Int): Screening = screenings[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null || convertView.getTag(R.id.tag_view_holder) !is ViewHolder) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.item_movie, parent, false)
            viewHolder = ViewHolder(view)
            view.setTag(R.id.tag_view_holder, viewHolder)
        } else {
            viewHolder = convertView.getTag(R.id.tag_view_holder) as ViewHolder
            view = convertView
        }
        val screeningData: ScreeningData = convertScreeningData(screenings[position])
        bindMovieItemViewHolder(viewHolder, screeningData)

        return view
    }

    private fun convertScreeningData(screening: Screening): ScreeningData =
        ScreeningData(
            title = screening.title,
            startDate = screening.period.start,
            endDate = screening.period.endInclusive,
            movieId = screening.movieId,
            runningTime = screening.runningTime,
            poster = ResourceMapper.movieIdToPoster(screening.movieId),
        )

    private fun bindMovieItemViewHolder(
        viewHolder: ViewHolder,
        screeningData: ScreeningData,
    ) {
        with(viewHolder) {
            val context = titleView.context

            titleView.text = screeningData.title

            screeningDateView.text =
                context.getString(
                    R.string.screening_period,
                    screeningData.startDate.year,
                    screeningData.startDate.monthValue,
                    screeningData.startDate.dayOfMonth,
                    screeningData.endDate.year,
                    screeningData.endDate.monthValue,
                    screeningData.endDate.dayOfMonth,
                )

            runningTimeView.text =
                context.getString(R.string.running_time, screeningData.runningTime)

            posterView.setPoster(screeningData.poster)

            reserveButton.setOnClickListener {
                onClickReserveButton(screeningData)
            }
        }
    }
}
