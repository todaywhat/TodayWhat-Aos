package com.onmi.domain.exception

enum class RemoteError(
    val code: Int,
    val message: String,
) {
    MISSING_REQUIRED_PARAMETER(
        code = 300,
        message = "필수 값이 누락되어 있습니다. 요청인자를 참고 하십시오."
    ),
    INVALID_REMOTE_KEY(
        code = 290,
        message = "인증키가 유효하지 않습니다. 인증키가 없는 경우, 홈페이지에서 인증키를 신청하십시오."
    ),
    SERVICE_NOT_FOUND(
        code = 310,
        message = "서비스를 찾을 수 없습니다. 요청인자 중 SERVICE를 확인하십시오."
    ),
    INVALID_REQUEST_TYPE(
        code = 333,
        message = "요청위치 값의 타입이 유효하지 않습니다.요청위치 값은 정수를 입력하세요."
    ),
    REQUEST_LIMIT_EXCEEDED(
        code = 336,
        message = "데이터요청은 한번에 최대 1,000건을 넘을 수 없습니다."
    ),
    DAILY_TRAFFIC_EXCEEDED(
        code = 337,
        message = "일별 트래픽 제한을 넘은 실행입니다. 오늘은 더이상 실행할 수 없습니다."
    ),
    INTERNAL_SERVER_ERROR(
        code = 500,
        message = "서버 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    DATABASE_CONNECTION_ERROR(
        code = 600,
        message = "데이터베이스 연결 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    SQL_SYNTAX_ERROR(
        code = 601,
        message = "SQL 문장 오류 입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    UNKNOWN_ERROR(
        code = 999,
        message = "알 수 없는 에러 발생."
    );

    companion object {
        fun find(code: Int): RemoteError {
            return entries.find { it.code == code } ?: UNKNOWN_ERROR
        }
    }
}