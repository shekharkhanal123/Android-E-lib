package com.elibrarysapplication.app.modules.signuppage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elibrarysapplication.app.modules.signuppage.`data`.model.SignupPageModel
import org.koin.core.KoinComponent

class SignupPageVM : ViewModel(), KoinComponent {
  val signupPageModel: MutableLiveData<SignupPageModel> = MutableLiveData(SignupPageModel())

  var navArguments: Bundle? = null
}
