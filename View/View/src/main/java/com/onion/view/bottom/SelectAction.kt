package com.onion.view.bottom

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.onion.view.R
import kotlinx.android.synthetic.main.bottom_select.*

/**
 * Copyright (C), 2021-2021, 游加科技
 * FileName: SelectAction
 * Author: EDZ by 张琦
 * Date: 2021/5/7 15:38
 * Description:
 */
class SelectAction(ctx: Context,var texts: Array<String?>,
                   var confirmBlock: (SelectAction.() -> Boolean)? = null,
                   var cancelBlock: (SelectAction.() -> Unit)? = null): BottomSheetDialog(ctx, R.style.bottomSheetStyle)  {

    private var data: Any? = null

    fun <T> setData(data: T){
        this.data = data
    }

    fun <T> getData(): T{
        return data as T
    }

    init {
        setContentView(R.layout.bottom_select)
        window?.findViewById<View>(R.id.design_bottom_sheet)?.setBackgroundColor(Color.TRANSPARENT)

        bottom_select_tips.text = texts[0]

        texts[1]?.let {
            bottom_select_ok.text = it
        }
        texts[2]?.let {
            bottom_select_no.text = it
        }

        bottom_select_ok?.setOnClickListener {
            confirmBlock?.let {
                if(it()){
                    dismiss()
                }
            }
        }

        bottom_select_no?.setOnClickListener {
            cancelBlock?.let {
                it()
            }
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}