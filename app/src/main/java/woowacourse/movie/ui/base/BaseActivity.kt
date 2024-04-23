package woowacourse.movie.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P> : AppCompatActivity() {
    val presenter: P by lazy { initializePresenter() }

    abstract fun initializePresenter(): P
}
