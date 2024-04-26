package woowacourse.movie.view

class TicketingActivityErrorTest {
//    @get:Rule
//    var activityRule: ActivityScenarioRule<TicketingActivity> =
//        ActivityScenarioRule<TicketingActivity>(
//            Intent(ApplicationProvider.getApplicationContext(), TicketingActivity::class.java).apply {
//                putExtra("movie_id", -1)
//            },
//        )
//
//    private lateinit var decorView: View
//
//    @Before
//    fun setUp() {
//        activityRule.scenario.onActivity {
//            decorView = it.window.decorView
//        }
//    }
//
//    @Test
//    fun `존재하지_않는_영화_아이디로_영화_정보를_탐색하면_토스트_에러_메시지를_표출한다`() {
//        onView(withText("존재하지 않는 아이디 값입니다."))
//            .inRoot(withDecorView(Matchers.not(decorView)))
//            .check(matches(isDisplayed()))
//    }

//    inner class ToastMatcher(private val message: String) : TypeSafeMatcher<Root?>() {
//        override fun matchesSafely(item: Root?): Boolean {
//            val type: Int? = item?.windowLayoutParams?.get()?.type
//            if (type == WindowManager.LayoutParams.TYPE_TOAST) {
//                val windowToken: IBinder = item.decorView.windowToken
//                val appToken: IBinder = item.decorView.getApplicationWindowToken()
//                if (windowToken === appToken) {
//                    return true
//                }
//            }
//            return false
//        }
//
//        override fun describeTo(description: Description?) {
//            description?.appendText(message)
//        }
//    }
}
