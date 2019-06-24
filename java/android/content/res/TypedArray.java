/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.content.res;

import java.util.ArrayList;

import android.content.Context;

/**
 *
 * @author Joshua
 */
public class TypedArray {
	private ArrayList<Integer> name = new ArrayList<Integer>();
	private ArrayList<String> value = new ArrayList<String>();
	private Context c;
	private TypedArray(Context c, ArrayList<Integer> name,ArrayList<String>  Value) { this.c = c; this.name = name; this.value = value; }

    public int getDimensionPixelSize(int i, int defaultValue) {
        throw new UnsupportedOperationException("function not finished");
    }

    public void recycle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static class builder{
    	ArrayList<Integer> name = new ArrayList<Integer>();
    	ArrayList<String> value = new ArrayList<String>();
    	
    	public builder() {}
    	public void add(int attrName, String attrValue){
    		name.add(attrName);
    		value.add(attrValue);
    	}
    	public TypedArray build(Context C) {
    		return new TypedArray(C,name, value);
    	}
    }
}
