package com.onion.http.exception

import android.util.MalformedJsonException
import com.google.gson.JsonSyntaxException
import com.onion.logger.Logger
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Copyright (C), 2021-2021, 游加科技
 * FileName: AppException
 * Author: EDZ by 张琦
 * Date: 2021/4/10 17:09
 * Description:
 */
class AppException(throwable: Throwable): Throwable() {
    var code: Int = 0
    var msg: String = "服务器繁忙"

    init {
        Logger.i(throwable.message)
        when(throwable){
            is SocketTimeoutException,
            is ConnectException -> {
                msg = "请求超时"
            }
            is JsonSyntaxException,
            is MalformedJsonException -> {
                msg = "服务器数据异常"
            }
            is HttpException -> {
                msg = "服务器繁忙"
            }
        }
    }

}