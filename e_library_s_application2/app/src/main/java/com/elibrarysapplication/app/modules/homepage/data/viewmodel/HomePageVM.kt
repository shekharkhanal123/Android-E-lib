package com.elibrarysapplication.app.modules.homepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elibrarysapplication.app.modules.homepage.`data`.model.HomePageModel
import com.elibrarysapplication.app.modules.homepage.`data`.model.HomePageRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class HomePageVM : ViewModel(), KoinComponent {
  val homePageModel: MutableLiveData<HomePageModel> = MutableLiveData(HomePageModel())

  var navArguments: Bundle? = null

  val homePageList: MutableLiveData<MutableList<HomePageRowModel>> =
      MutableLiveData(mutableListOf())
}
