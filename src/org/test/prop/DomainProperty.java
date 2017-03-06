package org.test.prop;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 */
public class DomainProperty {
    private String domainClassName;
    private String packageName;
    private String id;
    FileProperty fileProperty;
    

	private ConcurrentHashMap<String, String> domainPropMap = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, String> domainDescPropMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> getDomainDescPropMap() {
		return domainDescPropMap;
	}

	public void setDomainDescPropMap(
			ConcurrentHashMap<String, String> domainDescPropMap) {
		this.domainDescPropMap = domainDescPropMap;
	}

	/**
     * Gets the domainClassName.
     * 
     * @return Returns the domainClassName.
     */
    public String getDomainClassName() {
        return domainClassName;
    }

    /**
     *  Sets the domainClassName.
     * 
     *  @param domainClassName The domainClassName to set.
     */
    public void setDomainClassName(String domainClassName) {
        this.domainClassName = domainClassName;
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
     * Gets the domainPropMap.
     * 
     * @return Returns the domainPropMap.
     */
    public ConcurrentHashMap<String, String> getDomainPropMap() {
        return domainPropMap;
    }

    /**
     *  Sets the domainPropMap.
     * 
     *  @param domainPropMap The domainPropMap to set.
     */
    public void setDomainPropMap(ConcurrentHashMap<String, String> domainPropMap) {
        this.domainPropMap = domainPropMap;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public FileProperty getFileProperty() {
    	return fileProperty;
    }
    
    public void setFileProperty(FileProperty fileProperty) {
    	this.fileProperty = fileProperty;
    }
}
