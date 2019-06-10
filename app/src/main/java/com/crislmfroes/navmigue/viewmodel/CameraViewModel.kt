package com.crislmfroes.navmigue.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.crislmfroes.navmigue.model.ObjectRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObject

class CameraViewModel(application: Application) : AndroidViewModel(application) {
    val allDetections : LiveData<List<FirebaseVisionObject>>
    private val repository : ObjectRepository

    init {
        val detector = FirebaseVision.getInstance().onDeviceObjectDetector
        repository = ObjectRepository(detector)
        allDetections = repository.allDetections
    }

    fun detectObjects(image : FirebaseVisionImage) {
        repository.detectObjects(image)
    }
}