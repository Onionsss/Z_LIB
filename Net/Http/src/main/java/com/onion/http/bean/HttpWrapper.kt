package com.onion.http.bean

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: HttpWrapper
 * Author: 张琦
 * Date: 2021/11/25 10:39
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
abstract class HttpWrapper<T>() {

    //拿到原始的code码
    abstract fun getOriginCode(): Int

    //拿到原始的code码
    abstract fun getOriginData(): T

    abstract fun getOriginMsg(): String

}
