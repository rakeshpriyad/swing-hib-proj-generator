package org.test.generator.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.test.prop.DomainProperty;
import org.test.prop.FileProperty;
import org.test.prop.FormProperty;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/** */
public abstract class BaseGeneratorUtil {
	private static final String BASE_PACKAGE_PATH = "/com/test/swing/spring";
	private static final String TEMPLATE_EXTENSION = ".vm";
	private static final String GENERIC_SERVICE = "GenericService";
	private static final String GENERIC_SERVICE_IMPL = GENERIC_SERVICE + "Impl";
	private static final String JAVA_EXTENSION = ".java";
	private static final String GENERIC_DAO_IMPL = "GenericDaoImpl";
	private static final String GENERIC_DAO = "GenericDao";
	private static final String DOMAIN_CLASS_NAME = "DOMAIN_CLASS_NAME";
	private static final String PACKAGE_NAME = "PACKAGE_NAME";
	private static final String PACKAGE_NAME_GENERIC_DAO = "com.test.swing.spring.generic.dao";
	private static final String PACKAGE_NAME_GENERIC_SERVICE = "com.test.swing.spring.generic.service";
	private static final String PACKAGE_NAME_GENERIC_SERVICE_IMPL = "com.test.swing.spring.generic.service.impl";
	private static final String PROJ_BASE_PATH = "G:/php-codegen-ws/spring-hib-swing/src/main/java";
	private static final String BASE_DAO_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/generic/dao";
	private static final String DOMAIN_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/domain";
	private static final String DAO_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/dao";
	private static final String DAO_IMPL_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/dao/impl";
	private static final String SERVICE_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/service";
	private static final String SERVICE_IMPL_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/service/impl";
	private static final String SWING_FORM_DESTIANTION_PATH = PROJ_BASE_PATH
			+ "/com/test/swing/spring/form";
	private static final String BASE_SERVICE_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/generic/service";
	private static final String BASE_SERVICE_IMPL_DESTIANTION_PATH = PROJ_BASE_PATH
			+ BASE_PACKAGE_PATH + "/generic/service/impl";
	private static VelocityEngine veEngine;
	static {
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		veEngine = new VelocityEngine();
		veEngine.init(props);
	}

	public void copyDIR(String destDIRToCopy, String destPath) {
		try {
			File destDIR = new File(destPath + File.separator + destDIRToCopy);
			if (!destDIR.exists()) {
				destDIR.mkdirs();
			}

			File dIRToCopy = new File(destDIRToCopy);
			for (File f : dIRToCopy.listFiles()) {
				File destFile = new File(destPath + File.separator
						+ destDIRToCopy + File.separator + f.getName());
				Files.copy(f.toPath(), destFile.toPath(),
						java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void generateBaseService() {
		genericGenerate(
				PACKAGE_NAME_GENERIC_SERVICE,
				setGenericFileProperty(BASE_SERVICE_DESTIANTION_PATH,
						GENERIC_SERVICE, "genericService"));
	}

	public void generateBaseServiceImpl() {
		genericGenerate(
				PACKAGE_NAME_GENERIC_SERVICE_IMPL,
				setGenericFileProperty(BASE_SERVICE_IMPL_DESTIANTION_PATH,
						GENERIC_SERVICE_IMPL, "genericServiceImpl"));
	}

	/** */
	public void generateBaseDaoImpl() {
		genericGenerate(
				PACKAGE_NAME_GENERIC_DAO,
				setGenericFileProperty(BASE_DAO_DESTIANTION_PATH,
						GENERIC_DAO_IMPL, "genericDaoImpl"));
	}

	/** */
	public void generateBaseDao() {
		genericGenerate(
				PACKAGE_NAME_GENERIC_DAO,
				setGenericFileProperty(BASE_DAO_DESTIANTION_PATH, GENERIC_DAO,
						"genericDao"));
	}

	private FileProperty setGenericFileProperty(String destinationDIR,
			String outputFile, String templateFileName) {
		FileProperty fileProperty = new FileProperty();
		fileProperty.setOutputFile(outputFile);
		StringWriter writer = new StringWriter();
		fileProperty.setDestinationDirectory(destinationDIR);
		fileProperty.setWriter(writer);
		fileProperty.setTemplateName(templateFileName);
		return fileProperty;
	}

	private void genericGenerate(String packageName, FileProperty fileProperty) {
		VelocityContext context = getVelocityContext(null);
		context.put(PACKAGE_NAME, packageName);
		Template t = getVelocityTemplate(fileProperty.getTemplateName() + ".vm");
		t.merge(context, fileProperty.getWriter());
		writeFile(fileProperty);
	}

	public static Template getVelocityTemplate(String templateFileName) {
		return veEngine.getTemplate(templateFileName);
	}

	public static VelocityContext getVelocityContext(DomainProperty domainProp) {
		VelocityContext context = new VelocityContext();
		if (domainProp != null) {
			context.put(PACKAGE_NAME, domainProp.getPackageName());
			context.put(DOMAIN_CLASS_NAME, domainProp.getDomainClassName());
			context.put("propMap", domainProp.getDomainPropMap());
			context.put("id", domainProp.getId());

		}
		return context;
	}

	/** */
	public void generateDao(DomainProperty domainProp) {
		setFileProperty(domainProp, "Dao", DAO_DESTIANTION_PATH, "Dao");
		generate(domainProp);
	}

	private void setFileProperty(DomainProperty domainProp, String type,
			String destinationDIR, String template) {
		FileProperty fileProperty = new FileProperty();
		fileProperty.setTemplateName(template + TEMPLATE_EXTENSION);
		fileProperty.setDestinationDirectory(destinationDIR);
		String outputFile = domainProp.getDomainClassName()
				+ (type == null ? "" : type);
		fileProperty.setOutputFile(outputFile);
		fileProperty.setWriter(new StringWriter());
		domainProp.setFileProperty(fileProperty);
	}

	private FileProperty setFilePropertyForMenu(
			ConcurrentHashMap<String, String> menuMap, String destinationDIR) {
		FileProperty fileProperty = new FileProperty();
		fileProperty.setTemplateName("menu" + TEMPLATE_EXTENSION);
		fileProperty.setDestinationDirectory(destinationDIR);
		String outputFile = "ShoppingMenu";
		fileProperty.setOutputFile(outputFile);
		fileProperty.setWriter(new StringWriter());
		return fileProperty;
	}

	private void generateMenu(FileProperty fileProperty,ConcurrentHashMap<String, String> menuMap) {
		Template t = getVelocityTemplate(fileProperty.getTemplateName());
		VelocityContext context = getVelocityContext(null);
		context.put("PACKAGE_NAME", "com.test.swing.spring");
		context.put("propMap", menuMap);
		t.merge(context, fileProperty.getWriter());
		writeFile(fileProperty);
	}

	private void generate(DomainProperty domainProp) {
		Template t = getVelocityTemplate(domainProp.getFileProperty()
				.getTemplateName());
		t.merge(getVelocityContext(domainProp), domainProp.getFileProperty()
				.getWriter());
		writeFile(domainProp.getFileProperty());
	}

	/** */
	public void generateDaoImpl(DomainProperty domainProp) {
		setFileProperty(domainProp, "DaoImpl", DAO_IMPL_DESTIANTION_PATH,
				"DaoImpl");
		generate(domainProp);
	}

	/** */
	public void generateDomain(DomainProperty domainProp) {
		setFileProperty(domainProp, null, DOMAIN_DESTIANTION_PATH, "domain");
		generate(domainProp);
	}

	public void generateService(DomainProperty serviceProp) {
		setFileProperty(serviceProp, "Service", SERVICE_DESTIANTION_PATH,
				"service");
		generate(serviceProp);
	}

	public void generateServiceImpl(DomainProperty domainProp) {
		setFileProperty(domainProp, "ServiceImpl",
				SERVICE_IMPL_DESTIANTION_PATH, "serviceImpl");
		generate(domainProp);
	}

	/** */
	/*
	 * public void generateSwingForm(String templateFileName, FormProperty
	 * FormProp) { Template t = getVelocityTemplate(templateFileName);
	 * VelocityContext context = getVelocityContext(null);
	 * formPropContext(FormProp, context); StringWriter writer = new
	 * StringWriter(); t.merge(context, writer);
	 * 
	 * // String // destiantionPath=
	 * "G:/php-codegen-ws/spring-hib-swing/src/main/java/com/test/swing/spring/generic/dao"
	 * ; writeFile(writer, SWING_FORM_DESTIANTION_PATH,
	 * FormProp.getFormClassName() + "Form", JAVA_EXTENSION); }
	 */

	public void generateSwingForm(DomainProperty formProp) {
		setFileProperty(formProp, "Panel", SWING_FORM_DESTIANTION_PATH,
				"SwingJPanel");
		generate(formProp);
	}

	public void generateSwingMenu(ConcurrentHashMap<String, String> menuMap) {
		FileProperty fp= setFilePropertyForMenu(menuMap, SWING_FORM_DESTIANTION_PATH);
		generateMenu(fp,menuMap);
	}

	private void formPropContext(FormProperty formProp, VelocityContext context) {
		Map<String, String> constMap = new HashMap<>();

		context.put("PACKAGE_NAME", formProp.getPackageName());
		context.put("FORM_CLASS_NAME", formProp.getFormClassName());
		context.put("constMap", constMap);
		context.put("propMap", formProp.getFormPropMap());
	}

	public static Document getXMLDocument(String xMLFile) {

		File fXmlFile = new File(xMLFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	/*
	 * public static void writeFile(StringWriter writer,String destPath, String
	 * outputFile, String outputFileExt){
	 */
	public static void writeFile(FileProperty fileProp) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			File destDIR = new File(fileProp.getDestinationDirectory());
			if (!destDIR.exists()) {
				destDIR.mkdirs();
			}

			File file = new File(fileProp.getDestinationDirectory()
					+ File.separator + fileProp.getOutputFile()
					+ fileProp.getOutputFileExt());
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(fileProp.getWriter().toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
