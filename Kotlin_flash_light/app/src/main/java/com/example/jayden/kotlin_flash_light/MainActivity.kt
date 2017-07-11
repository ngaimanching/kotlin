package com.example.jayden.kotlin_flash_light

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var flashLightStatus = true
    var button: Button? = null
    lateinit var params: Camera.Parameters
    var cam: Camera? = null
    lateinit var camManager: CameraManager
    lateinit var cameraId: String
    var cameraDevice: CameraDevice? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button = findViewById(R.id.button) as Button

        if (isFlashSupported) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
                cameraId = camManager.cameraIdList[0]

            }

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                cam = Camera.open()
                params = cam!!.parameters
            }
        } else {
            Log.d("android", "Your Devices is not supported!!")
        }

        button!!.setOnClickListener {

            if (flashLightStatus) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    FlashLightOn()
                } else {
                    turnOnLowApiCam()
                }

                flashLightStatus = false
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    FlashLightOff()
                } else {
                    turnOffLowApiCam()
                }

                flashLightStatus = true
            }


        }

    }

    private val isFlashSupported: Boolean
        get() {
            val pm = packageManager
            return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        }


    // flash light on

    private fun FlashLightOn() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camManager.setTorchMode(cameraId, true)
            }
        } catch (e: Exception) {

        }
    }

    // flash light off
    private fun FlashLightOff() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camManager.setTorchMode(cameraId, false)
                closeCamApi()
            }
        } catch (e: Exception) {

        }
    }


    // turnOn or turnOFF method for android low version use

    fun turnOnLowApiCam() {
        params.flashMode = Camera.Parameters.FLASH_MODE_TORCH
        cam!!.parameters = params
        cam!!.startPreview()
    }

    fun turnOffLowApiCam() {
        params.flashMode = Camera.Parameters.FLASH_MODE_OFF
        cam!!.parameters = params
        cam!!.stopPreview()
    }

    private fun closeCamApi() {
        if (cameraDevice != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                cameraDevice!!.close()
            }
        }
    }
}
