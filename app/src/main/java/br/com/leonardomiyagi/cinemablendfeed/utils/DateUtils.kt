package br.com.leonardomiyagi.cinemablendfeed.utils

import android.content.Context
import br.com.leonardomiyagi.cinemablendfeed.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lmiyagi on 11/6/17.
 */
class DateUtils {

    companion object {
        const val API_DATE_FORMAT = "E, dd MMM yyy HH:mm:ss ZZ"
        fun formatApiDate(context: Context, date: String): String {
            val date: Date = SimpleDateFormat(API_DATE_FORMAT, Locale.US).parse(date)
            return SimpleDateFormat(context.getString(R.string.global_date_format), Locale.getDefault()).format(date)
        }
    }
}