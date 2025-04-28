package woowacourse.movie.view.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Advertisement
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.domain.reservation.ScreeningContent
import woowacourse.movie.view.util.ErrorMessage

class ScreeningAdapter(
    private val items: List<ScreeningContent>,
    private val onClickReserveButton: (Screening) -> Unit,
) : RecyclerView.Adapter<ScreeningContentViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        val screeningContent = items[position]
        return when (screeningContent) {
            is Screening -> VIEW_TYPE_SCREENING
            is Advertisement -> VIEW_TYPE_ADVERTISEMENT
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreeningContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_SCREENING -> {
                val view = layoutInflater.inflate(R.layout.item_screening, parent, false)
                ScreeningViewHolder(view, onClickReserveButton)
            }

            VIEW_TYPE_ADVERTISEMENT -> {
                val view = layoutInflater.inflate(R.layout.item_advertisement, parent, false)
                AdvertisementViewHolder(view)
            }

            else -> error(ErrorMessage("viewType").noSuch())
        }
    }

    override fun onBindViewHolder(
        holder: ScreeningContentViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    companion object {
        const val VIEW_TYPE_SCREENING = 0
        const val VIEW_TYPE_ADVERTISEMENT = 1
    }
}
