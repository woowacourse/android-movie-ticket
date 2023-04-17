package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import model.ReservationModel
import model.ScreeningModel
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val items: List<ScreeningModel>,
    private val onClickButton: (ScreeningModel) -> Unit,
) : BaseAdapter() {
    private val viewHolder: MutableMap<View, MovieListViewHolder> = mutableMapOf()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): ScreeningModel {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: initItemView(parent)
        val screening = items[position]

        bindViewHolder(view, screening)
        return view
    }

    private fun initItemView(parent: ViewGroup?): View = View.inflate(
        parent?.context,
        R.layout.item_movie_list,
        null,
    )

    private fun bindViewHolder(view: View, screeningModel: ScreeningModel) {
        viewHolder.getOrPut(view) { MovieListViewHolder(view) }
            .bind(
                posterResource = screeningModel.poster,
                title = screeningModel.title,
                date = getScreeningDate(screeningModel.reservationModel),
                runningTime = view.context.getString(R.string.movie_running_time).format(screeningModel.runTime),
                onClickButton = { onClickButton(screeningModel) },
            )
    }

    private fun getScreeningDate(reservationModel: ReservationModel): String {
        return "${reservationModel.startDate.format(dateTimeFormatter)} ~ ${reservationModel.endDate.format(dateTimeFormatter)}"
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
