package com.onion.z_lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onion.ext.m2
import com.onion.ext.view.diffSizeM2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = "¥123"
        tv.setText(text)

        tv.diffSizeM2("123".m2(), arrayOf(50,90))
    }

}