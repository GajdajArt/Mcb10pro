package com.labralab.mkbpro10.implementation.utils

import android.util.Base64

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

object SerializationUtils {

    fun objectToString(`object`: Serializable): String {
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val objectOutputStream = ObjectOutputStream(
                    byteArrayOutputStream)
            objectOutputStream.writeObject(`object`)
            objectOutputStream.close()
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0)
        } catch (e: IOException) {
            throw RuntimeException("Serialize error", e)
        }

    }

    @Throws(IOException::class)
    fun stringToObject(string: String): Serializable {
        try {
            val bytes = Base64.decode(string, 0)
            val objectInputStream = ObjectInputStream(
                    ByteArrayInputStream(bytes))
            return objectInputStream.readObject() as Serializable
        } catch (e: IOException) {
            throw IOException("Deserialize error", e)
        } catch (e: ClassNotFoundException) {
            throw IOException("Deserialize error", e)
        } catch (e: ClassCastException) {
            throw IOException("Deserialize error", e)
        }

    }
}
