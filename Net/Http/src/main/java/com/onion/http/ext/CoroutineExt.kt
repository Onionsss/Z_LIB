package com.onion.http.ext

import com.onion.http.bean.*
import com.onion.http.exception.AppException
import com.onion.protocol.ViewProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: CoroutineExt
 * Author: 张琦
 * Date: 2021/11/25 10:43
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun <T> CoroutineScope.request(view: ViewProtocol,
                               api: suspend () -> HttpWrapper<T>,
                               httpCallBack: HttpCallBack<T>,
                               httpParams: HttpParams = HttpParams(),
){
    launch {
        kotlin.runCatching {
            if(httpParams.showDialog){
                view.showDialog(httpParams.msg)
            }
            httpCallBack.onLoading?.run { this() }
            api()
        }.onSuccess { wrapper ->
            view.disDialog()

            when(wrapper.getOriginCode()){
                httpParams.successCode -> {
                    httpCallBack.onSuccess(wrapper.getOriginData())
                }
                else -> {

                    httpCallBack.onFailed?.let {
                        it(wrapper)
                    }

                    httpCallBack.onErrorFailed?.run {
                        this()
                    }

                    if(httpParams.showMsg){
                        view.showMsg(wrapper.getOriginMsg())
                    }
                }
            }

            httpCallBack.onFinish?.run { this() }
        }.onFailure {
            view.disDialog()

            val exception = AppException(it)
            /**
             * 弹出异常信息
             */
            if(httpParams.showMsg){
                view.showMsg(exception.msg)
            }

            httpCallBack.onError?.run { this(exception) }
            httpCallBack.onErrorFailed?.run {
                this()
            }
            httpCallBack.onFinish?.run { this() }
        }
    }
}

/**
 * 请求分页
 */
fun <T> CoroutineScope.requestPage(view: ViewProtocol,
                                   api: suspend () -> HttpWrapper<T>,
                                   httpCallBack: HttpCallBack<T>,
                                   httpParams: HttpParams = HttpParams(),
                                   page: Page = Page(),
){
    launch {
        kotlin.runCatching {
            if(httpParams.showDialog){
                view.showDialog(httpParams.msg)
            }
            httpCallBack.onLoading?.run { this() }
            api()
        }.onSuccess { wrapper ->
            view.disDialog()

            when(wrapper.getOriginCode()){
                httpParams.successCode -> {

                    if(wrapper.getOriginData() is DataList<*>){
                        //如果是集合
                        val it = wrapper.data as DataList<*>
                        //是分页数据
                        it.page = page

                        if(it.getOriginList()!!.isEmpty()){
                            it.page.pageNum--
                        }
                    }

                    if(wrapper.getOriginData() is PageList<*>){
                        val it = wrapper.getOriginData() as PageList<*>
                        it.page = page

                        if(it.isEmpty()){
                            it.page!!.pageNum--
                        }
                    }

                    httpCallBack.onSuccess(wrapper.getOriginData())
                }
                else -> {
                    page.pageNum--

                    httpCallBack.onFailed?.let {
                        it(wrapper)
                    }

                    httpCallBack.onErrorFailed?.run {
                        this()
                    }

                    if(httpParams.showMsg){
                        view.showMsg(wrapper.getOriginMsg())
                    }
                }
            }

            httpCallBack.onFinish?.run { this() }
        }.onFailure {
            page.pageNum--
            view.disDialog()

            val exception = AppException(it)
            /**
             * 弹出异常信息
             */
            if(httpParams.showMsg){
                view.showMsg(exception.msg)
            }

            httpCallBack.onError?.run { this(exception) }
            httpCallBack.onErrorFailed?.run {
                this()
            }
            httpCallBack.onFinish?.run { this() }
        }
    }
}