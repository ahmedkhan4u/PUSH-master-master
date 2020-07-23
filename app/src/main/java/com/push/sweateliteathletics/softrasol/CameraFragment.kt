package com.push.sweateliteathletics.softrasol


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.iammert.library.cameravideobuttonlib.CameraVideoButton
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.controls.Preview
import com.otaliastudios.cameraview.overlay.OverlayLayout
import com.squareup.picasso.Picasso
import java.io.File
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : Fragment() {

    private var camera: CameraView? = null
    internal lateinit var btnCapture: CameraVideoButton
    private val controlPanel: ViewGroup? = null
    private var mCaptureTime: Long = 0
    internal var imageView: ImageView? = null

    internal var textWatermart: TextView? = null

    private var cameraProgressBar: ProgressBar? = null

    private var btnSwitchCamera: ImageView? = null

    var mFirestore: FirebaseFirestore? = null
    private var mStorage: StorageReference? = null

    var dialog: Dialog? = null



    //permission is automatically granted on sdk<23 upon installation
    val isStoragePermissionGranted: Boolean
        get() {
            val TAG = "Storage Permission"
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity!!.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG, "Permission is granted")
                    return true
                } else {
                    Log.v(TAG, "Permission is revoked")
                    ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                    return false
                }
            } else {
                Log.v(TAG, "Permission is granted")
                return true
            }
        }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_camera, container, false)

        activity!!.window.statusBarColor = ContextCompat.getColor(activity!!, R.color.black) //


        cameraProgressBar = v.findViewById(R.id.camera_progressbar)


        showProgressBar();
        getImageFromFirebaseAndSetOnImageView()

        camera = v.findViewById(R.id.camera)
        camera!!.setLifecycleOwner(viewLifecycleOwner)
        isStoragePermissionGranted


        //Image Watermark
        imageView = v.findViewById(R.id.overly_view)
        var param: OverlayLayout.LayoutParams = OverlayLayout.LayoutParams(175, 100)
        param.gravity = Gravity.RIGHT
        param.setMargins(0, 40, 40, 0);
        if(camera?.parent != null)
        {
            camera?.removeView(imageView);
        }
        camera?.addView(imageView,param)
        param.drawOnPreview = true; // draw on preview
        param.drawOnPictureSnapshot = true
        param.drawOnVideoSnapshot = true


        //Text Watermark
        textWatermart = v.findViewById(R.id.text_watermark)
        var param2: OverlayLayout.LayoutParams = OverlayLayout.LayoutParams(OverlayLayout.LayoutParams.WRAP_CONTENT, OverlayLayout.LayoutParams.WRAP_CONTENT)
        param2.gravity = Gravity.CENTER
        if(camera?.parent != null)
        {
            camera?.removeView(textWatermart);
        }
        camera?.addView(textWatermart,param2)
        param2.drawOnPreview = true; // draw on preview
        param2.drawOnPictureSnapshot = true
        param2.drawOnVideoSnapshot = true

        // When done, apply
        textWatermart?.setLayoutParams(param2);
        imageView?.setLayoutParams(param);


        btnCapture = v.findViewById(R.id.capture_btn)
        btnCapture.enablePhotoTaking(true);
        btnCapture.setVideoDuration(30000)
        btnCapture.enableVideoRecording(true)


        btnCapture.actionListener  = object : CameraVideoButton.ActionListener{

            override fun onStartRecord() {
                camera?.setMode(Mode.VIDEO)
                captureVideoSnapshot()
                Log.v("TEST", "Start recording video")

                camera!!.addCameraListener(object : CameraListener() {
                    override fun onVideoTaken(result: VideoResult) {
                        // Video was taken!
                        // Use result.getFile() to access a file holding
                        // the recorded video.
                        super.onVideoTaken(result)
                        VideoPreviewActivity.setVideoResult(result)
                        val intent = Intent(context, VideoPreviewActivity::class.java)
                        startActivity(intent)
                        Log.v("","onVideoTaken called! Launched activity.")
                    }
                    override fun onVideoRecordingStart() {
                        super.onVideoRecordingStart()
                        Log.v("Video: ","onVideoRecordingStart!")
                    }
                })
            }

            override fun onEndRecord() {
                Log.v("TEST", "Stop recording video")
                camera?.stopVideo();
                camera!!.addCameraListener(object : CameraListener() {
                    override fun onVideoRecordingEnd() {
                        super.onVideoRecordingEnd()
                        //message("Video taken. Processing...", false)
                        //com.otaliastudios.cameraview.demo.CameraActivity.LOG.w("onVideoRecordingEnd!")
                    }
                })
                Log.v("TEST", "Video Done")
            }

            override fun onDurationTooShortError() {
                Log.v("TEST", "Toast or notify user")
            }

            override fun onSingleTap() {
                Log.v("TEST", "Take photo here")

                capturePictureSnapshot()

                camera!!.addCameraListener(object : CameraListener() {
                    override fun onPictureTaken(result: PictureResult) {

                        super.onPictureTaken(result)
                        PicturePreviewActivity.setPictureResult(result)
                        val intent = Intent(activity, PicturePreviewActivity::class.java)
                        startActivity(intent)

                        // Access the raw data if needed.
                        //val data = result.data

                        return

                    }
                })
            }
        }


        //switch camera
        btnSwitchCamera = v.findViewById(R.id.btn_switch_cammera)

        btnSwitchCamera?.setOnClickListener(View.OnClickListener { toggleCamera() })


        //btnCapture.setOnClickListener { capturePicture() }

        return v

    }


    //region Permissions

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var valid = true
        for (grantResult in grantResults) {
            valid = valid && grantResult == PackageManager.PERMISSION_GRANTED
        }
        if (valid && !camera!!.isOpened) {
            camera!!.open()
        }
    }

    private fun capturePictureSnapshot() {
        if (camera!!.isTakingPicture) return
        if (camera!!.preview != Preview.GL_SURFACE) {
            //message("Picture snapshots are only allowed with the GL_SURFACE preview.", true)
            return
        }
        mCaptureTime = System.currentTimeMillis()
        //message("Capturing picture snapshot...", false)
        camera!!.takePictureSnapshot()
    }

    private fun captureVideoSnapshot() {
        if (camera!!.isTakingVideo) {
            //message("Already taking video.", false)
            return
        }
        if (camera!!.preview != Preview.GL_SURFACE) {
            //message("Video snapshots are only allowed with the GL_SURFACE preview.", true)
            return
        }
        val generator = Random()
        var n = 10000
        n = generator.nextInt(n)
        //message("Recording snapshot for 5 seconds...", true)
        val mediaFile = File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Push_Video" +n+ ".mp4")
        Log.d("MEDIA: ", "Ref: " + mediaFile)
        camera!!.takeVideoSnapshot(mediaFile ,  30000)
    }


    private fun toggleCamera() {
        if (camera!!.isTakingPicture || camera!!.isTakingVideo) return
        when (camera!!.toggleFacing()) {
            Facing.BACK -> {
            }
            Facing.FRONT -> {
            }
        }
    }

    private fun getImageFromFirebaseAndSetOnImageView() {
        val db = FirebaseFirestore.getInstance().collection("Watermarks")
        db.document("1").addSnapshotListener(EventListener { documentSnapshot, e ->
            if (e != null) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                return@EventListener
            }
            val image = documentSnapshot!!.getString("image_url")
            val text = documentSnapshot.getString("textwatermark")
            textWatermart!!.text = text
            Picasso.get().load(image).placeholder(R.drawable.appicon)
                    .resize(100,100)
                    .into(imageView)
            dialog?.cancel()
        })
    }

    fun showProgressBar() {
        dialog = Dialog(activity)
        dialog!!.setContentView(R.layout.progress_dialog)
        val progressBar = dialog!!.findViewById<ProgressBar>(R.id.progress_bar)
        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressBar.progress = 100
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

}// Required empty public constructor

