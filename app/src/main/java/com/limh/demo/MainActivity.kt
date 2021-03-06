package com.limh.demo

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.limh.baselibs.dialog.MsgDialog
import com.limh.baselibs.utils.Logs
import com.limh.baselibs.utils.ScreenUtils
import com.limh.calendar.PopCalendar
import com.limh.qrcode.activity.CaptureActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private val TAG = "MainActivity"
    private var imageUri: Uri? = null
//    private var popwindow: CommPopwindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        initView()
    }

    private fun initView() {
        btn1.setOnClickListener {
            val calendar = PopCalendar(context)
                .setSelectColor(R.color.colorAccent)
                .setOnResultListener(object :PopCalendar.OnResultListener {
                    override fun onResult(year: Int, month: Int, day: Int) {

                    }

                    override fun onCancel() {

                    }
                })
            calendar.show(it)
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
            // 创建文件保存拍照的图片
            val mFilePath = "${Environment.getExternalStorageDirectory()}/bank_card.jpg"
            val takePhotoImage = File(mFilePath)
            try {
                // 文件存在，删除文件
                if (takePhotoImage.exists()) {
                    takePhotoImage.delete()
                }
                // 根据路径名自动的创建一个新的空文件
                takePhotoImage.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // 获取图片文件的uri对象
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri =
                    FileProvider.getUriForFile(
                        context.applicationContext,
                        "${BuildConfig.APPLICATION_ID}.fileprovider",
                        File(mFilePath)
                    )
            } else {
                imageUri = Uri.fromFile(File(mFilePath))
            }
            // 创建Intent，用于启动手机的照相机拍照
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 指定输出到文件uri中
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            // 启动intent开始拍照
            startActivityForResult(intent, 120)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 120) {
            if (resultCode == Activity.RESULT_OK) {
                Logs.d(TAG, "结果回调：${data?.extras?.toString()}")
                corpPic(imageUri)
            }
        } else if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Logs.d(TAG, "结果回调：${data?.extras?.toString()}")
                Picasso.get()
                    .load(imageUri)
                    .into(img)
            }
        }
    }

    /**
     * 裁剪图片
     */
    private fun corpPic(uri: Uri?) {
        uri?.let {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(it, "image/*")
            //以下两行添加，解决无法加载此图片的提示
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, it)
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 3) // 裁剪框比例
            intent.putExtra("aspectY", 2)
            intent.putExtra("outputX", ScreenUtils.getScreenWidth(context)) // 输出图片大小
            intent.putExtra("outputY", ScreenUtils.getScreenWidth(context) * 2 / 3)
            intent.putExtra("scale", false)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
            startActivityForResult(intent, 100)
        }
    }
}
