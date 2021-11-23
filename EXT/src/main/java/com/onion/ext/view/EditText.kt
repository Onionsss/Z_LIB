package com.onion.ext.view

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.*

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: EditText
 * Author: 张琦
 * Date: 2021/11/23 10:04
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
/**
 * editText
 */
fun EditText.toEnd(){
    val length = this.text.toString().length
    this.setSelection(length)
}

fun EditText.hideKeyBoard(ctx: Context){
    val imm: InputMethodManager =
        ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
    imm.hideSoftInputFromWindow(this.windowToken,0)
}

fun EditText.openKeyBoard(ctx: Context){

    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    val timer = Timer()
    timer.schedule(object: TimerTask(){
        override fun run() {
            val imm: InputMethodManager =
                ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this@openKeyBoard,0)
            toEnd()
        }
    },200)
}