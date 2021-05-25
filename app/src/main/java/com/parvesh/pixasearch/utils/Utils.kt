package com.parvesh.pixasearch.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.net.URLEncoder
import java.util.regex.Matcher
import java.util.regex.Pattern


object Utils {

    private val TAG: String = "EXPO_DEBUG_TAG"

    fun log(key: String, value: String) {
        Log.d(TAG, "$key : $value")
    }

    fun containsSpecialCharacters(input: String): Boolean {
        val p: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher(input)
        return m.find()
    }

    fun showShortToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun urlEncodeString(string: String): String {
        return URLEncoder.encode(string, "utf-8")
    }

    fun removeSpecialCharacters(input: String): String {
        val re = Regex("[^A-Za-z0-9 ]")
        return re.replace(input, "")
    }

}
