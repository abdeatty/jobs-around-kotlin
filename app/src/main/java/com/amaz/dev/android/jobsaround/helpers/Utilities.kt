package com.amaz.dev.android.jobsaround.helpers

import android.content.Context
import android.graphics.Typeface
import android.icu.text.MessageFormat
import android.os.Build
import android.util.Log

object Utilities {


    fun ovverideAppFont(context: Context , defaultFontNameToOverride :String , customFontFileNameInAssets : String){

        val customFontTypeFace = Typeface.createFromAsset(context.assets ,customFontFileNameInAssets)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            val map = HashMap<String, Typeface>()
            map["regular"] = customFontTypeFace

            try {
                 val staticField = Typeface::class.java.getDeclaredField("sSystemFontMap")
                staticField.isAccessible = true
                staticField.set(null, map)

            } catch (e :NoSuchFieldException) {
                e.printStackTrace()
            } catch (e : IllegalAccessException) {
                e.printStackTrace()
            }
        }else {
            try {
                val defaultFontTypefaceField =
                    Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
                defaultFontTypefaceField.isAccessible = true
                defaultFontTypefaceField.set(null, customFontTypeFace)
            } catch (e: Exception) {
                Log.e(
                    Test::class.java.simpleName,
                    "Can not set custom font $customFontFileNameInAssets instead of $defaultFontNameToOverride"
                )
            }

        }
    }
}