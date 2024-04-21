package woowacourse.movie.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P> : AppCompatActivity() {
    abstract fun initializePresenter(): P
}
