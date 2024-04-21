package woowacourse.movie.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity() {
    val presenter: P by lazy { initializePresenter() }

    abstract fun initializePresenter(): P
}
