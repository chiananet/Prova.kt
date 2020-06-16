package com.digital.prova.activity

import android.app.AlertDialog
import android.content.Context

object DialogError {
    fun messageError(s: String?, title: String?, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
                .setMessage(s)
                .setCancelable(false)
                .setPositiveButton("OK!") { dialog, _ ->
                    dialog.cancel()
                }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}