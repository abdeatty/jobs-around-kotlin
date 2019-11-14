package com.amaz.dev.android.jobsaround.helpers

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.icu.text.MessageFormat
import android.os.Build
import android.util.Log

object Utilities {


    fun getGenderName(genderId : Int) : String{

        return when(genderId){

            1 -> "ذكر"
            2 -> "أنثى"
            else -> "غير محدد"
        }
    }
}