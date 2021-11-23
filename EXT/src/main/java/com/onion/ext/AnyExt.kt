package com.onion.ext

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: AnyExt
 * Author: 张琦
 * Date: 2021/11/23 9:59
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit = {}) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}