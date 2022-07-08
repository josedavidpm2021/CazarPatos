package com.pallojose.cazarpatos

import android.app.Activity
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.net.URI.create

class EncryptedSharedPreferencesManager(val activity: Activity): FileHandler {

     fun saveInformation(saveData: Pair<String, String>) {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPref = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            activity,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val editor = sharedPref.edit()
        editor.putString(Constantes.LOGIN_KEY, saveData.first)
        editor.putString(Constantes.PASSWORD_KEY,saveData.second)
        editor.apply()
    }

     fun readInfo(): Pair<String, String> {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPref = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            activity,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val email = sharedPref.getString(Constantes.LOGIN_KEY,"").toString()
        val pass = sharedPref.getString(Constantes.PASSWORD_KEY,"").toString()
        return (email to pass)
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        TODO("Not yet implemented")
    }

    override fun ReadInformation(): Pair<String, String> {
        TODO("Not yet implemented")
    }

}