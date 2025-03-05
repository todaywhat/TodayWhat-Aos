package com.onmi.domain.exception

import java.io.IOException

data class NeisException(val result: NeisResult) : IOException()
