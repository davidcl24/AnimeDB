package com.example.appejemplo.utils

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


    class CameraUtils(private val context: Context, private val contentResolver: ContentResolver) {
        private var photoUri: Uri? = null

        private fun generateFileName(): String {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            return "IMG_$timeStamp.jpeg"
        }

        private fun createImageUri(): Uri? {
            val contentValues = ContentValues().apply {
                put (MediaStore.Images.Media.DISPLAY_NAME, generateFileName())
                put (MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put (MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ExampleApp")
            }
            return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        }

        fun takePicture(takePictureLauncher: ActivityResultLauncher<Uri>) {
            val uri = createImageUri()
            photoUri = uri
            uri?.let {
                takePictureLauncher.launch(it)
            }
        }

        fun getPhotoUri(): Uri? {
            return photoUri
        }

        /*fun hasCameraPermission(context: Context): List<String> {
            val permissionsToRequest = mutableListOf<String>()

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                permissionsToRequest.add(Manifest.permission.CAMERA)
            }

            if (Build.VERSION.PREVIEW_SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(Manifest.permission.READ_MEDIA_IMAGES)
                }
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VIDEO) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(Manifest.permission.READ_MEDIA_VIDEO)
                }
            }

            if (Build.VERSION.PREVIEW_SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
                }
            } else {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }

            return permissionsToRequest
        }*/

        //Opcion mas optimizada
        fun getCameraPermission(context: Context): List<String> {
            val permissions = mutableListOf(Manifest.permission.CAMERA)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissions.addAll(
                    listOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO
                    )
                )
            } else {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                permissions.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
            }

            return permissions.filter {
                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
            }
        }
    }

