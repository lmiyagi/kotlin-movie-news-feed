package br.com.leonardomiyagi.cinemablendfeed.utils

import android.content.Context
import androidx.annotation.StringRes
import br.com.leonardomiyagi.cinemablendfeed.R

/**
 * Created by lmiyagi on 21/02/18.
 */
class PlaceholderData(val show: Boolean,
                      val loading: Boolean,
                      val text: String? = null,
                      val action: Runnable? = null) {

    fun runAction() {
        action?.run()
    }

    companion object {

        fun loading(context: Context): PlaceholderData {
            return PlaceholderData(true, true, context.getString(R.string.global_loading), null)
        }

        fun error(context: Context, tryAgainAction: Runnable): PlaceholderData {
            return PlaceholderData(true, false, context.getString(R.string.global_error), tryAgainAction)
        }

        fun error(context: Context, @StringRes message: Int, tryAgainAction: Runnable): PlaceholderData {
            return PlaceholderData(true, false, context.getString(message), tryAgainAction)
        }

        fun error(message: String, tryAgainAction: Runnable): PlaceholderData {
            return PlaceholderData(true, false, message, tryAgainAction)
        }

        fun hide(): PlaceholderData? {
            return PlaceholderData(false, false, null, null)
        }
    }
}