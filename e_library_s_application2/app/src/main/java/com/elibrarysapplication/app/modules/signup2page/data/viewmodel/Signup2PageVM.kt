package com.elibrarysapplication.app.modules.signup2page.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elibrarysapplication.app.modules.signup2page.`data`.model.Signup2PageModel
import org.koin.core.KoinComponent

class Signup2PageVM : ViewModel(), KoinComponent {
  val signup2PageModel: MutableLiveData<Signup2PageModel> = MutableLiveData(Signup2PageModel())

  var navArguments: Bundle? = null
}
