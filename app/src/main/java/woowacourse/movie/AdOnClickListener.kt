package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.net.Uri

class AdOnClickListener(private val context: Context) : OnClickListener<Ad> {
    override fun onClick(item: Ad) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        context.startActivity(intent)
    }
}
