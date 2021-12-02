package com.onion.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Copyright (C), 2021-2021, 游加科技
 * FileName: NoScrollRecyclerView
 * Author: EDZ by 张琦
 * Date: 2021/5/25 9:54
 * Description:
 */
class NoScrollRecyclerView(ctx: Context, attrs: AttributeSet): MyRecyclerView(ctx, attrs) {

    private var mIsScroll = true

    fun setScroll(isScroll: Boolean) {
        this.mIsScroll = isScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (mIsScroll) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (mIsScroll) {
            super.onTouchEvent(ev)
        } else true

    }
}