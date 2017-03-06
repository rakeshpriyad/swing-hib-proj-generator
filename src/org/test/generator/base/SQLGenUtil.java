package org.test.generator.base;

import java.io.File;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.test.prop.DomainProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** */
public class SQLGenUtil extends BaseGeneratorUtil {

    /** */
	public static void main(String[] args) {

		SQLGenUtil util = new SQLGenUtil();
		util.readControllerXMLConfig();

	}

	public void generateTable(DomainProperty domainProp,StringWriter writer) {
		Template t = getVelocityTemplate("table.vm");
		
		VelocityContext context=getVelocityContext(domainProp);
		t.merge(context, writer);
		
	}
	
	public void generateDB(DomainProperty domainProp,StringWriter writer) {
		Template t = getVelocityTemplate("db.vm");
		
		VelocityContext context=getVelocityContext(domainProp);
		context.put("DATA_BASE_NAME", "shopping_kart");
		t.merge(context, writer);
		
	}

	/** */
	public void readControllerXMLConfig() {

		try {

            File fXmlFile = new File("G:/spring-mvc-ws/VelocityCodeGenerator/src/domain.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("entity");
            DomainProperty domainProp = new DomainProperty();
            StringWriter writer=new StringWriter();
            generateDB(domainProp, writer);
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
				generateTable(domainProp,writer);	
			}
			
			String outputFileExt = ".sql";
			String destionationPath="G:/spring-mvc-ws/SpringMVCGenerated";
			String outputFile = "schema_sql";
			//writeFile(writer, destionationPath,outputFile, outputFileExt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
