package org.emf.example.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UML212UMLResource;
import org.eclipse.uml2.uml.resource.UML22UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;

public class ModelLoader {

	private static ResourceSet RESOURCE_SET = null;

	public ModelLoader() {
		RESOURCE_SET = new ResourceSetImpl();
		registerPackages(getResourceSet());
		registerResourceFactories();
	}

	/*
	 * This function converts input path to uri and return uri.
	 */
	public String getFileURI(String path) throws Exception {
		File f = new File(path);
		String uri = f.toURI().toString();
		return uri;
	}

	public Package loadModel(File file) throws Exception {

		String relPath = null;
		try {
			relPath = new File(".").getCanonicalPath();
			System.out.println(System.getProperty("user.dir"));
		} catch (IOException e1) {
			e1.printStackTrace();
		
			
		}

		URIConverter.URI_MAP.put(URI.createURI("platform:/plugin/org.eclipse.uml2.uml/"),
				URI.createURI("jar:file:" + relPath + "/jars/uml/org.eclipse.uml2.uml_5.1.0.v20150906-1225.jar!/"));

		Resource resource = null;

		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");

		resource = getResourceSet().createResource(URI.createFileURI(file.getCanonicalPath()));
		resource.load(options);

		Package _package = null;

		for (EObject ojEObject : resource.getContents()) {
			System.out.println(ojEObject.getClass());
		}

		_package = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);

		if (_package == null) {
			_package = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		}

		return _package;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerResourceFactories() {
		Map extensionFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);

		extensionFactoryMap.put(Ecore2XMLResource.FILE_EXTENSION, Ecore2XMLResource.Factory.INSTANCE);
		extensionFactoryMap.put("ecore", new EcoreResourceFactoryImpl());

		extensionFactoryMap.put(UML22UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);

		extensionFactoryMap.put("xml", UMLResource.Factory.INSTANCE);

		extensionFactoryMap.put("xml", new XMLResourceFactoryImpl());

		extensionFactoryMap.put("xmi", UMLResource.Factory.INSTANCE);
		extensionFactoryMap.put("xmi", new XMIResourceFactoryImpl());

		extensionFactoryMap.put("genmodel", new XMIResourceFactoryImpl());

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerPackages(ResourceSet resourceSet) {
		Map packageRegistry = resourceSet.getPackageRegistry();
		packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		packageRegistry.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
		packageRegistry.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(UML212UMLResource.UML_METAMODEL_NS_URI, UMLPackage.eINSTANCE);
	
		packageRegistry.put("http://www.eclipse.org/uml2/1.0.0/UML", UMLPackage.eINSTANCE);
		packageRegistry.put("http://www.eclipse.org/uml2/2.0.0/UML", UMLPackage.eINSTANCE);
		packageRegistry.put("http://www.eclipse.org/uml2/3.0.0/UML", UMLPackage.eINSTANCE);
		packageRegistry.put("http://www.eclipse.org/uml2/4.0.0/UML", UMLPackage.eINSTANCE);
		packageRegistry.put("http://www.eclipse.org/uml2/5.0.0/UML", UMLPackage.eINSTANCE);
	
	}

	@SuppressWarnings("unused")
	private void registerPathmaps(URI uri) {
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
				uri.appendSegment("libraries").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
				uri.appendSegment("metamodels").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
				uri.appendSegment("profiles").appendSegment(""));
	}

	public static ResourceSet getResourceSet() {
		if (RESOURCE_SET == null) {
			RESOURCE_SET = new ResourceSetImpl();
			registerPackages(getResourceSet());
			registerResourceFactories();
		}
		return RESOURCE_SET;
	}
}
