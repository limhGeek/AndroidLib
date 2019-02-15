package com.limh.demo

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.limh.baselibs.MsgDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn1.setOnClickListener {
            val msgDialog = MsgDialog.Builder(this)
                .setMsg("这是一组消息内容提示框，没有标题")
                .setTxtLeft("确定", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
                .create()
            msgDialog.show()
        }

        btn2.setOnClickListener {
            val msgDialog = MsgDialog.Builder(this)
                .setMsg("这是一组消息内容提示框，有标题有标题有标题")
                .setmTitle("我是标题")
                .setBtnColor("#137dff")
                .setTxtLeft("取消", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
                .setTxtRight("确定", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
                .create()
            msgDialog.show()
        }


    }
}
