package com.onion.tab.bean

import com.flyco.tablayout.listener.CustomTabEntity
/**
 *  Created by zhangqi on 2019/4/23.
 */
data class TabEntity(val title: String, val selectedIcon: Int = 0, val unSelectedIcon: Int = 0): CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}