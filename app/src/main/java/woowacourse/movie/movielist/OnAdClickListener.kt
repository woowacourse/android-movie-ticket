package woowacourse.movie.movielist

import android.content.Context
import android.content.Intent
import android.net.Uri
import woowacourse.movie.dto.AdDto

class OnAdClickListener(private val context: Context) : OnClickListener<AdDto> {
    override fun onClick(item: AdDto) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        context.startActivity(intent)
    }
}
