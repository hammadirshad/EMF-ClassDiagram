package org.eclipse.emf.example.loader;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.emf.example.util.ConvertProperties;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UML212UMLResource;
import org.eclipse.uml2.uml.resource.UML22UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModelLoader {

    private static ResourceSet RESOURCE_SET = null;

    public ModelLoader() {
        RESOURCE_SET = new ResourceSetImpl();
        registerPackages(getResourceSet());
        registerResourceFactories();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void registerResourceFactories() {
        Map extensionFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);

        extensionFactoryMap.put(Ecore2XMLResource.FILE_EXTENSION, Ecore2XMLResource.Factory.INSTANCE);
        extensionFactoryMap.put("ecore", new EcoreResourceFactoryImpl());

        extensionFactoryMap.put(UML22UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);
        extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);

        extensionFactoryMap.put("xml", UMLResource.Factory.INSTANCE);

        extensionFactoryMap.put("xml", new XMLResourceFactoryImpl());

        extensionFactoryMap.put("xmi", UMLResource.Factory.INSTANCE);
        extensionFactoryMap.put("xmi", new XMIResourceFactoryImpl());

        extensionFactoryMap.put("genmodel", new XMIResourceFactoryImpl());

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
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

    public static ResourceSet getResourceSet() {
        if (RESOURCE_SET == null) {
            RESOURCE_SET = new ResourceSetImpl();
            registerPackages(getResourceSet());
            registerResourceFactories();
        }
        return RESOURCE_SET;
    }

    private Resource registerModel(File file) throws IOException {

        final char PKG_SEPARATOR = '.';
        final char DIR_SEPARATOR = '/';

        String scannedPackage = "org.eclipse.uml2.uml";
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL url = getClass().getClassLoader().getResource(scannedPath);
        if (url != null) {
            String pathToJar;
            if (url.getPath().contains("file:")) {
                pathToJar = url.getPath().substring(5, url.getPath().indexOf(".jar") + 4);
            } else {
                pathToJar = url.getPath().substring(0, url.getPath().indexOf(".jar") + 4);
            }

            URIConverter.URI_MAP.put(URI.createURI("platform:/plugin/org.eclipse.uml2.uml/"),
                    URI.createURI("jar:file:" + pathToJar + "!/"));

        }


        Resource resource;

        Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");

        resource = getResourceSet().createResource(URI.createFileURI(file.getCanonicalPath()));
        resource.load(options);
        return resource;
    }

    public Package loadModel(File file) throws IOException {
        if (file.getName().endsWith("uml")) {
            return loadUMLModel(file);
        } else if (file.getName().equals("ecore")) {
            return loadEcoreModel(file);
        }
        return null;
    }

    private Package loadUMLModel(File file) throws IOException {

        Resource resource = registerModel(file);

        Package _package;

//        for (EObject ojEObject : resource.getContents()) {
//            System.out.println(ojEObject.getClass());
//        }

        _package = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);

        if (_package == null) {
            _package = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
        }

        if (_package == null) {


            EPackage _package2 = (EPackage) EcoreUtil.getObjectByType(resource.getContents(),
                    EcorePackage.Literals.EPACKAGE);
            if (_package2 != null) {
                Collection<Package> ecorePackages =
                        UMLUtil.convertFromEcore(_package2, ConvertProperties.optionsToProcess());

                if (ecorePackages != null && !ecorePackages.isEmpty()) {
                    _package = (Package) ecorePackages.toArray()[0];
                }


            }
        }
        return _package;

    }

    private Package loadEcoreModel(File file) throws IOException {

        Resource resource = registerModel(file);

        EPackage _package;

        _package = (EPackage) EcoreUtil.getObjectByType(resource.getContents(),
                EcorePackage.Literals.EPACKAGE);

        Package _UMLPackage = null;

        if (_package != null) {
            Collection<Package> ecorePackages =
                    UMLUtil.convertFromEcore(_package, ConvertProperties.optionsToProcess());
            if (ecorePackages != null && !ecorePackages.isEmpty()) {
                _UMLPackage = (Package) ecorePackages.toArray()[0];
            }
        }

        return _UMLPackage;

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
}
