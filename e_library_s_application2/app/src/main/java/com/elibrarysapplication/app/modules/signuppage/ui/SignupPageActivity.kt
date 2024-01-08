package com.elibrarysapplication.app.modules.signuppage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.base.BaseActivity
import com.elibrarysapplication.app.databinding.ActivitySignupPageBinding
import com.elibrarysapplication.app.modules.loginpage.ui.LoginPageActivity
import com.elibrarysapplication.app.modules.signup2page.ui.Signup2PageActivity
import com.elibrarysapplication.app.modules.signuppage.`data`.viewmodel.SignupPageVM
import kotlin.String
import kotlin.Unit

class SignupPageActivity : BaseActivity<ActivitySignupPageBinding>(R.layout.activity_signup_page) {
  private val viewModel: SignupPageVM by viewModels<SignupPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signupPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtLogin.setOnClickListener {
      val destIntent = LoginPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearColumnnext.setOnClickListener {
      val destIntent = Signup2PageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SIGNUP_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignupPageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
