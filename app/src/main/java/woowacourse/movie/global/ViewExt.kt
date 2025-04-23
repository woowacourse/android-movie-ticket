package woowacourse.movie.global

import android.graphics.BitmapFactory
import android.widget.ImageView
import woowacourse.movie.R

fun ImageView.setImage(url: String) {
    runCatching {
        val imageByteStream = resources.assets.open(url)
        setImageBitmap(BitmapFactory.decodeStream(imageByteStream))
    }.onFailure {
        setImageResource(R.drawable.movie_poster)
    }
}
