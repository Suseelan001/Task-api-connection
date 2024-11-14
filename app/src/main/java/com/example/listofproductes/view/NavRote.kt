package com.example.listofproductes.view

sealed class NavRote(var path:String) {
    data object UserListScreen: NavRote("USER_LIST_SCREEN")

}