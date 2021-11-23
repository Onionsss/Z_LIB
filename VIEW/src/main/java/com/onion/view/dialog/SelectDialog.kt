package com.onion.view.dialog

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.onion.recy.SizeUtils
import com.onion.view.R

/**
 * Copyright (C), 2020-2021, 游加科技
 * FileName: SelectDialog
 * Author: EDZ by 张琦
 * Date: 2020/11/12 13:57
 * Description:
 */
class SelectDialog(ctx: Context, var texts: Array<String?>,
                   var confirmBlock: (SelectDialog.() -> Boolean)? = null,
                   var cancelBlock: (SelectDialog.() -> Unit)? = null): AppCompatDialog(ctx, R.style.selectDialog) {

    private var mActivity = ctx as Activity

    private var titleView: TextView? = null
    private var contentView: TextView? = null
    private var cancelView: TextView? = null
    private var confirmView: TextView? = null

    init {
        while (texts.size < 4){
            texts += arrayOf<String?>(null)
        }

    }

    override fun show() {
        super.show()
        titleView = findViewById(R.id.select_dialog_title)
        contentView = findViewById(R.id.select_dialog_content)
        cancelView = findViewById(R.id.select_dialog_cancel)
        confirmView = findViewById(R.id.select_dialog_confirm)

        titleView?.text = texts[0]
        contentView?.text = texts[1]

        texts[2]?.let {
            confirmView?.text = it
        }
        texts[3]?.let {
            cancelView?.text = it
        }

        confirmView?.setOnClickListener {
            confirmBlock?.let {
                if(it()){
                    dismiss()
                }
            }
        }

        cancelView?.setOnClickListener {
            cancelBlock?.let {
                it()
            }
            dismiss()
        }
    }

    private var data: Any? = null

    fun <T> setData(data: T){
        this.data = data
    }

    fun <T> getData(): T{
        return data as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_select)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        val windowManager = mActivity.windowManager
        val display = windowManager.defaultDisplay

        val mWindow = window
        mWindow?.setWindowAnimations(R.style.`person＿dialog_anim`)

        val lp = mWindow?.attributes
        //设置居中
        mWindow?.setGravity(Gravity.CENTER)
        lp?.y = SizeUtils.dp2px(-20F)
        lp?.alpha = 1.0f
        lp?.width = (display.getWidth() * 0.55F).toInt()
        window!!.attributes = lp
    }

}