package com.onion.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 *  Created by zhangqi on 2019/4/23.
 */
class NoScrollViewPager(context: Context,attributeSet: AttributeSet): ViewPager(context,attributeSet) {

    private var mIsScroll = true

    fun setScroll(isScroll: Boolean) {
        this.mIsScroll = isScroll
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        //分发
        return super.dispatchTouchEvent(ev)
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