package android.view

import java.awt.LayoutManager
import android.util.AttributeSet
import android.content.Context
import java.util.ArrayList
import java.awt.Container
import java.awt.Component
import java.awt.Dimension
import android.util.Log

open class ViewGroup : android.view.View , LayoutManager{
	
    open var children: ArrayList<View> =  ArrayList<View>();
    var childrenParams:ArrayList<LayoutParams?>  =  ArrayList<LayoutParams?>();
	
	constructor(cont:Context, attr:AttributeSet?, i: Int)  : super(cont,attr,i);
	
	open fun onLayout ( changed: Boolean,   left: Int,  top:Int, right:Int, bottom:Int){}
	
	open fun addView( child: View, index:Int = children.size,  lp: LayoutParams? = null){
    	children.add(index,child);
		childrenParams.add(index,lp);
		validate();
    }
	
	
	
	
	//overriden LayoutManager functions
   override fun layoutContainer( parent: Container) {
    	// TODO Auto-generated method stub
		Log.i("layout container", "layout container")
    	this.parentContainer = parent;
    	for( child: View in  children) {
    		child.parentContainer = parent;
    	}
    	this.layout( 0,0,width, height);
    }
	
	override fun addLayoutComponent( name: String,  item: Component) { throw  UnsupportedOperationException("use #addView(View, LayoutParams); instead"); }
    override fun removeLayoutComponent( item: Component)  { throw  UnsupportedOperationException("use #addView(View, LayoutParams); instead"); }
    override fun   preferredLayoutSize( parent: Container):Dimension { 
    	 var retu = parent.size;
    	this.onMeasure(MeasureSpec.makeMeasureSpec(retu.width, MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(retu.height, MeasureSpec.AT_MOST));
    	retu.setSize(measuredWidth, measuredHeight);
    	return retu;
    }
    override fun   minimumLayoutSize( parent: Container):Dimension {	return  Dimension(minimumWidth, minimumHeight); }
	
	class LayoutParams {
        var width = 0
		var  height = 0;
    }
}