package com.elibrarysapplication.app.modules.homepage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.base.BaseActivity
import com.elibrarysapplication.app.databinding.ActivityHomePageBinding
import com.elibrarysapplication.app.modules.homepage.`data`.model.HomePageRowModel
import com.elibrarysapplication.app.modules.homepage.`data`.viewmodel.HomePageVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class HomePageActivity : BaseActivity<ActivityHomePageBinding>(R.layout.activity_home_page) {
  private val viewModel: HomePageVM by viewModels<HomePageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val homePageAdapter = HomePageAdapter(viewModel.homePageList.value?:mutableListOf())
    binding.recyclerHomePage.adapter = homePageAdapter
    homePageAdapter.setOnItemClickListener(
    object : HomePageAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : HomePageRowModel) {
        onClickRecyclerHomePage(view, position, item)
      }
    }
    )
    viewModel.homePageList.observe(this) {
      homePageAdapter.updateData(it)
    }
    binding.homePageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerHomePage(
    view: View,
    position: Int,
    item: HomePageRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "HOME_PAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomePageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
