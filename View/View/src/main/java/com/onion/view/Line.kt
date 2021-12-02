package com.onion.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

/**
 * Copyright (C), 2020-2021, 游加科技
 * FileName: Line
 * Author: EDZ by 张琦
 * Date: 2020/10/30 15:51
 * Description:
 */
class Line(ctx: Context,attrs: AttributeSet): View(ctx,attrs) {

    init {
        setBackgroundColor(Color.parseColor("#f5f5f5"))
    }
}