package com.elibrarysapplication.app.modules.signup2page.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.base.BaseActivity
import com.elibrarysapplication.app.databinding.ActivitySignup2PageBinding
import com.elibrarysapplication.app.modules.signup2page.`data`.viewmodel.Signup2PageVM
import kotlin.String
import kotlin.Unit

class Signup2PageActivity : BaseActivity<ActivitySignup2PageBinding>(R.layout.activity_signup2_page)
    {
  private val viewModel: Signup2PageVM by viewModels<Signup2PageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signup2PageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "SIGNUP2PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Signup2PageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
