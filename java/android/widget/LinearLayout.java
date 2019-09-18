package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LinearLayout extends android.view.ViewGroup {

	
	
	public LinearLayout(Context cont, AttributeSet attr, int i) {
		super(cont, attr, i);
	}


	public void onDraw(Canvas c) {
		Log.i("draw called","linear layout ${Exception().stackTrace[1].toString()}");		 
		 c.drawARGB(255, 255, 0, 0);
        Paint p =  new Paint();
        p.setColor(0xFF00FF00);
        p.setStyle(Paint.Style.FILL);
        c.drawRect(0.0f, 0.0f, c.getWidth(), c.getHeight(), p);
        p.setColor(0xFFFF0000);
        c.drawLine(0.0f, 0.0f, c.getWidth(), c.getHeight(), p);
		 
	}
	
	
	public void onLayout(Boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i("layout called", "with ${children.size} children");
		int y = 0;
		int i = 0;
		for (View child : children) {
			if(y>getHeight()){ break; }
			int height = child.getHeight();
			child.layout(0,y,child.getWidth(),y+height);
			Log.i("layout child $i", "width:$width, height:$height, y:$y");
			y+=height;
			i++;
		}
	}
}
