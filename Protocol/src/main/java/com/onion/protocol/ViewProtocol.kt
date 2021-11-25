package com.onion.protocol

import android.os.Bundle

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: ViewProtocol
 * Author: 张琦
 * Date: 2021/11/25 10:50
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
interface ViewProtocol {

    fun initView(savedInstanceState: Bundle?)

    fun initData()

    fun initShow()

    fun initListener()

    fun tokenOut()

    /**
     * 网络进度条
     */
    fun showDialog(tips: String? = "加载中")

    /**
     * 关闭网络加载条
     */
    fun disDialog()

    fun showMsg(msg: String? = null)

    fun showState(state: PageState)
}