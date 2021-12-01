package com.onion.tab

import android.content.Context
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: TabTitleView
 * Author: 张琦
 * Date: 2021/12/1 13:30
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
class TabTitleView(ctx: Context): SimplePagerTitleView(ctx) {

    override fun onSelected(index: Int, totalCount: Int) {
        super.onSelected(index, totalCount)
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        super.onDeselected(index, totalCount)
    }

}