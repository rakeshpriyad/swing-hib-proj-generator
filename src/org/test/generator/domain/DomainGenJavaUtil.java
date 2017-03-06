package org.test.generator.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.test.prop.DomainProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** */
public class DomainGenJavaUtil {

    /** */
	public static void main(String[] args) {

		DomainGenJavaUtil util = new DomainGenJavaUtil();
		util.readControllerXMLConfig();

	}

    /** */
    public void generateDomain(DomainProperty domainProp) {
		Map<String, String> constMap = new HashMap<>();

		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		VelocityEngine ve = new VelocityEngine();
		ve.init(props);
        Template t = ve.getTemplate("domain.vm");
		VelocityContext context = new VelocityContext();
		context.put("PACKAGE_NAME", domainProp.getPackageName());
        context.put("DOMAIN_CLASS_NAME", domainProp.getDomainClassName());
		context.put("constMap", constMap);
        context.put("propMap", domainProp.getDomainPropMap());

		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		
		try {
            File file =
                    new File("C:/Users/aayushraj/Desktop/swing-generated" + "//"
                            + domainProp.getDomainClassName() + ".java");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(writer.toString());
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(writer.toString());

	}

    /** */
	public void readControllerXMLConfig() {

		try {

            File fXmlFile = new File("G:/php-codegen-ws/swing-proj-generator/src/domain.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("entity");
            DomainProperty domainProp = new DomainProperty();
            
			for (int temp = 0; temp < nList.getLength(); temp++) {
				ConcurrentHashMap<String, String> domainPropMap = new ConcurrentHashMap<>();
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

                    String domainClassName = eElement.getAttribute("id");
                    String packageName = eElement.getAttribute("package");
                    int length = eElement.getElementsByTagName("property").getLength();
                    for (int i = 0; i < length; i++) {
                        Element pElement = (Element) eElement.getElementsByTagName("property").item(i);
                        String propertyName = pElement.getAttribute("name");
                        String propertyType = eElement.getElementsByTagName("property").item(i).getTextContent();
                        domainPropMap.put(propertyName, propertyType);
                    }
                    domainProp.setDomainClassName(domainClassName);
					domainProp.setPackageName(packageName);
                    domainProp.setDomainPropMap(domainPropMap);
				}
                generateDomain(domainProp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
