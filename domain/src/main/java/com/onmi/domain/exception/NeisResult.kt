package com.onmi.domain.exception

enum class NeisResult(
    val code: String,
    val message: String,
) {
    SUCCESS(
        code = "INFO-000",
        message = "정상 처리되었습니다."
    ),
    REFERENCE_ONLY(
        code = "INFO-100",
        message = "(해당 자료는 단순 참고용으로만 활용하시기 바랍니다.)"
    ),
    DATA_NOT_FOUND(
        code = "INFO-200",
        message = "해당하는 데이터가 없습니다."
    ),
    API_KEY_RESTRICTED(
        code = "INFO-300",
        message = "관리자에 의해 인증키 사용이 제한되었습니다."
    ),
    MISSING_REQUIRED_PARAMETER(
        code = "ERROR-300",
        message = "필수 값이 누락되어 있습니다. 요청인자를 참고 하십시오."
    ),
    INVALID_REMOTE_KEY(
        code = "ERROR-290",
        message = "인증키가 유효하지 않습니다. 인증키가 없는 경우, 홈페이지에서 인증키를 신청하십시오."
    ),
    SERVICE_NOT_FOUND(
        code = "ERROR-310",
        message = "서비스를 찾을 수 없습니다. 요청인자 중 SERVICE를 확인하십시오."
    ),
    INVALID_REQUEST_TYPE(
        code = "ERROR-333",
        message = "요청위치 값의 타입이 유효하지 않습니다.요청위치 값은 정수를 입력하세요."
    ),
    REQUEST_LIMIT_EXCEEDED(
        code = "ERROR-336",
        message = "데이터요청은 한번에 최대 1,000건을 넘을 수 없습니다."
    ),
    DAILY_TRAFFIC_EXCEEDED(
        code = "ERROR-337",
        message = "일별 트래픽 제한을 넘은 실행입니다. 오늘은 더이상 실행할 수 없습니다."
    ),
    INTERNAL_SERVER_ERROR(
        code = "ERROR-500",
        message = "서버 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    DATABASE_CONNECTION_ERROR(
        code = "ERROR-600",
        message = "데이터베이스 연결 오류입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    SQL_SYNTAX_ERROR(
        code = "ERROR-601",
        message = "SQL 문장 오류 입니다. 지속적으로 발생시 홈페이지로 문의(Q&A) 바랍니다."
    ),
    UNKNOWN_ERROR(
        code = "ERROR-UNKNOWN",
        message = "알 수 없는 에러 발생."
    );

    companion object {
        fun find(code: String): NeisResult {
            return entries.find { it.code == code } ?: UNKNOWN_ERROR
        }
    }
}