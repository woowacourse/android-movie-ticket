package woowacourse.movie.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.presentation.model.movieitem.ListItem

class BaseRecyclerView {
    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun <T : ListItem> bind(item: T)
    }

    abstract class Adapter(
        private val onItemClick: (ListItem) -> Unit = {},
    ) : RecyclerView.Adapter<BaseViewHolder>() {
        protected val items: ArrayList<ListItem> = arrayListOf()
        protected val onItemViewClick: (Int) -> Unit = { position -> onItemClick(items[position]) }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        protected fun inflate(context: Context, parent: ViewGroup, @LayoutRes resId: Int): View =
            LayoutInflater.from(context).inflate(resId, parent, false)
    }
}
