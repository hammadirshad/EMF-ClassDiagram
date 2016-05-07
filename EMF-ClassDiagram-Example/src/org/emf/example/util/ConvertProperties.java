package org.emf.example.util;

import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.uml2.uml.util.UMLUtil.UML2EcoreConverter;

import java.util.HashMap;
import java.util.Map;

public class ConvertProperties {
    public static Map<String, String> optionsToProcess() {
        final Map<String, String> options = new HashMap<String, String>();

    /*
     * options.put(UMLUtil.UML2EcoreConverter.OPTION__*,
     * UMLUtil.OPTION__DISCARD);
     * 
     * options.put(UMLUtil.UML2EcoreConverter.OPTION__*,
     * UMLUtil.OPTION__IGNORE);
     * 
     * options.put(UMLUtil.UML2EcoreConverter.OPTION__*,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UMLUtil.UML2EcoreConverter.OPTION__*,
     * UMLUtil.OPTION__REPORT);
     */

        options.put(UML2EcoreConverter.OPTION__UNION_PROPERTIES,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__REDEFINING_OPERATIONS,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__SUBSETTING_PROPERTIES,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURE_INHERITANCE,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURES,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATION_INHERITANCE,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATIONS,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__CAMEL_CASE_NAMES,
                UMLUtil.OPTION__PROCESS);

        options.put(UML2EcoreConverter.OPTION__SUPER_CLASS_ORDER,
                UMLUtil.OPTION__PROCESS);

    
  
    
    /*
     * 
     * options.put(UML2EcoreConverter.OPTION__ECORE_TAGGED_VALUES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__REDEFINING_OPERATIONS,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__REDEFINING_PROPERTIES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__SUBSETTING_PROPERTIES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__DERIVED_FEATURES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATIONS,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__DUPLICATE_OPERATION_INHERITANCE
     * , UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__DUPLICATE_FEATURE_INHERITANCE,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__SUPER_CLASS_ORDER,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__ANNOTATION_DETAILS,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__INVARIANT_CONSTRAINTS,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__OPERATION_BODIES,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__COMMENTS,
     * UMLUtil.OPTION__PROCESS);
     * 
     * options.put(UML2EcoreConverter.OPTION__CAMEL_CASE_NAMES,
     * UMLUtil.OPTION__IGNORE);
     */

        return options;
    }

}
