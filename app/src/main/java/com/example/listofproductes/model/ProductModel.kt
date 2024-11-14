package com.example.listofproductes.model


import com.google.gson.annotations.SerializedName


data class ProductModel (

    @SerializedName("id"              ) var id              : Int?              = null,
    @SerializedName("productCategory" ) var productCategory : String?           = null,
    @SerializedName("name"            ) var name            : String?           = null,
    @SerializedName("brand"           ) var brand           : String?           = null,
    @SerializedName("description"     ) var description     : String?           = null,
    @SerializedName("basePrice"       ) var basePrice       : Int?              = null,
    @SerializedName("inStock"         ) var inStock         : Boolean?          = null,
    @SerializedName("stock"           ) var stock           : Int?              = null,
    @SerializedName("featuredImage"   ) var featuredImage   : String?           = null,
    @SerializedName("thumbnailImage"  ) var thumbnailImage  : String?           = null,
    @SerializedName("storageOptions"  ) var storageOptions  : ArrayList<String> = arrayListOf(),
    @SerializedName("colorOptions"    ) var colorOptions    : ArrayList<String> = arrayListOf(),
    @SerializedName("display"         ) var display         : String?           = null,
    @SerializedName("CPU"             ) var CPU             : String?           = null,
    @SerializedName("camera"          ) var camera          : Camera?           = Camera()

)

data class Camera (

    @SerializedName("rearCamera"  ) var rearCamera  : String? = null,
    @SerializedName("frontCamera" ) var frontCamera : String? = null

)
