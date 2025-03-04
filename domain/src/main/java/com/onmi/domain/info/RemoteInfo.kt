package com.onmi.domain.info

enum class RemoteInfo(
    val code: Int,
    val message: String,
) {
    SUCCESS(
        code = 0,
        message = "정상 처리되었습니다."
    ),
    REFERENCE_ONLY(
        code = 100,
        message = "(해당 자료는 단순 참고용으로만 활용하시기 바랍니다.)"
    ),

    DATA_NOT_FOUND(
        code = 200,
        message = "해당하는 데이터가 없습니다."
    ),
    API_KEY_RESTRICTED(
        code = 300,
        message = "관리자에 의해 인증키 사용이 제한되었습니다."
    ),
    UNKNOWN_INFO(
        code = 999,
        message = "알 수 없는 정보입니다."
    );

    companion object {
        fun find(code: Int): RemoteInfo {
            return entries.find { it.code == code } ?: UNKNOWN_INFO
        }
    }
}