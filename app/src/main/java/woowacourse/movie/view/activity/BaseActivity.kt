package woowacourse.movie.view.activity

import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.presenter.BasePresenter

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity() {
    val presenter: P by lazy { initializePresenter() }

    abstract fun initializePresenter(): P
}
