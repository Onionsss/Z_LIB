package com.onion.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Copyright (C), 2021-2021, 易码盛
 * FileName: ImageViewExt
 * Author: 张琦
 * Date: 2021/11/24 14:10
 * Description:
 * EMAIL: 759308541@qq.com
 * History:
 */
fun ImageView.bind(url: String,p: Int = GlideConstant.PLACEHOLDER){
    var baseUrl: String? = url
    url.let {
        baseUrl = if(it.startsWith("http")){
            it
        }else{
            GlideConstant.BASE_URL+url
        }
    }

    Glide.with(context).load(baseUrl)
        .apply(RequestOptions().placeholder(p).error(p))
        .into(this)
}

fun ImageView.bindCenterCrop(url: String,p: Int = GlideConstant.PLACEHOLDER){
    var baseUrl: String? = url
    url.let {
        baseUrl = if(it.startsWith("http")){
            it
        }else{
            GlideConstant.BASE_URL+url
        }
    }
    Glide.with(context).load(baseUrl).centerCrop()
        .apply(RequestOptions().placeholder(p).error(p))
        .into(this)
}