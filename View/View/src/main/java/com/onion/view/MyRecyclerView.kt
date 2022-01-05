package com.onion.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

/**
 * Copyright (C), 2020-2021, 游加科技
 * FileName: MyRecyclerView
 * Author: EDZ by 张琦
 * Date: 2020/10/20 9:43
 * Description:
 */
open class MyRecyclerView(ctx: Context, attrs: AttributeSet): RecyclerView(ctx, attrs) {

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
        val anim = itemAnimator as SimpleItemAnimator
        /**
         * 去掉默认动画
         */
        anim.addDuration = 0
        anim.changeDuration = 0
        anim.moveDuration = 0
        anim.removeDuration = 0
        anim.supportsChangeAnimations = false
    }

}