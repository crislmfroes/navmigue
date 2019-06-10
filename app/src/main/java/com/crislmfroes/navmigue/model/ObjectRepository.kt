package com.crislmfroes.navmigue.model

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObject
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetector

class ObjectRepository(private val objectDetector : FirebaseVisionObjectDetector) {
    val allDetections : MutableLiveData<List<FirebaseVisionObject>> = MutableLiveData(emptyList())

    @WorkerThread
    fun detectObjects(image : FirebaseVisionImage) {
        objectDetector.processImage(image)
            .addOnSuccessListener {
                allDetections.postValue(it)
            }
            .addOnFailureListener {
                Log.d("ObjectRepository", "Error while processing image", it)
            }
    }
}