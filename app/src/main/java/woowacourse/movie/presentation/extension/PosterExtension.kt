package woowacourse.movie.presentation.extension

import android.widget.ImageView
import woowacourse.movie.domain.model.Poster

fun Poster.setImage(view: ImageView) {
    when (this) {
        is Poster.Resource -> view.setImageResource(this.resId)
        is Poster.Url -> {
            // 포스터 이미지 로드
        }
    }
}
