package woowacourse.movie.view.reservation

import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.view.mapper.Formatter.movieTimeToUI

class TimeSpinnerAdapter(
    context: Context,
    times: List<Int> = emptyList(),
) : ArrayAdapter<String>(
        context,
        com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
        times.map { movieTimeToUI(it) },
    ) {
    override fun getItem(position: Int): String = super.getItem(position).toString()

    fun updateDateItems(newItems: List<Int>) {
        clear()
        addAll(newItems.map { movieTimeToUI(it) })
        notifyDataSetChanged()
    }
}
