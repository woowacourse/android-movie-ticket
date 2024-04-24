package woowacourse.movie.utils

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity() {
    val presenter: P by lazy { initializePresenter() }

    abstract fun initializePresenter(): P
}
