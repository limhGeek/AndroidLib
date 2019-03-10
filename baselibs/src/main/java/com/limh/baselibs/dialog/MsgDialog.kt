package com.limh.baselibs.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.limh.baselibs.R

/**
 * @author limh
 * @function 通用对话框
 * @date 2019/2/15 9:59
 */
class MsgDialog(context: Context) : Dialog(context, R.style.styleDialog) {
    class Builder(private var context: Context) {
        private var mTitle: String? = null
        private var msg: String? = null
        private var txtLeft: String? = null
        private var txtRight: String? = null
        private var btnSize: Float = 16f
        private var titleSize: Float = 18f
        private var msgSize: Float = 16f
        private var btnColor: String? = null
        private var msgColor: String? = null
        private var isOutTouchCancel: Boolean = false
        private var showCloseBtn: Boolean = false
        private var leftOnClickListener: DialogInterface.OnClickListener? = null
        private var rightOnClickListener: DialogInterface.OnClickListener? = null

        fun setmTitle(title: String): Builder {
            this.mTitle = title
            return this
        }

        fun setMsg(msg: String): Builder {
            this.msg = msg
            return this
        }

        fun setTxtLeft(txtLeft: String, leftOnClickListener: DialogInterface.OnClickListener): Builder {
            this.txtLeft = txtLeft
            this.leftOnClickListener = leftOnClickListener
            return this
        }

        fun setTxtRight(txtRight: String, rightOnClickListener: DialogInterface.OnClickListener): Builder {
            this.txtRight = txtRight
            this.rightOnClickListener = rightOnClickListener
            return this
        }

        fun setBtnSize(btnSize: Float): Builder {
            this.btnSize = btnSize
            return this
        }

        fun setTitleSize(titleSize: Float): Builder {
            this.titleSize = titleSize
            return this
        }

        fun setMsgSize(msgSize: Float): Builder {
            this.msgSize = msgSize
            return this
        }

        fun setBtnColor(btnColor: String): Builder {
            this.btnColor = btnColor
            return this
        }

        fun setMsgColor(msgColor: String): Builder {
            this.msgColor = msgColor
            return this
        }

        fun setOutTouchCancel(outTouchCancel: Boolean): Builder {
            isOutTouchCancel = outTouchCancel
            return this
        }

        fun setShowCloseBtn(showCloseBtn: Boolean): Builder {
            this.showCloseBtn = showCloseBtn
            return this
        }

        fun create(): MsgDialog {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.layout_dialog_msg, null)
            val msgDialog = MsgDialog(context)
            msgDialog.addContentView(
                layout,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            )
            msgDialog.setCanceledOnTouchOutside(isOutTouchCancel)
            val txtTitle = layout.findViewById<TextView>(R.id.txt_dialog_title)
            val txtMsg = layout.findViewById<TextView>(R.id.txt_dialog_msg)
            val txtViewLeft = layout.findViewById<TextView>(R.id.txt_dialog_left)
            val txtViewRight = layout.findViewById<TextView>(R.id.txt_dialog_right)
            val imgClose = layout.findViewById<ImageView>(R.id.txt_dg_close)
            val viewLine = layout.findViewById<View>(R.id.view_dialog_mid)
            if (showCloseBtn) {
                imgClose.visibility = View.VISIBLE
                imgClose.setOnClickListener {
                    if (msgDialog.isShowing)
                        msgDialog.dismiss()
                }
            } else run { imgClose.visibility = View.GONE }
            if (TextUtils.isEmpty(mTitle)) {
                txtTitle.visibility = View.GONE
            } else {
                txtTitle.visibility = View.VISIBLE
                txtTitle.setText(mTitle)
            }

            txtMsg.text = msg
            txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleSize)
            txtMsg.setTextSize(TypedValue.COMPLEX_UNIT_DIP, msgSize)
            if (!TextUtils.isEmpty(msgColor)) {
                txtMsg.setTextColor(Color.parseColor(msgColor))
            }
            if (!TextUtils.isEmpty(btnColor)) {
                txtViewLeft.setTextColor(Color.parseColor(btnColor))
                txtViewRight.setTextColor(Color.parseColor(btnColor))
            }

            txtViewLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, btnSize)
            txtViewRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, btnSize)
            if (TextUtils.isEmpty(txtLeft)) {
                txtViewLeft.visibility = View.GONE
                viewLine.visibility = View.GONE
            } else {
                txtViewLeft.visibility = View.VISIBLE
                txtViewLeft.text = txtLeft
                txtViewLeft.setOnClickListener {
                    leftOnClickListener?.onClick(
                        msgDialog,
                        DialogInterface.BUTTON_NEGATIVE
                    )
                }
            }
            if (TextUtils.isEmpty(txtRight)) {
                txtViewRight.visibility = View.GONE
                viewLine.visibility = View.GONE
            } else {
                txtViewRight.visibility = View.VISIBLE
                txtViewRight.text = txtRight
                txtViewRight.setOnClickListener {
                    rightOnClickListener?.onClick(
                        msgDialog,
                        DialogInterface.BUTTON_POSITIVE
                    )
                }
            }

            return msgDialog

        }
    }
}