package com.example.whattowatch.data.local.storage

import android.content.Context
import com.example.whattowatch.domain.MediaType
import java.io.File

class ImageStorage(
    private val context: Context
) {

    fun createFile(
        mediaType: MediaType,
        mediaId: Int
    ): File {
        val folder = File(context.filesDir, "posters")

        if (!folder.exists()) {
            folder.mkdir()
        }

        val fileName = "${mediaType.name}_${mediaId}.jpg"

        return File(folder, fileName)

    }

    fun deleteFile(
        mediaType: MediaType,
        mediaId: Int,
    ): Boolean {
        return createFile(mediaType, mediaId).delete()

    }

    suspend fun saveImageToStorage(
        mediaType: MediaType,
        mediaId: Int,
        bytes: ByteArray
    ): String {
        val savedImage = createFile(mediaType, mediaId)

        savedImage.writeBytes(bytes)


        return savedImage.absolutePath
    }
}


