package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.AdvState

object AdvRepository {
    fun allAdv(): List<AdvState> = adbs.toList()

    private val adbs: List<AdvState> = List(3) {
        AdvState(
            R.drawable.adv_wooteco,
            "배달의민족 운영사인 우아한형제들에서 운영하는 개발자 교육 프로그램.\n" +
                "\n" +
                "NHN NEXT 교수 출신인 박재성(자바지기)의 주도로 설립되었으며, 삼성 청년 SW 아카데미와 같이 실질적으로는 무료로 교육을 제공하나, 명목상으로 각 레벨당 100만원의 강의료가 책정되어 있으며 중도 포기시 납부해야 한다.\n" +
                "\n" +
                "운영 비용은 기존에는 우아한형제들 측에서 전액 부담하였으나 2021년부터 K-Digital Training 사업의 지원을 받아 일정 부분 국비 지원을 받고 있다.\n" +
                "\n" +
                "프론트, 백엔드, 모바일 안드로이드 교육 과정이 있다.\n" +
                "\n" +
                "삼성 청년 SW 아카데미 (SAFFY) 와 차별화되는 점은 매년 전국에서 50명 내외(백엔드 기준, 3기 기준 프론트엔드는 절반인 25명)를 선발하여 운영하는 소수정예식이라는 점 이다. 3기 기준으로 경쟁률이 약 20:1 가량으로 알려져있다. 4기 (2022년) 부터 모집인원이 2배로 증가하여 백엔드 기준 100명 내외, 프론트엔드 기준 50명 내외로 선발 인원이 확대되었다.\n" +
                "\n" +
                "수료자들중 약 40%는 우아한형제들 입사하고, 나머지 인원은 대부분 네카라쿠배로 일컬어지는 IT 대기업이나 유수의 스타트업에 입사한다."
        )
    }
}
