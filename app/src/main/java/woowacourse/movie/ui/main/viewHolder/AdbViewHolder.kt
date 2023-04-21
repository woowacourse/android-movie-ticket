package woowacourse.movie.ui.main.viewHolder

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.ui.main.itemModel.AdbItemModel
import woowacourse.movie.ui.main.itemModel.ItemModel

class AdbViewHolder(
    itemView: View
) : ItemViewHolder(itemView) {
    private val image: ImageView

    init {
        image = itemView.findViewById(R.id.adb_img)
    }

    override fun bind(itemModel: ItemModel) {
        val item = itemModel as AdbItemModel
        image.setImageResource(item.adbState.imgId)
        image.setOnClickListener { item.onClick(adapterPosition) }
    }
}
