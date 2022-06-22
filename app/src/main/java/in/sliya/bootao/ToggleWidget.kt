package `in`.sliya.bootao

import android.app.PendingIntent
import android.app.WallpaperManager
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast


private const val TAG = "ToggleWidget"

class ToggleWidget() : AppWidgetProvider() {

    companion object {
        var weeb = false
        // TODO: make a gui in mainactivity to select the desired file.
        private val bm1: Bitmap = BitmapFactory.decodeFile("/storage/emulated/0/one.png");
        private val bm2: Bitmap = BitmapFactory.decodeFile("/storage/emulated/0/two.png");

    }


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {

        Log.e(TAG, "before onreceive: action: ${intent.action}, weeb: $weeb")
        super.onReceive(context, intent)

        Log.e(TAG, "after onreceive; action: ${intent.action}, weeb: $weeb")

        if ("ACTION_CHANGE_WALL" == intent.action) {
            Log.e(TAG, "clicked wow!")
            Toast.makeText(context, "Changing wallpaper!", Toast.LENGTH_SHORT).show()

            if (!weeb) {
                weeb = true
                // TODO: very slow, make it faster
                WallpaperManager.getInstance(context).setBitmap(bm1)
                Log.e(TAG, "done settings bm1")
            } else {
                weeb = false

                WallpaperManager.getInstance(context).setBitmap(bm2)
                Log.e(TAG, "done setting bm2")
            }

        }


        Log.e(TAG, "end of onreceive; action: ${intent.action}, weeb: $weeb")

    }



}

fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {



    val intent: Intent = Intent(context, ToggleWidget::class.java)
    intent.action = "ACTION_CHANGE_WALL"

    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

    val views = RemoteViews(context.packageName, R.layout.toggle_widget)

    views.setOnClickPendingIntent(R.id.btn_toggle, pendingIntent)


    appWidgetManager.updateAppWidget(appWidgetId, views)
}

