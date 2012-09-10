/*******************************************************************************

Copyright 2009-2012 Ellucian Company L.P. and its affiliates.

*******************************************************************************/

import org.apache.commons.lang.StringUtils
import org.apache.commons.io.FileUtils
import java.util.regex.Pattern


/**
 * Grails target for converting all *.sql in ddl and subdirectories to a format that is supported in Linux.
 * Usage:  'grails convert-ddl'        converts all sql in ddl and its subdirectories to a linux format.
 **/

target(main: "Converts copyright information.") {

    // pass a directory or use the current directory
    def folder = "$basedir"
    def ddlDirectory = new File(folder)
    def targetDirectory = new File( "$basedir" )

    def replaceStr = "/*******************************************************************************\nCopyright 2009-2012 Ellucian Company L.P. and its affiliates.\n*******************************************************************************/ "
    def replaceStrZul = "<!--*****************************************************************************\nCopyright 2009-2012 Ellucian Company L.P. and its affiliates.\n*********************************************************************************-->"
    def tempStr
    def osPluginDir
    if (System.properties['os.name'].toLowerCase().contains('windows'))
        osPluginDir = "\\plugins"
    else
        osPluginDir = "/plugins"

   println "OS Plugin directory $osPluginDir"
   println "Processing directory $basedir"
    targetDirectory.eachFileRecurse() { file ->
        if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" )||file.name.endsWith( ".gsp" ) )  && (file.name != "UpdateCopyright.groovy") && !(file.path =~ osPluginDir)) {
            if (file.text.indexOf("/******************************************") >= 0 )  {
                tempStr = file.text.substring(file.text.indexOf("/******************************************"),file.text.indexOf("******************************************/") + 43)
                if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                           file.write file.text.replace( tempStr, replaceStr)
            }
            if (file.text.indexOf("/** ***************************************") >= 0 )  {
                tempStr = file.text.substring(file.text.indexOf("/** ***************************************"),file.text.indexOf("**************************************** */") + 43)
                if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                           file.write file.text.replace( tempStr, replaceStr)
            }
        }
    }

    targetDirectory.eachFileRecurse() { file ->
         if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy") && !(file.path =~ osPluginDir)) {
             if (file.text.indexOf("<!--***************************************") >= 0 )  {
                 tempStr = file.text.substring(file.text.indexOf("<!--***************************************"),file.text.indexOf("****************************************-->") + 43)
                 if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
             }
         }
     }

    targetDirectory.eachFileRecurse() { file ->
         if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy") && !(file.path =~ osPluginDir)) {
             if (file.text.indexOf("<!-- ***************************************") >= 0 )  {
                 tempStr = file.text.substring(file.text.indexOf("<!-- **************************************"),file.text.indexOf("*************************************** -->") + 43)
                 if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
             }
         }
     }


    targetDirectory.eachFileRecurse() { file ->
         if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )
                && (file.name != "UpdateCopyright.groovy") && !(file.path =~ osPluginDir)) {
         if (file.text.find ("SunGard Higher Education. All Rights Reserved") )
            println "Update Manually $file.name "
        }
    }


    targetDirectory.eachFileRecurse() { file ->
         if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )
                && (file.name != "UpdateCopyright.groovy") && !(file.path =~ osPluginDir)) {
         if (!file.text.find ("SunGard Higher Education. All Rights Reserved") && !file.text.find ("Copyright 2009-2012 Ellucian Company L.P. and its affiliates.") )
            println "No Copyrights in $file.name "
        }
    }
}

setDefaultTarget "main"