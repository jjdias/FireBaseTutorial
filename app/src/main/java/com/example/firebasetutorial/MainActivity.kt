package com.example.firebasetutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



//    private fun runTextRecognition() {
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage)
//        FirebaseVisionTextRecognizer detector = FirebaseVisionTextRecognizer.getInstance().getOnDeviceTextRecognizer()
//        mTextButton.setEnabled(false)
//
//    }
}
