package woowacourse.movie.ui.main.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.formatScreenDate
import woowacourse.movie.model.main.MainData
import woowacourse.movie.model.main.MovieUiModel
import woowacourse.movie.ui.main.adapter.MainViewType

class MovieViewHolder(parent: ViewGroup, clickBook: (Int) -> Unit) :
    MainViewHolder(makeView(parent)) {
    private val thumbnail: ImageView = view.findViewById(R.id.imageItemThumbnail)
    private val title: TextView = view.findViewById(R.id.textItemTitle)
    private val date: TextView = view.findViewById(R.id.textBookingScreeningDate)
    private val runningTime: TextView = view.findViewById(R.id.textBookingRunningTime)
    private val button: Button = view.findViewById(R.id.buttonItemBook)

    override val mainViewType: MainViewType = MainViewType.CONTENT

    init {
        button.setOnClickListener { clickBook(adapterPosition) }
    }

    override fun onBind(data: MainData) {
        val movie = data as MovieUiModel
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        date.text = view.context.getString(
            R.string.screening_date,
            movie.startDate.formatScreenDate(),
            movie.endDate.formatScreenDate(),
        )
        runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
    }

    companion object {
        private fun makeView(parent: ViewGroup): View {
            val layoutInflater = LayoutInflater.from(parent.context)
            return layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        }
    }
}
