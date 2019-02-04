package com.labralab.mkbpro10.implementation.store

import android.content.Context
import android.content.SharedPreferences
import com.labralab.mkbpro10.implementation.utils.SerializationUtils

import com.labralab.mkbpro10.model.store.PreferenceManager

import java.io.IOException
import java.io.Serializable
import java.util.ArrayList

import javax.inject.Inject

class PreferenceManagerImpl @Inject
constructor(paramContext: Context, name: String) : PreferenceManager {

    private val preferences: SharedPreferences

    init {
        preferences = paramContext.getSharedPreferences(name, 0)
    }

    override fun delete(key: String) {
        val localEditor = this.preferences.edit()
        localEditor.remove(key)
        localEditor.apply()
    }

    override fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    override fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    override fun getInt(key: String, value: Int): Int {
        return preferences.getInt(key, value)
    }

    override fun getString(key: String): String {
        return preferences.getString(key, null)
    }

    override fun getString(key: String, value: String): String {
        return preferences.getString(key, value)
    }

    override fun getLong(key: String): Long {
        return preferences.getLong(key, 0L)
    }

    override fun getFloat(key: String): Float {
        return preferences.getFloat(key, 0f)
    }

    override fun getIntegerList(key: String): List<Int> {
        try {
            val arrayOfString = this.preferences.getString(key, null)!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val localArrayList = ArrayList<Int>(arrayOfString.size)

            for (anArrayOfString in arrayOfString) {
                localArrayList.add(Integer.parseInt(anArrayOfString))
            }
            return localArrayList
        } catch (e: Exception) {
            return emptyList()
        }

    }

    @Throws(IOException::class)
    override fun getSerializable(key: String): Serializable? {
        val storedString = preferences.getString(key, null) ?: return null

        return SerializationUtils.stringToObject(storedString)
    }

    override fun set(key: String, value: Int) {
        val localEditor = preferences.edit()
        localEditor.putInt(key, value)
        localEditor.apply()
    }

    override fun set(key: String, value: Float) {
        val localEditor = preferences.edit()
        localEditor.putFloat(key, value)
        localEditor.apply()
    }

    override fun set(key: String, paramLong: Long) {
        val localEditor = preferences.edit()
        localEditor.putLong(key, paramLong)
        localEditor.apply()
    }

    override fun set(key1: String, key2: String) {
        val localEditor = preferences.edit()
        localEditor.putString(key1, key2)
        localEditor.apply()
    }

    override fun set(key: String, value: Boolean?) {
        val localEditor = preferences.edit()
        localEditor.putBoolean(key, value!!)
        localEditor.apply()
    }

    override fun set(key: String, value: Serializable) {
        val localEditor = preferences.edit()
        localEditor.putString(key, SerializationUtils.objectToString(value))
        localEditor.apply()
    }

    override fun setIntegerList(key: String, paramList: List<Int>) {
        val localStringBuilder = StringBuilder()

        for (localInteger in paramList) {
            localStringBuilder.append(localInteger).append(",")
        }
        val str = localStringBuilder.substring(0, -1 + localStringBuilder.length)
        val localEditor = preferences.edit()
        localEditor.putString(key, str)
        localEditor.apply()
    }

    override fun contains(key: String): Boolean {
        return preferences.contains(key)
    }
}