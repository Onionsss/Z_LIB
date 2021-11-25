package com.onion.http.bean

import java.util.*

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: Page
 * Author: 张琦
 * Date: 2021/11/25 10:42
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
open class  DataList<T>(var count: Int? = 0,
                        var list: ArrayList<T>? = null,
                        var page: Page = Page()
) {
    //给子类重写 拿到原始集合
    open fun getOriginList(): ArrayList<T>?{
        return list
    }

}

/**
 * 页码
 */
data class Page(
    var loadMore: Boolean = false, //是否加载更多
    var pageNum: Int = 1,          //加载页数
    var pageSize: Int = 20,         //每页个数
    var extraId: String? = null,
    var date: Date? = null
) {

    fun set(loadMore: Boolean = false) {
        this.loadMore = loadMore
        when (this.loadMore) {
            true -> {
                pageNum++
                if(date == null){
                    date = Date()
                }
            }
            false -> {
                pageNum = 1
                date = Date()
            }
        }
    }

    /**
     * 重置
     */
    fun reset(){
        pageNum = 1
        pageSize = 20
    }

    fun  dateTime(): Long?{
        return date?.time
    }
}