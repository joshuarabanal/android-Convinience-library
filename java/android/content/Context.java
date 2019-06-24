/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.content;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.io.File;

import javax.swing.JFrame;

/**
 *
 * @author Joshua
 */
public class Context {
    public Context() {
    }
    public File getExternalFilesDir(String type){
        if(type == null){
            type = "files";
        }
        File retu =  new File(System.getProperty("user.dir"),type);
        retu.mkdirs();
        return retu;
    }

    public TypedArray obtainStyledAttributes(AttributeSet attrs, int[] set) {
    	TypedArray.builder retu = new TypedArray.builder();
    	for(int i = 0; i<attrs.getAttributeCount(); i++) {
    		int attrNameInt= attrs.getAttributeNameResource(i);
    		for(int item : set) {
    			if(item == attrNameInt) {
    				retu.add(attrNameInt, attrs.getAttributeValue(i));
    			}
    		}
    		
    	}
    	return retu.build(this);
    }

  

   
}
