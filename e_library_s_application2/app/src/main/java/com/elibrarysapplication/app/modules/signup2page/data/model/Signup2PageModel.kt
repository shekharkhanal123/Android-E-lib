package com.elibrarysapplication.app.modules.signup2page.`data`.model

import com.elibrarysapplication.app.R
import com.elibrarysapplication.app.appcomponents.di.MyApp
import kotlin.String

data class Signup2PageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_email)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNewPassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_new_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmPasswor: String? =
      MyApp.getInstance().resources.getString(R.string.msg_confirm_passwor)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAgreetoTerm: String? = MyApp.getInstance().resources.getString(R.string.msg_agree_to_term)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShowpassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_show_password)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSignup: String? = MyApp.getInstance().resources.getString(R.string.lbl_signup)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSignupOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_signup)

)
