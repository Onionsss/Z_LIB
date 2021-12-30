package com.onion.z_lib

import com.onion.recy.bean.Mult

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: User
 * Author: 张琦
 * Date: 2021/11/30 15:34
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
class User {

    var name: String? = null

    val xName: String? get() {
        return "¥${name}"
    }
}

class News(var style: Int): Mult{
    override fun getMultType(): Int {
        when(style){
            0 -> return 1
            1 -> return 1
        }

        return 0
    }

}