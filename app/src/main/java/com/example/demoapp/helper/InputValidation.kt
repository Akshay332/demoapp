package com.example.demoapp.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.demoapp.R
import com.google.android.material.textfield.TextInputLayout

/**
 * constructor
 *
 * @param context
 */
class InputValidation (private val context: Context){

    /**
     * method to check InputEditText has Filled? .
     *
     * @param editText
     * @param textInputLayout
     * @param message
     * @return
     */
    fun isInputEditTextFilled(editText: EditText,textInputLayout: TextInputLayout,message: String): Boolean{
        val value = editText.text.toString().trim()
        if (value.isEmpty()){
            textInputLayout.error=message
            hideKeyboard(editText)
            return false
        }else{
            textInputLayout.isErrorEnabled = false
        }
        return true
    }
    /**
     * method to check InputEditText has valid email .
     *
     * @param editText
     * @param textInputLayout
     * @param message
     * @return
     */

    fun isInputEditTextEmail(editText: EditText,textInputLayout: TextInputLayout,message: String):Boolean{
        val value = editText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.error=message
            hideKeyboard(editText)
            return false
        }else{
            textInputLayout.isErrorEnabled = false
        }
        return true
    }
    /**
     * method to check both InputEditText value matches.
     *
     * @param editText1
     * @param editText2
     * @param textInputLayout
     * @param message
     * @return
     */

    fun isInputEditTextMatches(editText1: EditText,editText2: EditText,textInputLayout: TextInputLayout,message: String):Boolean{
        val value1 = editText1.text.toString().trim()
        val value2 = editText2.text.toString().trim()
        if (!value1.contentEquals(value2))
            if (!value1.contentEquals(value2)) {
                textInputLayout.error = message
                hideKeyboard(editText2)
                return false
            } else {
                textInputLayout.isErrorEnabled = false
            }
        return true
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
        private fun hideKeyboard(view: View){
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

    }
}
