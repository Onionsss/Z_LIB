package com.onion.bean

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: HttpWrapper
 * Author: 张琦
 * Date: 2021/11/25 10:39
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
open class HttpWrapper<T>(var code: Int, var info: String, val data: T) {

    //拿到原始的code码
    fun getOriginCode(): Int{
        return code
    }

    //拿到原始的code码
    fun getOriginData(): T{
        return data
    }

    fun getOriginMsg(): String?{
        return info
    }

}