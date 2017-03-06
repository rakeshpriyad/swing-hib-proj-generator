package org.test.generator.form;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.test.generator.base.BaseGeneratorUtil;
import org.test.prop.DomainProperty;
import org.test.prop.FormProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** */
public class JFrameGenJavaUtil extends BaseGeneratorUtil {

	
	public void init(){
		generateBaseService();
		generateBaseServiceImpl();
	}
	/** */
	public static void main(String[] args) {

		JFrameGenJavaUtil util = new JFrameGenJavaUtil();
		// util.readControllerXMLConfig();

		// util.generateBaseDao("genericDao.vm");
		// util.generateBaseDaoImpl("genericDaoImpl.vm");
		// util.readControllerXMLConfig();
		util.init();
		util.generateDomain();

	}

	public void generateDomain() {

		try {

			File fXmlFile = new File(
					"G:/php-codegen-ws/swing-proj-generator/src/domain.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			ConcurrentHashMap<String, String> menuMap = new ConcurrentHashMap<>();
			NodeList nList = doc.getElementsByTagName("entity");
			DomainProperty domainProp = new DomainProperty();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				ConcurrentHashMap<String, String> domainPropMap = new ConcurrentHashMap<>();
				ConcurrentHashMap<String, String> domainDescPropMap = new ConcurrentHashMap<>();
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String domainClassName = eElement.getAttribute("id");
					String id = eElement.getAttribute("primaryKey");
					domainProp.setId(id);
					String packageName = eElement.getAttribute("package");
					int length = eElement.getElementsByTagName("property")
							.getLength();
					for (int i = 0; i < length; i++) {
						Element pElement = (Element) eElement
								.getElementsByTagName("property").item(i);
						String propertyName = pElement.getAttribute("name");
						String propertyType = eElement
								.getElementsByTagName("property").item(i)
								.getTextContent();
						domainPropMap.put(propertyName, propertyType);
					}
					domainProp.setDomainClassName(domainClassName);
					domainProp.setPackageName(packageName);
					domainProp.setDomainPropMap(domainPropMap);
					menuMap.put(domainClassName, domainClassName);
				}
				/*generateDomain(domainProp);
				generateService(domainProp);
				generateDao(domainProp);
				generateDaoImpl(domainProp);
				generateServiceImpl(domainProp);*/
				generateSwingForm(domainProp);
			}

			generateSwingMenu(menuMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	public void readControllerXMLConfig() {

		try {

			File fXmlFile = new File(
					"G:/php-codegen-ws/swing-proj-generator/src/form.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("form");
			FormProperty formProp = new FormProperty();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				ConcurrentHashMap<String, String> formPropMap = new ConcurrentHashMap<>();
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String frameClassName = eElement.getAttribute("id");
					String packageName = eElement.getAttribute("package");
					int length = eElement.getElementsByTagName("property")
							.getLength();
					for (int i = 0; i < length; i++) {
						Element pElement = (Element) eElement
								.getElementsByTagName("property").item(i);
						String propertyName = pElement.getAttribute("name");
						String propertyLabel = eElement
								.getElementsByTagName("property").item(i)
								.getTextContent();
						formPropMap.put(propertyName, propertyLabel);
					}
					formProp.setFormClassName(frameClassName);
					formProp.setPackageName(packageName);
					formProp.setFormPropMap(formPropMap);
				}
				//generateSwingForm("SwingJFrame.vm", formProp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
