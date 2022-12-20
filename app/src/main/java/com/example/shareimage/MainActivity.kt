package com.example.shareimage

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var ivShare:Button
    lateinit var urlShare:Button
    lateinit var imgView:ImageView
    lateinit var btnApk:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivShare=findViewById(R.id.ivShare)
        urlShare=findViewById(R.id.apk_share)
        btnApk=findViewById(R.id.btn_apk)
        imgView=findViewById(R.id.img_view)

        ivShare.setOnClickListener {
            val b=BitmapFactory.decodeResource(resources,R.drawable.dhoni)
            val intent=Intent()
            intent.action=Intent.ACTION_SEND

            val path=MediaStore.Images.Media.insertImage(contentResolver,b,"Title",null)
            val uri=Uri.parse(path)

            intent.putExtra(Intent.EXTRA_STREAM,uri)
            intent.type="image/*"
            startActivity(Intent.createChooser(intent,"Share To :"))
        }

        urlShare.setOnClickListener {
            val intent=Intent()
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT,"ShareImage")
            var shareMessege:String="https://www.pinterest.com/pin/733031276833258839/"+BuildConfig.APPLICATION_ID+"\n\n"
            intent.putExtra(Intent.EXTRA_TEXT,shareMessege)
            startActivity(Intent.createChooser(intent,"share by"))
            //https://play.google.com/store/apps/details?=
        }
        btnApk.setOnClickListener {
            var api:ApplicationInfo=applicationContext.applicationInfo
            var apkPath:String=api.sourceDir

            var intent=Intent()
            intent.setType("application/vnd.android.package-archive")
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(File(apkPath)))
            startActivity(Intent.createChooser(intent,"Share App"))

        }
    }
}