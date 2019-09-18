package android.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;



public class ViewGroup extends View implements LayoutManager {
	
	protected ArrayList<View> children = new ArrayList<View>();
	private ArrayList<LayoutParams> childrenParams = new ArrayList<LayoutParams>();

	
	public ViewGroup(Context cont,  AttributeSet attr, int i ) {
		super(cont, attr, i);
	}
	
	public void onLayout (  boolean changed,    int left,  int top, int right, int bottom){}
	
	
	public void addView(  View child, int index,  LayoutParams lp){
    	children.add(index,child);
		childrenParams.add(index,lp);
		validate();
    }
	
	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("use #addView(View, LayoutParams); instead");
	}

	@Override
	public void layoutContainer(Container arg0) {
	    	// TODO Auto-generated method stub
			Log.i("layout container", "layout container");
	    	this.parentContainer = (getParent());
	    	for( View child : children) {
	    		child.parentContainer = (getParent());
	    	}
	    	this.layout( 0,0,getWidth(), getHeight());
	    }

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		 Dimension retu = getParent().getSize();
	    	this.onMeasure(
	    			MeasureSpec.makeMeasureSpec(retu.width, MeasureSpec.AT_MOST),
	    			MeasureSpec.makeMeasureSpec(retu.height, MeasureSpec.AT_MOST)
	    			);
	    	retu.setSize(getMeasuredWidth(), getMeasuredHeight());
	    	return retu;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		throw new UnsupportedOperationException("use #addView(View, LayoutParams); instead");
	}

	
	
	public class LayoutParams {
        int width = 0;
		int  height = 0;
    }
}
