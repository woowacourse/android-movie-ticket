package woowacourse.movie.presentation.extension

import android.widget.ImageView
import woowacourse.movie.presentation.model.PosterUiModel

fun PosterUiModel.setImage(view: ImageView) {
    when (this) {
        is PosterUiModel.Resource -> view.setImageResource(this.resId)
        is PosterUiModel.Url -> {
            // 포스터 이미지 로드
        }
    }
}
