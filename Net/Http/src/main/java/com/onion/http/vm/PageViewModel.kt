package com.onion.http.vm

import com.onion.http.bean.Page

/**
 * Copyright (C), 2020-2021, 游加科技
 * FileName: PageViewModel
 * Author: EDZ by 张琦
 * Date: 2020/10/19 14:52
 * Description: 分页所需要的的ViewModel
 */
open class PageViewModel: BaseViewModel() {

    companion object{
        const val ASC: String = "asc" //升序
        const val DESC: String = "desc" //降序
    }

    /**
     * 分页
     */
    protected val page: Page by lazy { Page() }

    /**
     * 重置page
     */
    fun resetPage(){
        page.pageNum = 1
        page.pageSize = 30
        page.loadMore = false
    }
}