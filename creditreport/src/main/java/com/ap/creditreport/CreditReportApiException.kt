package com.ap.creditreport

/**
 * Exception used for handling exceptions thrown from contacting the API.
 *
 * With more time, we should replace the usage of an exception with a HttpResult that either
 * contains the result or an exception (which we can extract and react to).
 * */
class CreditReportApiException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}