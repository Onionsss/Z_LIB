package com.onion.ext.view

import android.widget.Checkable

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: CheckableExt
 * Author: 张琦
 * Date: 2021/11/23 10:03
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun Checkable.check(): Checkable{
    this.isChecked = true
    return this
}

fun Checkable.unCheck(): Checkable{
    this.isChecked = false
    return this
}
