package `in`.sliya.bootao


import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import util.verifyStoragePermissions



private const val TAG = "MainActivity"



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyStoragePermissions(this)

        val bm1: Bitmap = BitmapFactory.decodeFile("/storage/emulated/0/one.png");
        val myImage: ImageView = findViewById(R.id.imageView)
        myImage.setImageBitmap(bm1)



    }


}



