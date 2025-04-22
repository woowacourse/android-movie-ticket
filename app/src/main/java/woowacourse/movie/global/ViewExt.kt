package woowacourse.movie.global

import android.graphics.BitmapFactory
import android.widget.ImageView

fun ImageView.setImage(url: String) {
    val imageByteStream = resources.assets.open(url)
    setImageBitmap(BitmapFactory.decodeStream(imageByteStream))
}
