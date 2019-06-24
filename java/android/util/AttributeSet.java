/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.util;

/**
 *
 * @author Joshua
 */
public interface AttributeSet {
    
    abstract boolean getAttributeBooleanValue(String namespace, String attribute, boolean defaultValue);

    abstract boolean getAttributeBooleanValue(int index, boolean defaultValue);

    abstract int getAttributeCount();
    
    abstract float getAttributeFloatValue(int index, float defaultValue);

    abstract float getAttributeFloatValue(String namespace, String attribute, float defaultValue);

    abstract int getAttributeIntValue(String namespace, String attribute, int defaultValue);

    abstract int getAttributeIntValue(int index, int defaultValue);

    abstract int getAttributeListValue(int index, String[] options, int defaultValue);

    abstract int getAttributeListValue(String namespace, String attribute, String[] options, int defaultValue);
    abstract String getAttributeValue(int index);

    abstract String getAttributeName(int index);
    public abstract String getAttributeValue (String namespace, String name);
    
    /**
     * the name of the attribute in the hext form
     * <br/> example:
     *<br/>
     *ifattrs[0] = {minWidth = 6}
     *<br/>
     *this function returns {@link android.R.attr#minWidth}
     * @param index
     * @return
     */
    abstract int getAttributeNameResource(int index);

    /**
     * the value of the attribute in the hex form
     * * <br/> example:
     *<br/>
     *ifattrs = {minWidth = 6, minHeight = 7}
     *<br/>
     * @param namespace
     * @param attribute = {@link android.R.attr#minWidth}
     * @param defaultValue = 0
     * @return 6
     */
    abstract int getAttributeResourceValue(String namespace, String attribute, int defaultValue);

    abstract int getAttributeResourceValue(int index, int defaultValue);
    
    
    
    
}
