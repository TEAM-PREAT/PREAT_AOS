package com.freetreechair.common.exception

class RetrofitFailureStateException(error: String?, val code: Int) : Exception(error)
