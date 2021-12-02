package com.onion.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout

/**
 * Copyright (C), 2019-2020, 南通筹友
 * FileName: ListBottomView
 * Author: OnionMac by 张琦
 * Date: 2020-05-07 16:16
 * Description:
 */
class ListBottomView(ctx: Context): RelativeLayout(ctx) {

    init {
        LayoutInflater.from(context).inflate(R.layout.live_list_bottom_view,this)
    }

}