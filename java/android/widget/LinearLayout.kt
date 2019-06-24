package android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.graphics.Canvas
import android.util.Log

import android.graphics.Paint;

class LinearLayout(arg0: Context, arg1: AttributeSet?, arg2: Int) : ViewGroup(arg0, arg1, arg2) {

   override fun onDraw(c: Canvas) {
		Log.i("draw called","linear layout ${Exception().stackTrace[1].toString()}")		 
		 c.drawARGB(255, 255, 0, 0)
        var p =  Paint()
        p.setColor(0xFF00FF00.toInt())
        p.setStyle(Paint.Style.FILL);
        c.drawRect(0.0f, 0.0f, c.width.toFloat(), c.height.toFloat(), p)
        p.setColor(0xFFFF0000.toInt());
        c.drawLine(0.0f, 0.0f, c.width.toFloat(), c.height.toFloat(), p)
		 
	}
	
	
	override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
		// TODO Auto-generated method stub
		Log.i("layout called", "with ${children.size} children")
		var y = 0
		var i = 0;
		for (child in children) {
			if(y>height){ break; }
			var height = child.height
			child.layout(0,y,child.width,y+height)
			Log.i("layout child $i", "width:$width, height:$height, y:$y")
			y+=height
			i++
		}
	}
	

}