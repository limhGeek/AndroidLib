package com.limh.baselibs.utils

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException

/**
 * @author limh
 * @function
 * @date 2019/2/15 10:50
 */
object GsonUtils {
    private var gson: Gson? = null

    init {
        gson = Gson()
    }

    fun <T> str2Bean(gsonString: String, cls: Class<T>): T {
        return gson!!.fromJson(gsonString, cls)
    }

    fun bean2Str(obj: Any): String {
        return gson!!.toJson(obj)
    }

    /**
     * gson转数组
     */
    fun <T> str2List(gsonString: String, cls: Class<T>): MutableList<T> {
        val data = ArrayList<T>()
        try {
            val array = JSONArray(gsonString)
            for (i in 0 until array.length()) {
                data.add(str2Bean(array.getString(i), cls))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Logs.i("json", "" + e.toString())
        }
        return data
    }
}