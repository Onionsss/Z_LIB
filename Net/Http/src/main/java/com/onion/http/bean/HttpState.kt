package com.onion.http.bean

import com.onion.http.Http
import com.onion.http.exception.AppException

/**
 * Copyright (C), 2020-2021, 游加科技
 * FileName: HttpState
 * Author: EDZ by 张琦
 * Date: 2020/9/4 13:43
 * Description:
 */
sealed class HttpState<out T> {

    var httpParams: HttpParams? = null

    companion object {
        fun <T> onSuccess(data: T): HttpState<T> =
            Success(data)

        fun <T> onFailed(data: HttpWrapper<T>): HttpState<T> =
            Failed(data)

        fun <T> onError(throwable: AppException): HttpState<T> =
            Error(throwable)

        fun <T> onLoading(message: String = ""): HttpState<T> =
            Loading(message)
    }

    data class Loading(val loadingMessage: String) : HttpState<Nothing>()

    data class Success<out T>(val data: T) : HttpState<T>()

    data class Failed<T>(val data: HttpWrapper<T>) : HttpState<T>()

    data class Error(val error: AppException) : HttpState<Nothing>() {
        init {

        }
    }
}

/**
 * http请求携带的
 */
class HttpParams(
    var showDialog: Boolean = true,
    var showMsg: Boolean = true,
    var msg: String = "正在请求...",
    var successCode: Int = Http.HTTP_SUCCESS_CODE
) {
    companion object{
        /**
         * 默认
         */
        fun default(): HttpParams{
            return HttpParams()
        }

        /**
         * 没有弹框
         */
        fun normal(): HttpParams{
            return HttpParams(showDialog = false)
        }

        /**
         * 没有消息
         */
        fun noMessage(): HttpParams{
            return HttpParams(showMsg = false)
        }
    }
}

/**
 * http结果的回调
 */
class HttpCallBack<T>(
    var onSuccess: (T?) -> Unit,
    var onFailed: ((HttpWrapper<T>) -> Unit)? = null,
    var onError: ((AppException) -> Unit)? = null,
    var onErrorFailed:  (() -> Unit)? = null,
    var onLoading: (() -> Unit)? = null,
    var onFinish: (() -> Unit)? = null
)