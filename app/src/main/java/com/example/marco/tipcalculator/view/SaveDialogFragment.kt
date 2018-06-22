package com.example.marco.tipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.marco.tipcalculator.R

class SaveDialogFragment : DialogFragment() {
    interface Callback {
        fun onSaveTip(name: String)
    }

    private var saveTipCallback: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "OnCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        Log.d(TAG, "OnDestroy")
        super.onDestroy()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "OnCreateDialog")

        val saveDialog = context?.let { ctx ->

            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            AlertDialog.Builder(ctx)
                .setView(editText)
                .setNegativeButton(R.string.action_cancel, null)
                .setPositiveButton(R.string.action_save) { _, _ -> onSave(editText) }
                .create()

        }

        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            saveTipCallback?.onSaveTip(text)
        }
    }

    companion object {
        private var TAG = SaveDialogFragment::class.java.simpleName
        val viewId = View.generateViewId()
    }
}
