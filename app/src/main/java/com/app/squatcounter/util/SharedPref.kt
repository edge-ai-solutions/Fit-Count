package com.app.squatcounter.util

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.app.squatcounter.SportChallengeApplication
import com.app.squatcounter.domain.PlayerModel
import com.google.gson.reflect.TypeToken


object SharedPref {

    private const val SHARED_PREFS_NAME = "squatcounter"
    public const val APP_USER_DATA = "app_user_data"
    private const val TV_CONFIG_DATA = "tv_config_data"
    private const val IS_USER_LOGIN = "is_user_logged_in"
    private const val IS_USB_GRANTED = "is_usb_granted"
    private const val AUTH_TOKEN = "auth_token"
    private const val USER_TYPE = "user_type"
    private var appPreference: SharedPref? = null

    private var sharedPreferences: SharedPreferences = SportChallengeApplication.getInstance().getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE
    )

    fun save(key: String, value: Any?) {
        val editor = sharedPreferences.edit()
        //  editor.clear()
        when {
            value is Boolean -> editor.putBoolean(key, (value as Boolean?)!!)
            value is Int -> editor.putInt(key, (value as Int?)!!)
            value is Float -> editor.putFloat(key, (value as Float?)!!)
            value is Long -> editor.putLong(key, (value as Long?)!!)
            value is String -> editor.putString(key, value as String?)
            value is Enum<*> -> editor.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.apply()
        editor.commit()
    }

    fun getInstance(context: Context?): SharedPref? {
        if (appPreference == null) {
            appPreference = SharedPref
        }
        return appPreference
    }

    fun saveString(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value)
            .apply()
    }

    fun delete(key: String) {
        if (has(key)) {
            getEditor().remove(key)
            getEditor().commit()
        }
    }

    fun clearData() {
        sharedPreferences.edit().clear().commit()
    }

    fun getString(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }


    fun setLabelValue(context: Context, key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value)
            .apply()
    }


    fun setItemJsonArray(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getItemJsonArray(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }



    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String): T {
        return sharedPreferences.all[key] as T
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }

    private fun has(key: String): Boolean {
        return sharedPreferences.contains(key)
    }


   /* fun saveUser(user: Result) {
        save(APP_USER_DATA, Gson().toJson(user))
    }*/

    fun saveSquatCountData(breakTypeList: List<PlayerModel>?) {
        sharedPreferences.edit().putString(
            APP_USER_DATA,
            Gson().toJson(breakTypeList)
        ).apply()
    }

    fun getSquatCountData(): List<PlayerModel>? {
        val type = object : TypeToken<List<PlayerModel>>() {}.type
        return Gson().fromJson<List<PlayerModel>>(sharedPreferences.getString(APP_USER_DATA, "[]"), type)
        /*  return Gson().fromJson(
              sharedPreferences.getString(
                  BREAK_TYPELIST_DATA,
                  "{}"
              ),
              EmployeLoginDetailModel::class.java
          )*/
    }

    fun saveBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    //fun getUser(): Result = Gson().fromJson(get(APP_USER_DATA, "{}"), Result::class.java)


    //fun getTVConfig(): TVConfigData = Gson().fromJson(get(TV_CONFIG_DATA, "{}"), TVConfigData::class.java)
    fun isUserLoggedIn() = get(IS_USER_LOGIN, false)

    fun setUserLoggedIn(boolean: Boolean) {
        save(IS_USER_LOGIN, boolean)
    }

    fun isFirstPlay() = get(IS_USER_LOGIN, false)

    fun setFirstPlay(boolean: Boolean) {
        save(IS_USER_LOGIN, boolean)
    }

    fun getCurLoginUserType(): String = get(USER_TYPE, "")

    public fun logout() {
        delete(IS_USER_LOGIN)
        delete(APP_USER_DATA)
        delete(AUTH_TOKEN)
    }
}