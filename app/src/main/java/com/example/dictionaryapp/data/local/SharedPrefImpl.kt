package com.example.dictionaryapp.data.local

import android.content.Context

class SharedPrefImpl(context: Context):SharedPref {

    private var preference = context.getSharedPreferences("dictionary.app", Context.MODE_PRIVATE)
    private var editor = preference.edit()

//    override fun saveToken(token: String?) {
//        editor.putString(tokenKey, token)
//        editor.apply()
//    }
//
//    override fun getToken(): String? {
//        return preference.getString(tokenKey, null)
//    }

//    override fun saveFirebaseToken(token: String?) {
//        editor.putString(firebaseTokenKey, token)
//        editor.apply()
//    }
//
//    override fun getFirebaseToken(): String? {
//        return preference.getString(firebaseTokenKey, null)
//    }

    override fun getBooleanValue(key: String): Boolean {
        return preference.getBoolean(key, false)
    }

    override fun getStringValue(key: String): String? {
        return preference.getString(key, null)
    }

    override fun getIntValue(key: String): Int {
        return preference.getInt(key, 0)
    }

    override fun getLongValue(key: String): Long {
        return preference.getLong(key, 0L)
    }

    override fun getDoubleValue(key: String): Double {
        return preference.getFloat(key, 0F).toDouble()
    }

    override fun saveValue(key: String, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    override fun saveValue(key: String, value: Int?) {
        editor.putInt(key, value ?: 0)
        editor.apply()
    }

    override fun saveValue(key: String, value: Long?) {
        editor.putLong(key, value ?: 0L)
    }

    override fun saveValue(key: String, value: Double?) {
        editor.putFloat(key, value?.toFloat() ?: 0F)
        editor.apply()
    }

    override fun saveValue(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun clearSharedPref() {
//        val firebaseToken = getFirebaseToken()
        editor.clear().apply()
//        saveFirebaseToken(firebaseToken)
    }
}