package com.onion.ext.view

import android.view.View
import android.view.ViewGroup

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: ViewExt
 * Author: 张琦
 * Date: 2021/11/23 9:58
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun View.setSize(width: Int, w: Float, h: Float){
    val height: Float = width / w * h
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    params.height = height.toInt()
    params.width = width
    this.layoutParams = params
}

fun View.gone(): View{
    this.visibility = View.GONE
    return this
}

fun View.show(): View{
    this.visibility = View.VISIBLE
    return this
}

fun View.hide(): View {
    this.visibility = View.INVISIBLE
    return this
}