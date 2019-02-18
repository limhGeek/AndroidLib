# AndroidLib
Android通用工具类、基类

## qrcode二维码模块

二维码扫描，生成二维码功能

### 扫码

```
  //打开相机扫码
  btn3.setOnClickListener {
    startActivityForResult(Intent(context, CaptureActivity::class.java), 100)
  }
  
  //结果回调
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
      if (requestCode == 100) {
          if (resultCode == Activity.RESULT_OK) {
              Logs.d(TAG, "结果回调：${data?.extras?.toString()}")
              txt.text = data?.extras?.getString(CodeUtils.RESULT_STRING)
          }
      }
    }
```

### 生成二维码

```
    btn4.setOnClickListener {
        //中心的图标
        val logo = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        //生成二维码
        val bitmap: Bitmap = CodeUtils.createImage(txt.text.toString(), 200, 200, logo)
        //显示
        img.setImageBitmap(bitmap)
    }
```
