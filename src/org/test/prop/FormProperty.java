package org.test.prop;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 */
public class FormProperty {
	
    private String formClassName;

    private String packageName;

    private ConcurrentHashMap<String, String> formPropMap = new ConcurrentHashMap<>();

    /**
     * Gets the formClassName.
     * 
     * @return Returns the formClassName.
     */
    public String getFormClassName() {
        return formClassName;
    }

    /**
     *  Sets the formClassName.
     * 
     *  @param formClassName The formClassName to set.
     */
    public void setFormClassName(String formClassName) {
        this.formClassName = formClassName;
    }

    /**
     * Gets the packageName.
     * 
     * @return Returns the packageName.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     *  Sets the packageName.
     * 
     *  @param packageName The packageName to set.
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets the formPropMap.
     * 
     * @return Returns the formPropMap.
     */
    public ConcurrentHashMap<String, String> getFormPropMap() {
        return formPropMap;
    }

    /**
     *  Sets the domainPropMap.
     * 
     *  @param formPropMap The formPropMap to set.
     */
    public void setFormPropMap(ConcurrentHashMap<String, String> formPropMap) {
        this.formPropMap = formPropMap;
    }
}
