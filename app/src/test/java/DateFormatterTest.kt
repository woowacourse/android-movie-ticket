import junit.framework.TestCase.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.DateFormatter
import java.time.LocalDate

@RunWith(JUnitParamsRunner::class)
class DateFormatterTest {
    @Test
    @Parameters(
        "2023,8,1,2023.8.1",
        "2023,10,1,2023.10.1",
        "2023,9,10,2023.9.10",
        "2023,10,10,2023.10.10",
    )
    fun `LocalDate인자가_주어지면_포멧된_String값을_반환한다`(
        year: Int,
        month: Int,
        day: Int,
        formattedDate: String
    ) {
        // date format = "yyyy.M.d"
        val actual = DateFormatter.format(LocalDate.of(year, month, day))
        assertEquals(actual, formattedDate)
    }
}
