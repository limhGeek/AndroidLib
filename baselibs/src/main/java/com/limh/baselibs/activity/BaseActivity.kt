package com.limh.baselibs.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @function：
 * @author： limh
 * @date： 2019/2/28
 * Copyright @2019 Corpration Name
 */
public abstract class BaseActivity : AppCompatActivity() {
    private var isFirst = true
    //是否在onResume中加载数据
    var isResume = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findView()
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (isFirst || isResume) {
            isFirst = false
            initData()
        }
    }
    abstract fun findView()
    abstract fun initView()
    abstract fun initData()
}