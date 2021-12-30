package com.onion.z_lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onion.recy.Recy
import com.onion.toast.ToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MainActivity : AppCompatActivity(),CoroutineScope by MainScope(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Recy.init(this.application)
    }

}