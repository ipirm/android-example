package az.azerconnect.bakcell.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object FileUtils {
    fun fileToPart(path: String?): MultipartBody.Part {
        val file = File(path ?: "")

        val description = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData("file", file.name, description)
    }
}