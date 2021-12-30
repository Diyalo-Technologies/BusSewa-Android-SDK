package com.diyalotech.bussewasdk.utils

object Validator {

    /*
    * just used as a filter for nepali mobile numbers
    * */
    fun isValidMobile(phoneStr: String): Boolean {
        return try {
            //check if its a valid number
            phoneStr.toLong()
            //check length
            if(phoneStr.length!=10) {
                return false
            }
            if(!phoneStr.startsWith("9")) {
                return false
            }
            true
        } catch (ex: Exception) {
            false
        }
    }
}