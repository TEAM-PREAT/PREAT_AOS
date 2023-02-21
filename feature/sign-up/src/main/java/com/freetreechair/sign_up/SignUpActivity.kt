package com.freetreechair.sign_up

import android.os.Bundle
import com.freetreechair.common.base.BindingActivity
import com.freetreechair.sign_up.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var platform: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtraData()
    }

    private fun initExtraData() {
        platform = intent.getStringExtra("platform").orEmpty()
    }
}
