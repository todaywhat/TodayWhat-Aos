package com.onmi.domain.exception

sealed interface RemoteError {
    val code: Int
    val message: String

    data object MissingRequiredParameter : RemoteError {
        override val code = 300
        override val message = "필수 값이 누락되어 있습니다. 요청인자를 참고 하십시오."
    }

    data object InvalidRemoteKey : RemoteError {
        override val code = 290
        override val message = "인증키가 유효하지 않습니다. 인증키가 없는 경우, 홈페이지에서 인증키를 신청하십시오."
    }

    data object ServiceNotFound : RemoteError {
        override val code = 310
        override val message = "서비스를 찾을 수 없습니다. 요청인자 중 SERVICE를 확인하십시오."
    }

    data object InvalidRequestType : RemoteError {
        override val code = 333
        override val message = "요청위치 값의 타입이 유효하지 않습니다.요청위치 값은 정수를 입력하세요."
    }

    data object RequestLimitExceeded : RemoteError {
        override val code = 336
        override val message = "데이터요청은 한번에 최대 1,000건을 넘을 수 없습니다."
    }

    data object DailyTrafficExceeded : RemoteError {
        override val code = 337
        override val message = "일별 트래픽 제한을 넘은 실행입니다. 오늘은 더이상 실행할 수 없습니다."
    }

    data object InternalServerError : RemoteError {
        override val code = 500
        override val message = "서버 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    }

    data object DatabaseConnectionError : RemoteError {
        override val code = 600
        override val message = "데이터베이스 연결 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    }

    data object SQLSyntaxError : RemoteError {
        override val code = 601
        override val message = "SQL 문장 오류 입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    }
}