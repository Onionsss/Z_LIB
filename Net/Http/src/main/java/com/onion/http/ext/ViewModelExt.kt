package com.onion.http.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.onion.http.bean.*
import com.onion.http.exception.AppException
import com.onion.http.vm.BaseViewModel
import com.onion.protocol.ViewProtocol
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: ViewModelExt
 * Author: 张琦
 * Date: 2021/11/25 17:08
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun <T> BaseViewModel.request(
    api: suspend () -> HttpWrapper<T>,
    httpState: MutableLiveData<HttpState<T>>,
    httpParams: HttpParams = HttpParams()
): Job {
    return viewModelScope.launch {
        kotlin.runCatching {
            httpState.loading()
            api()
        }.onSuccess { result ->
            //如果是200code 代表请求拿到数据了
            if(result.getOriginCode() == httpParams.successCode){
                httpState.success(result.getOriginData())
            }else{
                //如果不是200 就返回failed
                httpState.failed(result)
            }
        }.onFailure {
            httpState.error(it)
        }
    }
}

/**
 * 发起一次分页请求
 */
fun <T> BaseViewModel.requestPage(
    api: suspend () -> HttpWrapper<T>,
    httpState: MutableLiveData<HttpState<T>>,
    page: Page,
    httpParams: HttpParams = HttpParams()
): Job {
    httpState.value
    return viewModelScope.launch {
        kotlin.runCatching {
            //执行一次请求返回数据
            httpState.loading()
            api()
        }.onSuccess { result ->
            //如果是200code 代表请求拿到数据了
            if(result.getOriginCode() == httpParams.successCode){
                if(result.getOriginData() is DataList<*>){
                    val it = result.getOriginData() as DataList<*>
                    //是分页数据
                    it.page = page

                    if(it.getOriginList()?.isEmpty()?: false){
                        it.page.pageNum--
                    }
                }

                if(result.getOriginData() is PageList<*>){
                    //如果是集合
                    val it = result.getOriginData() as PageList<*>
                    //是分页数据
                    it.page = page

                    if(it.isEmpty()){
                        it.page!!.pageNum--
                    }
                }

                httpState.success(result.getOriginData())
            }else{
                page.pageNum--
                //如果不是200 就返回failed
                httpState.failed(result)
            }
        }.onFailure {
            page.pageNum--
            httpState.error(it)
        }
    }
}

/**
 * 对于结果的解析
 */
fun <T> ViewProtocol.state(
    httpState: HttpState<T>,
    httpCallBack: HttpCallBack<T>,
    httpParams: HttpParams = HttpParams.default()
){
    when (httpState) {
        is HttpState.Loading -> {

            if(httpParams.showDialog){
                showDialog()
            }

            httpCallBack.onLoading?.run { this }
        }
        is HttpState.Success -> {
            disDialog()
            httpCallBack.onSuccess(httpState.data)
            httpCallBack.onFinish?.run { this() }
        }
        is HttpState.Failed -> {
            disDialog()

            if(httpParams.showMsg){
                showMsg(httpState.data.getOriginMsg())
            }

            httpCallBack.onFailed?.run {
                this(httpState.data)
            }

            httpCallBack.onErrorFailed?.run {
                this()
            }

            httpCallBack.onFinish?.run { this() }
        }
        is HttpState.Error -> {
            disDialog()

            if(httpParams.showMsg){
                showMsg(httpState.error.msg)
            }

            httpCallBack.onError?.run { this(httpState.error) }
            httpCallBack.onErrorFailed?.run {
                this()
            }
            httpCallBack.onFinish?.run { this() }
        }
    }
}

//成功是200的情况
fun <T> MutableLiveData<HttpState<T>>.success(result: T){
    value = HttpState.onSuccess(result)
}

//成功不是200的情况
fun <T> MutableLiveData<HttpState<T>>.failed(result: HttpWrapper<T>){
    value = HttpState.onFailed(result)
}

//异常
fun <T> MutableLiveData<HttpState<T>>.error(throwable: Throwable){
    value = HttpState.onError(AppException(throwable))
}

//加载
fun <T> MutableLiveData<HttpState<T>>.loading(){
    value = HttpState.onLoading()
}