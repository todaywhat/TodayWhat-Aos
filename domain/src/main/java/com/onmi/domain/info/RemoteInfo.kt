package com.onmi.domain.info

interface RemoteInfo {
    val code: Int
    val message: String

    data object Success : RemoteInfo {
        override val code = 0
        override val message = "정상 처리되었습니다."
    }

    data object ReferenceOnly : RemoteInfo {
        override val code = 100
        override val message = "(해당 자료는 단순 참고용으로만 활용하시기 바랍니다.)"
    }

    data object DataNotFound : RemoteInfo {
        override val code = 200
        override val message = "해당하는 데이터가 없습니다."
    }

    data object ApiKeyRestricted : RemoteInfo {
        override val code = 300
        override val message = "관리자에 의해 인증키 사용이 제한되었습니다."
    }
}