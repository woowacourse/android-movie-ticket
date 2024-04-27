package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity

class SeatActivity : BaseActivity(), SeatContract.View {
    private lateinit var presenter: SeatPresenter
    
    override fun getLayoutResId(): Int = R.layout.activity_seat
    
    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val movieTicketId = intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1)
        
        presenter = SeatPresenter(this, MovieTicketRepositoryImpl, movieTicketId)
    }
    
    override fun showMessage(message: String) {
        showSnackBar(message)
    }
    
    companion object {
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
    }
}
