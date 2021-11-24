package com.onion.ext.number

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: NumberExt
 * Author: 张琦
 * Date: 2021/11/24 10:40
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun Float.m2(): String{
    val format = DecimalFormat("0.00")
    return format.format(BigDecimal("$this"))
}
