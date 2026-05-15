package com.example.gramayatri.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object QRCodeGenerator {

    fun generateQRCode(content: String, size: Int = 512): Bitmap? {
        return try {
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, size, size)
        } catch (e: Exception) {
            null
        }
    }
}