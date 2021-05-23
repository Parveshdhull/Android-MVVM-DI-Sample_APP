package com.parvesh.pixasearch.utils

import android.util.Log

object Utils {

    private val TAG: String = "EXPO_DEBUG_TAG"

    fun log(key: String, value: String) {
        Log.d(TAG, "$key : $value")
    }


}
