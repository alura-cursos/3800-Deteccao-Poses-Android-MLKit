package com.alura.mexeai.ui.screens.pose

import android.graphics.PointF
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.mexeai.ui.screens.camera.CameraPreview
import com.alura.mexeai.ui.screens.camera.CameraViewModel
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions


@OptIn(ExperimentalGetImage::class)
@Composable
fun PoseDetectionScreen() {
    val viewModel = hiltViewModel<CameraViewModel>()
    val state by viewModel.uiState.collectAsState()


    var pose by remember { mutableStateOf<Pose?>(null) }

    val options = remember {
        PoseDetectorOptions.Builder()
            .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
            .build()
    }

    val poseDetector = remember { PoseDetection.getClient(options) }

    val imageAnalyzer = ImageAnalysis.Analyzer { imageProxy ->
        imageProxy.image?.let { inputImage ->
            viewModel.setScreenSize(Pair(imageProxy.height, imageProxy.width))

            poseDetector
                .process(inputImage, imageProxy.imageInfo.rotationDegrees)
                .addOnSuccessListener { detectedPose ->
                    pose = detectedPose
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    state.currentScaleX?.let { currentScaleX ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(scaleX = currentScaleX)
                .onSizeChanged {
                    viewModel.setPreviewSize(Pair(it.width, it.height))
                }
        ) {
            // 1 Camera Controller
            val context = LocalContext.current
            val cameraController = remember {
                LifecycleCameraController(context).apply {
                    cameraSelector = viewModel.getCameraSelector()
                    setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                    setImageAnalysisAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        imageAnalyzer
                    )
                }
            }

            // 2 Camera Preview
            CameraPreview(cameraController)


            // 3 Pose Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                pose?.let {
                    PoseDraw(
                        pose = it,
                        scaleFactor = state.scaleFactor,
                        extraOffset = state.postScaleWidthOffset
                    )
                }

//                Box(
//                    modifier = Modifier
//                        .offset(
//                            x = pointPosition.x.pxToDp(),
//                            y = pointPosition.y.pxToDp()
//                        )
//                        .size(50.dp)
//                        .background(Color.Red, CircleShape)
//                )
            }
        }
    }
}