package com.atruskova.itunesapitesttask.data

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DataConverter {
        fun dateToString (date: Date?): String {
            var result =
                try {
                    getSimpleDateFormat.format(date)
                } catch (e: Exception) {
                    ""
                }
            return result

        }

    @JvmStatic
    fun getStringDateTimeForView (date: Date?): String{
            var result =
                try {
                    simpleDateTimeFormatForView.format(date)
                } catch (e: Exception) {
                    ""
                }
            return result
        }

        fun stringToDate (dateString: String?): Date? {
            var date =
                try {
                    getSimpleDateFormat.parse(dateString)
                } catch (e: Exception) {
                    null
                }
            return date
        }
        var  getSimpleDateFormat: SimpleDateFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        var simpleDateTimeFormatForView: SimpleDateFormat =  SimpleDateFormat("dd.MM.yyyy HH:mm")


}