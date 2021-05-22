package com.parvesh.pixasearch.Utils

import android.util.Log

object Util {

    private val TAG: String = "EXPO_DEBUG_TAG"

    fun log(key: String, value: String) {
        Log.d(TAG, "$key : $value")
    }


}
