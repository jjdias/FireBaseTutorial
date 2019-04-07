package com.example.firebasetutorial
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer

class MainActivity : AppCompatActivity() {


    val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraButton.setOnClickListener{
            val callCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCamera.resolveActivity(packageManager) != null){
                startActivityForResult(callCamera, CAMERA_REQUEST_CODE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE ->{
                if (resultCode == Activity.RESULT_OK && data != null){
                    mainImageView.setImageBitmap(data.extras.get("data") as Bitmap)
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
