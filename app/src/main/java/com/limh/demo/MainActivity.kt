package com.limh.demo

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.limh.baselibs.MsgDialog
import com.limh.baselibs.utils.Logs
import com.limh.qrcode.activity.CaptureActivity
import com.limh.qrcode.activity.CodeUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
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

        btn3.setOnClickListener {
            startActivityForResult(Intent(context, CaptureActivity::class.java), 100)
        }
        btn4.setOnClickListener {
            val logo = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            val bitmap: Bitmap = CodeUtils.createImage(txt.text.toString(), 200, 200, logo)
            img.setImageBitmap(bitmap)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Logs.d(TAG, "结果回调：${data?.extras?.toString()}")
                txt.text = data?.extras?.getString(CodeUtils.RESULT_STRING)
            }
        }
    }
}
