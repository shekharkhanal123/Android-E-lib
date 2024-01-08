package com.elibrarysapplication.app.modules.homepage.`data`.model

import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.di.MyApp
import kotlin.String

data class HomePageRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDiploma: String? = MyApp.getInstance().resources.getString(R.string.lbl_diploma)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeeBooks: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_books)

)
