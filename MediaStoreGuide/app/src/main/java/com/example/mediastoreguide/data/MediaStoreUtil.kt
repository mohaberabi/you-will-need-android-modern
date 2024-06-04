package com.example.mediastoreguide.data

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@RequiresApi(Build.VERSION_CODES.Q)
class MediaStoreUtil(
    private val context: Context
) {


    suspend fun saveImage(bitmap: Bitmap) {

        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver

            val imageCollection = MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )

            val timeMillis = System.currentTimeMillis()
            val imageContentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "${timeMillis}_image.jpg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
                put(MediaStore.Images.ImageColumns.DATE_TAKEN, timeMillis)
                put(MediaStore.Images.ImageColumns.IS_PENDING, 1)
            }
            val imageMediaStoreUri = resolver.insert(
                imageCollection,
                imageContentValues
            )

            imageMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.let { stream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    }
                    imageContentValues.clear()
                    imageContentValues.put(MediaStore.Images.ImageColumns.IS_PENDING, 0)
                    resolver.update(
                        uri, imageContentValues, null, null
                    )
                } catch (e: Exception) {
                    e.printStackTrace()

                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    suspend fun saveVideo(file: File) {

        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver

            val videoCollection = MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )

            val timeMillis = System.currentTimeMillis()
            val videoValues = ContentValues().apply {
                put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
                put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                put(MediaStore.Video.Media.DISPLAY_NAME, "${timeMillis}_video" + ".mp4")

            }
            val videoUri = resolver.insert(
                videoCollection,
                videoValues
            )

            videoUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { output ->

                        resolver.openInputStream(Uri.fromFile(file))?.use {

                                input ->

                            input.copyTo(output)

                        }

                    }

                    videoValues.clear()
                    videoValues.put(
                        MediaStore.MediaColumns.IS_PENDING, 0
                    )


                    resolver.update(
                        uri, videoValues, null, null
                    )

                } catch (e: Exception) {
                    e.printStackTrace()

                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    suspend fun saveAudio(file: File) {

        withContext(Dispatchers.IO) {
            val resolver = context.contentResolver

            val videoCollection = MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )

            val timeMillis = System.currentTimeMillis()
            val audioValues = ContentValues().apply {
                put(MediaStore.Audio.Media.RELATIVE_PATH, Environment.DIRECTORY_MUSIC)
                put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3")
                put(MediaStore.Audio.Media.DISPLAY_NAME, "${timeMillis}_audio" + ".mp3")

            }
            val audioUri = resolver.insert(
                videoCollection,
                audioValues
            )

            audioUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { output ->

                        resolver.openInputStream(Uri.fromFile(file))?.use {

                                input ->

                            input.copyTo(output)

                        }

                    }

                    audioValues.clear()
                    audioValues.put(
                        MediaStore.MediaColumns.IS_PENDING, 0
                    )


                    resolver.update(
                        uri, audioValues, null, null
                    )

                } catch (e: Exception) {
                    e.printStackTrace()

                    resolver.delete(uri, null, null)
                }
            }
        }
    }

    fun getRawAudioFile(resId: Int): File {
        val inputStream = context.resources.openRawResource(resId)
        val audioFile = File.createTempFile("temp_audio", ".mp3", context.cacheDir)
        audioFile.outputStream().use { outputStream -> inputStream.copyTo(outputStream) }
        return audioFile
    }
}