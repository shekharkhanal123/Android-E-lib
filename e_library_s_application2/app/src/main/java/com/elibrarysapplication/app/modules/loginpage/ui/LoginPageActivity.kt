package com.elibrarysapplication.app.modules.loginpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.base.BaseActivity
import com.elibrarysapplication.app.databinding.ActivityLoginPageBinding
import com.elibrarysapplication.app.modules.indexpage.ui.IndexPageActivity
import com.elibrarysapplication.app.modules.loginpage.`data`.viewmodel.LoginPageVM
import com.elibrarysapplication.app.modules.signuppage.ui.SignupPageActivity
import kotlin.String
import kotlin.Unit

class LoginPageActivity : BaseActivity<ActivityLoginPageBinding>(R.layout.activity_login_page) {
  private val viewModel: LoginPageVM by viewModels<LoginPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginPageVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = IndexPageActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
      binding.txtSignup.setOnClickListener {
        val destIntent = SignupPageActivity.getIntent(this, null)
        startActivity(destIntent)
      }
    }

    companion object {
      const val TAG: String = "LOGIN_PAGE_ACTIVITY"


      fun getIntent(context: Context, bundle: Bundle?): Intent {
        val destIntent = Intent(context, LoginPageActivity::class.java)
        destIntent.putExtra("bundle", bundle)
        return destIntent
      }
    }
  }
