package com.onion.ext

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: BooleanExt
 * Author: 张琦
 * Date: 2021/11/23 10:00
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> {
            WithData(block())
        }
        else -> Otherwise
    }

inline fun <T> Boolean.no(block: () -> T) =
    when {
        this -> Otherwise
        else -> WithData(block())
    }

inline infix fun <T> BooleanExt<T>.otherwise(block: () -> T) =
    when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }

inline operator fun <T> Boolean.invoke(block: () -> T) = yes(block)