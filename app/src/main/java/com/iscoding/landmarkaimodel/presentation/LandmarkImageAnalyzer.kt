package com.iscoding.landmarkaimodel.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.iscoding.landmarkaimodel.domain.LandmarkClassifier
import com.iscoding.landmarkaimodel.domain.ModelOutPut
import com.iscoding.landmarkaimodel.presentation.util.centerCrop

class LandmarkImageAnalyzer(
    private val classifier: LandmarkClassifier,
    private val onResults: (List<ModelOutPut>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}