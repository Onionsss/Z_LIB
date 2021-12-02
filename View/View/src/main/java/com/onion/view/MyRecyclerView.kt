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
        /**
         * 去掉默认动画
         */
        getItemAnimator()?.setAddDuration(0)
        getItemAnimator()?.setChangeDuration(0)
        getItemAnimator()?.setMoveDuration(0)
        getItemAnimator()?.setRemoveDuration(0)
        val anim = getItemAnimator() as SimpleItemAnimator
        anim.supportsChangeAnimations = false
    }

}