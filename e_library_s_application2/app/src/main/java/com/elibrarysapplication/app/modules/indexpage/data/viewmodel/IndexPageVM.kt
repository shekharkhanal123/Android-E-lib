package com.elibrarysapplication.app.modules.indexpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elibrarysapplication.app.modules.indexpage.`data`.model.IndexPageModel
import org.koin.core.KoinComponent

class IndexPageVM : ViewModel(), KoinComponent {
  val indexPageModel: MutableLiveData<IndexPageModel> = MutableLiveData(IndexPageModel())

  var navArguments: Bundle? = null
}
