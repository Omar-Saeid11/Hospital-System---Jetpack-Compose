package com.example.hospitalsystem.core

import android.content.Context
import android.content.SharedPreferences


object UserPreferences {
    private lateinit var mAppContext: Context
    private const val SHARED_PREFERENCES_NAME = "hospital data"
    private const val USER_PHONE = "mobile"
    private const val TYPE = "type"
    private const val USER_NAME = "user name"
    private const val USER_EMAIL = "user email"
    private const val USER_TOKEN = "token"
    private const val USER_ID = "user id"
    private const val USER_ATTENDED = "attended"
    private const val USER_LEAVING = "leaving"
    private const val USER_GENDER = "gender"
    private const val USER_BIRTHDAY = "birthday"
    private const val USER_LOCATION = "location"
    private const val USER_STATUS = "status"

    fun init(appContext: Context) {
        mAppContext = appContext
    }

    private fun getSharedPreferences(): SharedPreferences {
        return mAppContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }


    fun setUserPhone(container: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_PHONE, container).apply()

    }

    fun getUserPhone(): String {
        return getSharedPreferences().getString(USER_PHONE, "")!!


    }

    fun setUserType(container: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(TYPE, container).apply()

    }

    fun getUserType(): String {
        return getSharedPreferences().getString(TYPE, "")!!


    }

    fun setUserEmail(email: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_EMAIL, email).apply()

    }

    fun getUserEmail(): String {
        return getSharedPreferences().getString(USER_EMAIL, "")!!


    }

    fun setUserName(name: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_NAME, name).apply()

    }

    fun getUserName(): String {
        return getSharedPreferences().getString(USER_NAME, "")!!


    }


    fun setUserId(id: Int) {

        val editor = getSharedPreferences().edit()
        editor.putInt(USER_ID, id).apply()

    }

    fun getUserId(): Int {
        return getSharedPreferences().getInt(USER_ID, 0)


    }

    fun setUserTOKEN(id: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_TOKEN, id).apply()

    }

    fun getUserToken(): String? {
        return getSharedPreferences().getString(USER_TOKEN, "")
    }

    fun setUserAttended(id: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_ATTENDED, id).apply()

    }

    fun getUserAttended(): String? {
        return getSharedPreferences().getString(USER_ATTENDED, "")


    }


    fun setUserLeaving(id: String) {

        val editor = getSharedPreferences().edit()
        editor.putString(USER_LEAVING, id).apply()

    }

    fun getUserLeaving(): String? {
        return getSharedPreferences().getString(USER_LEAVING, "")
    }

    fun setUserGender(gender: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_GENDER, gender).apply()

    }

    fun getUserGender(): String? {
        return getSharedPreferences().getString(USER_GENDER, "")
    }

    fun setUserBirthday(birthday: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_BIRTHDAY, birthday).apply()

    }

    fun getUserBirthday(): String? {
        return getSharedPreferences().getString(USER_BIRTHDAY, "")
    }

    fun setUserLocation(location: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_LOCATION, location).apply()

    }

    fun getUserLocation(): String? {
        return getSharedPreferences().getString(USER_LOCATION, "")
    }

    fun setUserStatus(status: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(USER_STATUS, status).apply()

    }

    fun getUserStatus(): String? {
        return getSharedPreferences().getString(USER_STATUS, "")
    }

    fun setDate(date: String) {
        val editor = getSharedPreferences().edit()
        editor.putString("DATE", date).apply()

    }

    fun getDate(): String? {
        return getSharedPreferences().getString("DATE", "")
    }


}
