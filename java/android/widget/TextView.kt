package android.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.util.AttributeSet
import android.util.Log
import android.view.View

class TextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
	private var text = "enter text here"
	private var textColor: Paint? = null

	init {
// TODO Auto-generated constructor stub
		textColor = Paint()
		textColor!!.setColor(0x000000)
		textColor!!.setStyle(Style.FILL)
		minimumWidth= (20);
		minimumHeight = (20);
	}
	

	public override fun onDraw(c: Canvas) {
// TODO Auto-generated method stub
		super.onDraw(c)
		Log.i("text view", "on draw")
		c!!.drawText(text, 0, text!!.length, 0.0f, c!!.height / 4.0f, textColor)
	}
}