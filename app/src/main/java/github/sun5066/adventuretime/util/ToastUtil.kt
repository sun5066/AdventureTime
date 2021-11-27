package github.sun5066.adventuretime.util

import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import github.sun5066.adventuretime.AdventureTimeApplication

object ToastUtil {
    private val toast by lazy { Toast(AdventureTimeApplication.getApplicationContext()) }

    @MainThread
    fun showToast(@StringRes msg: Int, duration: Int) {
        toast.cancel()
        toast.setText(msg)
        toast.duration = duration
        toast.show()
    }
}