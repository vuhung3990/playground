package com.tux.playground

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

private const val alertDialogFragmentTitle = "title"
private const val alertDialogFragmentMessage = "message"
private const val alertDialogFragmentPositive = "positive_button"
private const val alertDialogFragmentNegative = "negative_button"

/**
 * A DialogFragment is a fragment that displays a dialog window, floating on top of its activity's window.
 * This fragment contains a Dialog object, which it displays as appropriate based on the fragment's state
 *
 * The advantage of using a DialogFragment is that all the life cycle of the dialog will be handled for you. You will never get the error 'dialog has leaked...' again
 */
class AlertDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

  var positiveButtonListener: (() -> Unit)? = null
  var negativeButtonListener: (() -> Unit)? = null

  companion object {
    fun newInstance(message: String, title: String? = null, positive: String? = null, negative: String? = null,
        positiveEvent: (() -> Unit)? = null, negativeEvent: (() -> Unit)? = null): AlertDialogFragment {
      val fragment = AlertDialogFragment()
      fragment.arguments = Bundle().apply {
        putString(alertDialogFragmentTitle, title)
        putString(alertDialogFragmentMessage, message)
        putString(alertDialogFragmentPositive, positive)
        putString(alertDialogFragmentNegative, negative)
      }

      fragment.positiveButtonListener = positiveEvent
      fragment.negativeButtonListener = negativeEvent

      return fragment
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val title = arguments.getString(alertDialogFragmentTitle)
    val message = arguments.getString(alertDialogFragmentMessage)
    val positiveButton = arguments.getString(alertDialogFragmentPositive)
    val negativeButton = arguments.getString(alertDialogFragmentNegative)

    return AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButton, this)
        .setNegativeButton(negativeButton, this)
        .create()
  }

  override fun onClick(dialog: DialogInterface?, which: Int) {
    dialog?.dismiss()
    when (which) {
      DialogInterface.BUTTON_POSITIVE -> positiveButtonListener?.invoke()
      DialogInterface.BUTTON_NEGATIVE -> negativeButtonListener?.invoke()
      else -> {
      }
    }
  }
}