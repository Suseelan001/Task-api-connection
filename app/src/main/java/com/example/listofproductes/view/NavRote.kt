package com.example.listofproductes.view

sealed class NavRote(var path:String) {
    data object UserListScreen: NavRote("USER_LIST_SCREEN")
    data object ProductDetailScreen: NavRote("PRODUCT_DETAIL_SCREEN")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

}