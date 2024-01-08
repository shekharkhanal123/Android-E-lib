package com.elibrarysapplication.app.modules.indexpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.base.BaseActivity
import com.elibrarysapplication.app.databinding.ActivityIndexPageBinding
import com.elibrarysapplication.app.modules.homepage.ui.HomePageActivity
import com.elibrarysapplication.app.modules.indexpage.`data`.viewmodel.IndexPageVM
import com.elibrarysapplication.app.modules.loginpage.ui.LoginPageActivity
import com.elibrarysapplication.app.modules.signuppage.ui.SignupPageActivity
import kotlin.String
import kotlin.Unit

class IndexPageActivity : BaseActivity<ActivityIndexPageBinding>(R.layout.activity_index_page) {
  private val viewModel: IndexPageVM by viewModels<IndexPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.indexPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtGroupEighteen.setOnClickListener {
      val destIntent = SignupPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtGroupNineteen.setOnClickListener {
      val destIntent = HomePageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtGroupTwenty.setOnClickListener {
      val destIntent = LoginPageActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "INDEX_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, IndexPageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
