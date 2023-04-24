package woowacourse.movie.presentation.booking

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class SpinnerAdapter<T>(
    context: Context,
    @LayoutRes item: Int,
    @IdRes view: Int,
) : ArrayAdapter<T>(context, item, view) {

    fun initItems(items: List<T>) {
        clear()
        addAll(items.toMutableList())
        notifyDataSetChanged()
    }
}
