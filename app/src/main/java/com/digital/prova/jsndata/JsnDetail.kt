package com.digital.prova.jsndata

class JsnDetail(val id: String?, val title: String?, images: Array<String?>) {
    private val images: Any
    init { this.images = images }
    val image: String get() { val spL = SplitLinks(); val jsn = "JsnDetail"; return spL.ImageUrl(images.toString(), jsn) }
}