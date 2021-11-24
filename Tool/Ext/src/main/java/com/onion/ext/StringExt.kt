package com.onion.ext

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: StringExt
 * Author: 张琦
 * Date: 2021/11/23 10:01
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun String.m2(): String{
    val format = DecimalFormat("0.00")
    return format.format(BigDecimal(this))
}
