package com.onion.tab

import android.content.Context
import android.util.AttributeSet
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.onion.tab.bean.TabEntity

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: HomeTabLayout
 * Author: 张琦
 * Date: 2021/11/30 13:58
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
class HomeTabLayout(ctx: Context, attrs: AttributeSet): CommonTabLayout(ctx,attrs) {

    private val mTabNames: ArrayList<Int> = arrayListOf()

    private val mTabIconPress: ArrayList<Int> = arrayListOf()

    private val mTabIconNormal: ArrayList<Int> = arrayListOf()

    fun setTabName(names: ArrayList<Int>): HomeTabLayout {
        mTabNames.clear()
        mTabNames.addAll(names)
        return this
    }

    fun setNormalIcon(normal: ArrayList<Int>): HomeTabLayout {
        mTabIconNormal.addAll(normal)
        return this
    }

    fun setPressIcon(press: ArrayList<Int>): HomeTabLayout {
        mTabIconPress.addAll(press)
        return this
    }

    fun build(){
        val tabData = mTabNames.mapIndexed{
                index, s ->  TabEntity(resources.getString(s),mTabIconPress[index],mTabIconNormal[index])
        }.toMutableList() as ArrayList<CustomTabEntity>

        this.setTabData(tabData)
    }

}