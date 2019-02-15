package com.limh.baselibs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.limh.baselibs.R

/**
 * @author limh
 * @function 加载中
 * @date 2018/11/26 17:02
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.styleDialog) {

    class Builder(private val context: Context) {
        private var msg: String? = null
        private var isOutTouchCancel = false
        private var isBgDark = false
        private var onDismissListener: DialogInterface.OnClickListener? = null
        private var type = 1

        fun setMsg(msg: String): Builder {
            this.msg = msg
            return this
        }

        fun setOutTouchCancel(outTouchCancel: Boolean): Builder {
            isOutTouchCancel = outTouchCancel
            return this
        }

        fun setType(type: Int): Builder {
            this.type = type
            return this
        }

        fun setOnDismissListener(onDismissListener: DialogInterface.OnClickListener): Builder {
            this.onDismissListener = onDismissListener
            return this
        }

        fun setBgDark(bgDark: Boolean): Builder {
            this.isBgDark = bgDark
            return this
        }

        fun create(): LoadingDialog? {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.layout_dialog_loading, null)
            val loadingDialog = LoadingDialog(context)
            loadingDialog.addContentView(
                layout,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            )
            loadingDialog.setCanceledOnTouchOutside(isOutTouchCancel)
            val txtMsg = layout.findViewById<TextView>(R.id.txt_dialog_loading_msg)
            val progressBar = layout.findViewById<ProgressBar>(R.id.dg_pb_loading)
            val imageView = layout.findViewById<ImageView>(R.id.dg_img_success)

            if (TYPE_LOADING == this.type) {
                progressBar.visibility = View.VISIBLE
                imageView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                Handler().postDelayed({
                    loadingDialog.dismiss()
                    if (null != onDismissListener) {
                        onDismissListener!!.onClick(loadingDialog, DialogInterface.BUTTON_NEGATIVE)
                    }
                }, 1500L)
            }
            if (TextUtils.isEmpty(msg)) {
                txtMsg.visibility = View.GONE
            } else {
                txtMsg.visibility = View.VISIBLE
                txtMsg.text = msg
            }
            val window = loadingDialog.window
            if (null != window) {
                window.setWindowAnimations(R.style.dialogWindowAnim) //设置窗口弹出动画
                window.setBackgroundDrawableResource(R.color.transparent)
                if (!this.isBgDark) {
                    window.clearFlags(2)
                }
            }

            return loadingDialog
        }
    }

    companion object {

        private val TAG = "LoadingDialog"
        val TYPE_LOADING = 1
        val TYPE_SUCCESS = 2
    }
}
