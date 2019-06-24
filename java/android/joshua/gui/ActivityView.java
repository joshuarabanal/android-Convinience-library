/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.joshua.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @author Joshua
 */
public class ActivityView extends ViewGroup{
    private View root;
    
    public ActivityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        root = new LinearLayout(context,null,defStyleAttr);
    }

    
    

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c); //To change body of generated methods, choose Tools | Templates.
        Log.i("Activity view", "ondraw:"+c.getWidth()+","+c.getHeight());
        
        
       
        
        if(root != null){
            root.onDraw(c);
            
        }
    }

    @Override
    public void addView(View child,int index, LayoutParams lp) {
       root = child;
    }

    @Override
	public void onLayout(boolean changed, int l, int t, int r, int b) {
        root.layout(0, 0, getWidth(), getHeight());
    }
    
    
    
}
