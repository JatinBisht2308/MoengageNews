package com.example.newsapp

class Utils {
    companion object {
        const val API_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"
        const val API_RESPONSE_ERROR_TAG = "error occurred"
        private const val REGEX_TO_REMOVE = "\\[\\+\\d+ chars\\]$"
        private const val INPUT_DATE_AND_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val OUTPUT_DATE_AND_TIME_FORMAT = "dd-MMM-yyyy\nhh:mm:ss a"
        const val NOT_AVAILABLE = "Time Not Available"
        val regex = Regex(REGEX_TO_REMOVE)
        const val SORT_BY = "sort_by"
        const val NOTIFICATION_REQUEST_CODE = 0
        const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL"
        const val NOTIFICATION_CHANNEL_NAME = "NEWS_NOTIFICATION"
        const val MOENGAGE_APP_ID = "48VL1XKY8O8FKT6MO00XGEWA"
        const val MO_APP_VERSION_PREF_KEY = "APP_VERSION"
        const val DEFAULT_SAVED_APP_VERSION = -1
        const val NEWS_APP_LOG = "NEWS_APP_MOENGAGE_LOG"
        const val IMAGE = "0"
        const val TITLE = "1"
        const val DESCRIPTON = "2"
        const val BUTTON = "3"
        const val CARD_BACKGROUND_COLOR = "bgColor"
    }
}