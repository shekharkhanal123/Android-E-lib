package com.elibrarysapplication.app.modules.indexpage.`data`.model

import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.di.MyApp
import kotlin.String

data class IndexPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtELibrary: String? = MyApp.getInstance().resources.getString(R.string.lbl_e_library)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtReadReserve: String? = MyApp.getInstance().resources.getString(R.string.msg_read_reserve)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupTwenty: String? = MyApp.getInstance().resources.getString(R.string.lbl_login)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupEighteen: String? = MyApp.getInstance().resources.getString(R.string.lbl_signup)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupNineteen: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_view_as_guest)

)
