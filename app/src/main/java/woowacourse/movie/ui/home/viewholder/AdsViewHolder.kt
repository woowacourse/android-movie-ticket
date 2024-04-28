package woowacourse.movie.ui.home.viewholder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.movie.Ads

class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.ads_imageview)

    fun bind(ads: Ads) {
        image.setImageResource(ads.imgRes)
        image.setOnClickListener {
            val url: String = ads.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            itemView.context.startActivity(intent)
        }
    }
}
