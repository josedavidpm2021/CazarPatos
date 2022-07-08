package com.pallojose.cazarpatos


import android.app.Activity
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileExternalManager(private val actividad: Activity): FileHandler {
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun saveInformation(saveData: Pair<String,String>) {
        val username = saveData.first
        val password = saveData.second
        Log.d(TAG,"Persisting authentication details\nusername->$username" +
                "\npassword -> $password")
        if (isExternalStorageWritable()) {
            FileOutputStream(
                File(
                    actividad.getExternalFilesDir(null),
                    Constantes.SHARED_INFO_FILENAME
                )
            ).bufferedWriter().use { outputStream ->
                Log.d(TAG,"Successful writing")
                outputStream.write(username)
                outputStream.write(System.lineSeparator())
                outputStream.write(password)
            }
        }
    }

    private fun isExternalStorageReadable(): Boolean{
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED)
    }

     fun readInfo(): Pair<String,String>{
        Log.d(TAG,"Reading")
        if (isExternalStorageReadable()){
            FileInputStream(
                File(actividad.getExternalFilesDir(null),
                    Constantes.SHARED_INFO_FILENAME)
            ).bufferedReader().use {
                val datoLeido = it.readText()
                val textArray = datoLeido.split(System.lineSeparator())
                val username = textArray[0]
                val password = textArray[1]
                return Pair(username,password)
            }
        }
        return Pair("","")
    }

    companion object{
        const val TAG = "FileExternalManager"
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        TODO("Not yet implemented")
    }

    override fun ReadInformation(): Pair<String, String> {
        TODO("Not yet implemented")
    }

}