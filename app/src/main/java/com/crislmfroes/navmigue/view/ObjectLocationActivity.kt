package com.crislmfroes.navmigue.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.crislmfroes.navmigue.R
import com.crislmfroes.navmigue.viewmodel.CameraViewModel
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.otaliastudios.cameraview.Audio
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.Frame

import kotlinx.android.synthetic.main.activity_object_location.*

class ObjectLocationActivity : AppCompatActivity() {

    private lateinit var cameraViewModel : CameraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_location)
        setSupportActionBar(toolbar)
        val camera : CameraView = findViewById(R.id.camera_view)
        camera.audio = Audio.OFF
        camera.setLifecycleOwner(this)
        cameraViewModel = ViewModelProviders.of(this).get(CameraViewModel::class.java)
        val adapter = ObjectLabelAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.detectionsview)
        recyclerView.adapter = adapter
        cameraViewModel.allDetections.observe(this, Observer {
            adapter.setLabels(it)
        })
    }

    internal fun onFrame(frame : Frame) {
        val metadata = FirebaseVisionImageMetadata.Builder()
            .setFormat(frame.format)
            .setHeight(480)
            .setWidth(640)
            .setRotation(frame.rotation)
            .build()
        val image = FirebaseVisionImage.fromByteArray(frame.data, metadata)
        cameraViewModel.detectObjects(image)
    }

}
