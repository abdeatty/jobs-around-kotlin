package com.android.airbag.helpers

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private val SHARED_NAME = "myPrefs"


    // properties
    private val SOME_STRING_VALUE = "SOME_STRING_VALUE"
    // other properties...


    fun doesContain(context: Context, value: String): Boolean {
        return getSharedPreferences(context).contains(value)

    }

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }


    fun getStringValue(context: Context, fetchedString: String): String? {
        return getSharedPreferences(context).getString(fetchedString, null)
    }

    fun setStringValue(context: Context, key: String, value: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getFloatValue(context: Context, fetchedString: String): Float {
        return getSharedPreferences(context).getFloat(fetchedString, 0f)
    }

    fun setFloatValue(context: Context, key: String, value: Float) {
        val editor = getSharedPreferences(context).edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getBooleanValue(context: Context, fetchedString: String): Boolean {
        return getSharedPreferences(context).getBoolean(fetchedString, false)
    }

    fun setBooleanValue(context: Context, key: String, value: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setIntValue(context: Context, key: String, value: Int) {
        val editor = getSharedPreferences(context).edit()
        editor.putInt(key, value)
        editor.apply()
    }


    fun removeValue(context: Context, key: String) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(key)
        editor.apply()
    }


    fun getIntValue(context: Context, fetchedInt: String): Int {
        return getSharedPreferences(context).getInt(fetchedInt, 0)
    }

    fun clearShared(context: Context) {
        getSharedPreferences(context).edit().clear().apply()
    }

    // other getters/setters

}
