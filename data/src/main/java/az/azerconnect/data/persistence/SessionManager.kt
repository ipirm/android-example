package az.azerconnect.data.persistence

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


object SessionManager {
    private const val KEY_TOKEN = "token"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_LOGGED_IN = "logged_in"
    private const val KEY_LANGUAGE = "language"

    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref =
            EncryptedSharedPreferences.create(
                context, "secret_shared_prefs",
                createMasterKey(context),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    var loggedIn
        get() = sharedPref.getBoolean(KEY_LOGGED_IN, false)
        set(value) {
            sharedPref.edit { putBoolean(KEY_LOGGED_IN, value) }
        }

    var token
        get() = sharedPref.getString(KEY_TOKEN, "") ?: ""
        set(value) {
            sharedPref.edit { putString(KEY_TOKEN, value) }
        }

    var userId
        get() = sharedPref.getInt(KEY_USER_ID, 0)
        set(value) {
            sharedPref.edit { putInt(KEY_USER_ID, value) }
        }

    var language
        get() = sharedPref.getString(KEY_LANGUAGE, "en") ?: "en"
        set(value) {
            sharedPref.edit { putString(KEY_LANGUAGE, value) }
        }

    fun clearData() {
        sharedPref.edit().clear().apply()
    }

    private fun createMasterKey(context: Context) =
        MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
}