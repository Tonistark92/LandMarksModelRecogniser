package com.iscoding.landmarkaimodel.domain

import android.graphics.Bitmap

interface LandmarkClassifier  {
    fun classify(bitmap: Bitmap, rotation: Int): List<ModelOutPut>
}