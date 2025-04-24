package woowacourse.movie.view.screening.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening

class ScreeningAdapter(
    screenings: List<Screening>,
    private val onClickReserveButton: (Screening) -> Unit,
) : BaseAdapter() {
    private val screenings: List<Screening> = screenings.toList()
    private val viewHolderCache: MutableMap<Screening, ScreeningItemViewHolder> = mutableMapOf()

    override fun getCount(): Int = screenings.size

    override fun getItem(position: Int): Screening = screenings[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val screening: Screening = screenings[position]
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_screening, parent, false)
        val viewHolder: ScreeningItemViewHolder =
            viewHolderCache.getOrPut(screening) { ScreeningItemViewHolder(view) }
        viewHolder.bind(screening, onClickReserveButton)
        return view
    }
}
