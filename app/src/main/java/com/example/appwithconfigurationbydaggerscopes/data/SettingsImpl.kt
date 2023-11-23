package com.example.appwithconfigurationbydaggerscopes.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import javax.inject.Inject

class SettingsImpl @Inject constructor(private val sharedPreferences: SharedPreferences): Settings {

    companion object{
        const val VARIABLE = "variable"
        const val BASE_URL = "baseUrl"
    }

    override var variable: Int
        get() = sharedPreferences.getInt(VARIABLE, 0)
        set(value) {
            sharedPreferences.edit {
                putInt(VARIABLE, value)
            }
        }
    override var baseUrl: String
        get() = sharedPreferences.getString(BASE_URL, "") ?: ""
        set(value) {
            sharedPreferences.edit {
                putString(BASE_URL, value)
            }
        }
}