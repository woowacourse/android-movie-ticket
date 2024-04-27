package woowacourse.movie.seats.view

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.list.view.MovieListActivity.Companion.EXTRA_MOVIE_ID_KEY
import woowacourse.movie.seats.contract.SeatsContract
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.seats.presenter.SeatsPresenter

class SeatsActivity : AppCompatActivity(), SeatsContract.View {
    override val presenter: SeatsContract.Presenter = SeatsPresenter(this)
    private lateinit var seats: TableLayout
    private lateinit var title: TextView
    private lateinit var priceView: TextView
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seats)
        initView()
        initSeats()
        presenter.storeMovieId(intent.getLongExtra(EXTRA_MOVIE_ID_KEY, -1))
        presenter.setMovieTitleInfo()
        presenter.setPriceInfo()
        presenter.setSeatsTextInfo()
        setOnSelectSeat()
    }

    private fun initView() {
        title = findViewById(R.id.seats_movie_title)
        priceView = findViewById(R.id.seats_totla_price)
        confirmButton = findViewById(R.id.confirm_button)
        seats = findViewById(R.id.seats)
    }

    override fun initSeats() {
        seats.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, tableRow ->
            tableRow.children.filterIsInstance<TextView>().forEachIndexed { colIndex, cell ->
                presenter.createSeat(rowIndex, colIndex)
                cell.text = presenter.seat.coordinate
            }
        }
    }

    override fun initSeatsView(info: Seat) {
        seats.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, tableRow ->
            tableRow.children.filterIsInstance<TextView>().forEachIndexed { colIndex, cell ->
                cell.text = info.coordinate
            }
        }
    }

    override fun setOnSelectSeat() {
        seats.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, tableRow ->
            tableRow.children.filterIsInstance<TextView>().forEachIndexed { colIndex, cell ->
                cell.setOnClickListener {
                    presenter.createSeat(rowIndex, colIndex)
                    presenter.setSeatsCellsBackgroundColorInfo()
                }
            }
        }
    }

    override fun setSeatsText(info: Seat) {
        val row = seats.getChildAt(info.rowIndex) as TableRow
        val cell: TextView = row.getChildAt(info.colIndex) as TextView
        cell.text = info.coordinate
    }

    override fun setMovieTitle(info: String) {
        title.text = info
    }

    override fun setTotalPrice(info: Int) {
        priceView.text = TOTAL_PRICE.format(info)
    }

    override fun setSeatCellBackgroundColor(info: Seat) {
        val row = seats.getChildAt(info.rowIndex) as TableRow
        val cell = row.getChildAt(info.colIndex) // cell을 nulllable로 선언해야 하나?
        cell.setBackgroundColor(info.cellBackgroundColor)
    }

    companion object {
        private const val TOTAL_PRICE = "%d원"
    }
}
