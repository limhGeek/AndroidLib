package com.limh.baselibs.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @author limh
 * @function
 * @date 2019/2/18 10:06
 */
object SpUtils {
    private val FILE_NAME: String = "mcukt"

    fun put(context: Context, key: String, any: Any) {
        val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editer: SharedPreferences.Editor = sp.edit()

        when (any) {
            is String -> editer.putString(key, any)
            is Int -> editer.putInt(key, any)
            is Float -> editer.putFloat(key, any)
            is Long -> editer.putLong(key, any)
            is Boolean -> editer.putBoolean(key, any)
        }
        editer.apply()
    }

    fun get(context: Context, key: String, default: Any?): Any {
        val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        return when (default) {
            is String -> sp.getString(key, default)
            is Int -> sp.getInt(key, default)
            is Float -> sp.getFloat(key, default)
            is Long -> sp.getLong(key, default)
            is Boolean -> sp.getBoolean(key, default)
            else -> ""
        }
    }

    fun clean(context: Context){
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }
}