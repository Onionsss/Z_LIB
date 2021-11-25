package com.onion.protocol

/**
 * Copyright (C), 2021-2021, 游加科技
 * FileName: UiState
 * Author: EDZ by 张琦
 * Date: 2021/4/10 17:03
 * Description:
 */
/**
 * 定义页面的状态
 */
enum class PageState {

    loading, //加载中

    netError, //网络错误

    pageError, //页面错误

    notLogin, //没有登录

    empty, //空页面

    success //成功
}