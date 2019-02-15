package com.limh.baselibs.utils

import android.util.Log

/**
 * @author limh
 * @function log打印
 * @date 2019/2/15 10:51
 */
object Logs {
    var isDebug = true

    fun setIsDebug(isDebug: Boolean) {
        Logs.isDebug = isDebug
    }

    /**
     *
     * @param tag
     * @param msg
     */
    fun i(tag: String, msg: String?) {
        if (isDebug) {
            Log.i(tag, msg ?: "")
        }
    }

    fun i(`object`: Any, msg: String?) {
        if (isDebug) {
            Log.i(`object`.javaClass.simpleName, msg ?: "")
        }
    }

    fun i(msg: String?) {
        if (isDebug) {
            Log.i(" [INFO] --- ", msg ?: "")
        }
    }

    /**
     *
     * @param tag
     * @param msg
     */
    fun d(tag: String, msg: String?) {
        if (isDebug) {
            Log.d(tag, msg ?: "")
        }
    }

    fun d(`object`: Any, msg: String?) {
        if (isDebug) {
            Log.d(`object`.javaClass.simpleName, msg ?: "")
        }
    }

    fun d(msg: String?) {
        if (isDebug) {
            Log.d(" [DEBUG] --- ", msg ?: "")
        }
    }

    /**
     *
     * @param tag
     * @param msg
     */
    fun w(tag: String, msg: String?) {
        if (isDebug) {
            Log.w(tag, msg ?: "")
        }
    }

    fun w(`object`: Any, msg: String?) {
        if (isDebug) {
            Log.w(`object`.javaClass.simpleName, msg ?: "")
        }
    }

    fun w(msg: String?) {
        if (isDebug) {
            Log.w(" [WARN] --- ", msg ?: "")
        }
    }

    /**
     *
     * @param tag
     * @param msg
     */
    fun e(tag: String, msg: String?) {
        if (isDebug) {
            Log.e(tag, msg ?: "")
        }
    }

    fun e(`object`: Any, msg: String?) {
        if (isDebug) {
            Log.e(`object`.javaClass.simpleName, msg ?: "")
        }
    }

    fun e(msg: String?) {
        if (isDebug) {
            Log.e(" [ERROR] --- ", msg ?: "")
        }
    }

    /**
     *
     * @param tag
     * @param msg
     */
    fun v(tag: String, msg: String?) {
        if (isDebug) {
            Log.v(tag, msg ?: "")
        }
    }

    fun v(`object`: Any, msg: String?) {
        if (isDebug) {
            Log.v(`object`.javaClass.simpleName, msg ?: "")
        }
    }

    fun v(msg: String?) {
        if (isDebug) {
            Log.v(" [VERBOSE] --- ", msg ?: "")
        }
    }
}