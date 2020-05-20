package com.xiaoqiang.library_common.utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.*


/**
 * @author 小强
 *
 * @time 2020/5/13  8:28
 *
 * @desc Gson工具类
 *
 */


class GsonUtil {

    companion object {

        private var mGson: Gson? = null

        init {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(JsonObject::class.java,
                JsonDeserializer<Any?> { jsonElement, type, jsonDeserializationContext -> jsonElement.asJsonObject })

            mGson = builder.disableHtmlEscaping().create()
        }

        fun toJson(`object`: Any?): String? {
            return if (`object` == null) {
                null
            } else mGson!!.toJson(`object`)
        }

        fun <T> fromJson(content: String, clazz: Class<T>?): T? {
            return if (content.isEmpty() || clazz == null) {
                null
            } else try {
                mGson!!.fromJson(content, clazz)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                null
            }
        }
        fun <T> fromJson(content: String, token: TypeToken<T>?): T? {
            return if (content.isEmpty() || token == null) {
                null
            } else try {
                mGson?.fromJson(content, token.type) as T
            } catch (e: JsonSyntaxException) {
                null
            }
        }

//        fun <T> fromJson(content: String, token: TypeToken<T>?): T? {
//            return if (content.isEmpty() || token == null) {
//                null
//            } else try {
//                mGson?.fromJson(content, token.type)
//            } catch (e: JsonSyntaxException) {
//                null
//            }
//        }

        /**
         * 解析从服务器端返回的字符串转为JsonList封装类型
         */
        fun <T> fromJsonList(
            content: String,
            cls: Class<T>?
        ): List<T>? {
            var list: List<T> = ArrayList()
            if (content.isEmpty() || cls == null) {
                return null
            }
            list = try {
                mGson!!.fromJson(
                    content,
                    object : TypeToken<List<T>?>() {}.type
                )
            } catch (e: Exception) {
                return null
            }
            return list
        }

        /**
         * 解析从服务器端返回的字符串转为pubList封装类型
         */
        fun <T> JsonToPubs(
            json: String?,
            type: Class<Array<T>?>?
        ): List<T>? {
            val list = mGson!!.fromJson(json, type)!!
            return Arrays.asList(*list)
        }


        /**
         * 把json转成对应的类型。适合用于自定义数据类型，如ArrayList<Foo>等
         * @param content json
         * @param type 自定义类型的token。使用方法如下
         * Type listType = new TypeToken<ArrayList></ArrayList><Foo>>(){}.getType();
         * @param <T>
         * @return 对应类型的对象
        </T></Foo></Foo> */
        fun <T> fromJson(content: String, type: Type?): T? {
            if (!content.isEmpty() && type != null) {
                try {
                    return mGson?.fromJson(content, type)
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun toMap(obj: Any?): Map<String?, Any?>? {
            val element = mGson?.toJsonTree(obj)
            return mGson?.fromJson<Map<String?, *>>(
                element,
                MutableMap::class.java
            )
        }

        fun <T> fromObject(obj: Any?, clazz: Class<T>?): T? {
            val element = mGson?.toJsonTree(obj)
            return mGson?.fromJson(element, clazz)
        }

        fun <T> fromObject(obj: Any?, token: TypeToken<T>): T? {
            val element = mGson?.toJsonTree(obj)
            return mGson?.fromJson(element, token.type)
        }

        fun getMap(
            map: Map<String?, Any?>?,
            key: String?
        ): Map<*, *>? {
            if (map == null || key == null) {
                return null
            }
            val value = map[key]
            return if (value is Map<*, *>) {
                value
            } else null
        }

        fun getLong(
            map: Map<String?, Any?>?,
            key: String?
        ): Long? {
            if (map == null || key == null) {
                return null
            }
            val value = map[key] ?: return null
            return if (value is Number) {
                value.toLong()
            } else try {
                value.toString().toLong()
            } catch (e: NumberFormatException) {
                null
            }
        }

        fun getLongList(
            map: Map<String?, Any?>?,
            key: String?
        ): MutableList<Any?> {
            if (map == null || key == null) {
                return Collections.EMPTY_LIST
            }
            val value = map[key] ?: return Collections.EMPTY_LIST
            if (value is List<*>) {
                val longValues: MutableList<Long?> = ArrayList()
                for (i in value) {
                    if (i is Number) {
                        longValues.add(i.toLong())
                    }
                }
                return longValues.toMutableList()
            }
            return Collections.EMPTY_LIST
        }

        /**
         * 从json中搜索，根据键的名字，返回值。
         * @param json
         * @param name json中的键名
         * @return Object
         */
        fun findObject(json: String, name: String): Any? {
            var `object`: Any? = null
            if (json.isEmpty() or name.isEmpty()) {
                return null
            }
            try {
                val jsonobject = JSONObject(json)
                `object` = if (!jsonobject.has(name)) {
                    return null
                } else {
                    jsonobject[name]
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return `object`
        }
    }

    private constructor() {

    }


}

