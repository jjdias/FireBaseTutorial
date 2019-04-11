package com.example.firebasetutorial
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var bitmapHolder: Bitmap

    val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tiraFotoComCameraBtn.setOnClickListener{
            val callCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCamera.resolveActivity(packageManager) != null){
                startActivityForResult(callCamera, CAMERA_REQUEST_CODE)
            }
        }

        detectaTextoNaFotoBtn.setOnClickListener{
            val image = FirebaseVisionImage.fromBitmap(bitmapHolder)
            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
            val result = detector.processImage(image)
                .addOnSuccessListener { firebaseVisionText ->
                    // Task completed successfully
                    // ...

                    for (block in firebaseVisionText.textBlocks) {
                        val boundingBox = block.boundingBox
                        val cornerPoints = block.cornerPoints
                        val text = block.text

                        for (line in block.lines) {
                            // ...
                            for (element in line.elements) {
                                // ...
                            }
                        }
                    }

/**
                    val resultText = firebaseVisionText.text
                    for (block in firebaseVisionText.textBlocks) {
                        val blockText = block.text
                        val blockConfidence = block.confidence
                        val blockLanguages = block.recognizedLanguages
                        val blockCornerPoints = block.cornerPoints
                        val blockFrame = block.boundingBox
                        for (line in block.lines) {
                            val lineText = line.text
                            val lineConfidence = line.confidence
                            val lineLanguages = line.recognizedLanguages
                            val lineCornerPoints = line.cornerPoints
                            val lineFrame = line.boundingBox
                            for (element in line.elements) {
                                val elementText = element.text
                                val elementConfidence = element.confidence
                                val elementLanguages = element.recognizedLanguages
                                val elementCornerPoints = element.cornerPoints
                                val elementFrame = element.boundingBox
                            }
                        }
                    }
*/
                }
                .addOnFailureListener {
                    // Task failed with an exception
                    // ...
                }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    mainImageView.setImageBitmap(data.extras.get("data") as Bitmap)
                    bitmapHolder = data.extras.get("data") as Bitmap
                }
            }
            else -> {
                Toast.makeText(this, "Req Code desconhecido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun runTextRecognition() {
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage)
//        FirebaseVisionTextRecognizer detector = FirebaseVisionTextRecognizer.getInstance().getOnDeviceTextRecognizer()
//        mTextButton.setEnabled(false)
//
//    }
}
