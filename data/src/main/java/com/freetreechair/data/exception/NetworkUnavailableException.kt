package com.freetreechair.data.exception

import java.io.IOException

class NetworkUnavailableException(message: String = "네트워크 연결 상태가 불안정합니다.") : IOException(message)
