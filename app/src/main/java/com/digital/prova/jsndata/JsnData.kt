package com.digital.prova.jsndata

import com.google.gson.annotations.SerializedName

class JsnData(@field:SerializedName("id_category") val idCategory: String?, val name: String?, img_category: Array<String?>) {
    @SerializedName("img_category")
    val imageCategory: Any
    init {imageCategory = img_category}
    val imageCat: String get() { val spL = SplitLinks(); val jsn = "JsnData"; return  spL.ImageUrl(imageCategory.toString(), jsn)}
}