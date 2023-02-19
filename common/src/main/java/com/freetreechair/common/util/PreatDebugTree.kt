package com.freetreechair.common.util

import timber.log.Timber

class PreatDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
