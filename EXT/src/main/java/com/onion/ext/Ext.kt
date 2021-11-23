package com.onion.ext

import android.app.Application
import com.onion.ext.Ext.app

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: Ext
 * Author: 张琦
 * Date: 2021/11/23 10:12
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
object Ext {

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