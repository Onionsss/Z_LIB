package com.onion.ext.view

import android.view.ViewGroup
import android.widget.ImageView

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: ImageView
 * Author: 张琦
 * Date: 2021/11/23 10:05
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun ImageView.setSize(width: Int, w: Float, h: Float){
    val height: Float = width / w * h
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    params.height = height.toInt()
    params.width = width
    this.layoutParams = params
}