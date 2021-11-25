package com.onion.http

import android.app.Application

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: Http
 * Author: 张琦
 * Date: 2021/11/25 10:45
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
object Http {

    var HTTP_SUCCESS_CODE = 200
    var HTTP_TOKEN_OUT_CODE = 401
    internal var app: Application? = null

    @JvmStatic
    fun init(application: Application){
        app = application
    }
}