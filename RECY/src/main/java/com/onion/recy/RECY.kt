package com.onion.recy

import android.app.Application
import com.onion.recy.RECY.app
import com.onion.recy.recycler.BaseRecyclerAdapter

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: RECY
 * Author: 张琦
 * Date: 2021/11/23 10:42
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
object RECY {
    @JvmStatic
    var app: Application? = null

    @JvmStatic
    fun init(app: Application){
        this.app = app

        BaseRecyclerAdapter.setConfig(BR.m)
    }
}


fun getApp(): Application {
    return app!!
}
