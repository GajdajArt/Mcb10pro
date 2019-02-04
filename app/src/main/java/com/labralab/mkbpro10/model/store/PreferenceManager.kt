package com.labralab.mkbpro10.model.store

import java.io.IOException
import java.io.Serializable

interface PreferenceManager {

    companion object {

        val APP_PREFERENCES_NAME = "APP_PREFERENCES_NAME"
    }

    fun delete(key: String)

    fun getBoolean(key: String): Boolean

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getString(key: String): String

    fun getString(key: String, value: String): String

    fun getInt(key: String): Int

    fun getFloat(key: String): Float

    fun getInt(key: String, value: Int): Int

    fun getIntegerList(key: String): List<Int>

    fun getLong(key: String): Long

    @Throws(IOException::class)
    fun getSerializable(key: String): Serializable?

    operator fun set(key: String, value: Int)

    operator fun set(key: String, `val`: Float)

    operator fun set(key: String, value: Long)

    operator fun set(key: String, value: String)

    operator fun set(key: String, value: Boolean?)

    operator fun set(key: String, value: Serializable)

    fun setIntegerList(key: String, value: List<Int>)

    operator fun contains(key: String): Boolean
}
