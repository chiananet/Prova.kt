package com.digital.prova.jsndata

class SplitLinks {
    companion object {
        private const val TAG = "SplitLinks"
    }

    fun ImageUrl(img_category: String, jsn: String): String {
        var urlImage: String? = null

        if (jsn == "JsnData") {
            var url = img_category.split("preview2=".toRegex()).toTypedArray()
            urlImage = url[0].replace("preview1=", "")
            urlImage = urlImage.replace(",", "")
            urlImage = urlImage.replace("{", "")

            //If Preview2 not exists
            if (urlImage == "") {
                url = img_category.split("preview1=".toRegex()).toTypedArray()
                urlImage = url[0].replace("preview2=", "")
                urlImage = urlImage.replace(",", "")
                urlImage = urlImage.replace("{", "")
            }

        } else if (jsn == "JsnDetail") {
            val url = img_category.split("icon=".toRegex()).toTypedArray()
            val newUrl = url[1].split("\\{".toRegex()).toTypedArray()
            urlImage = newUrl[1].replace("512x512=", "")
        }
        return urlImage ?: ""
    }
}