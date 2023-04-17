package woowacourse.movie.presentation.extensions

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showBackButton(isShow: Boolean = true) {
    supportActionBar?.setDisplayHomeAsUpEnabled(isShow)
}
