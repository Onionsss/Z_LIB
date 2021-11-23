package com.onion.view

import android.app.Application
import com.onion.view.VIEW.app

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: VIEW
 * Author: 张琦
 * Date: 2021/11/23 10:24
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
object VIEW {

    @JvmStatic
    var app: Application? = null

    @JvmStatic
    fun init(app: Application){
        this.app = app
    }
}

fun getApp(): Application {
    return app!!
}