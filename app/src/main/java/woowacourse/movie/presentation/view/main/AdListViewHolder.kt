package woowacourse.movie.presentation.view.main

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.ItemAdvertiseBinding

class AdListViewHolder(
    private val binding: ItemAdvertiseBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val advertise = binding.ivAdvertise
    fun bind(ad: Drawable) {
        advertise.setImageDrawable(ad)
    }
}
