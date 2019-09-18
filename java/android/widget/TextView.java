package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TextView extends View {
	private String text = "enter text here";
	private Paint textColor = new Paint();

	public TextView(Context context, AttributeSet attrs, int defaultStyleAttr) {
		super(context, attrs, defaultStyleAttr);
		textColor.setColor(0x000000);
		textColor.setStyle(Style.FILL);
		minimumWidth= (20);
		minimumHeight = (20);
	}
	


	

	public void onDraw(Canvas c) {
// TODO Auto-generated method stub
		super.onDraw(c);
		Log.i("text view", "on draw");
		c.drawText(text, 0, text.length(), 0.0f, c.getHeight() / 4.0f, textColor);
	}

}
