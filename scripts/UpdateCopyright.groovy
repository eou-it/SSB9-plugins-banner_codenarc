/*******************************************************************************
Copyright 2009-2012 Ellucian Company L.P. and its affiliates.
*******************************************************************************/

import org.apache.commons.lang.StringUtils
import org.apache.commons.io.FileUtils
import java.util.regex.Pattern


/**
 * Grails target for updating copyright information
 *
 **/

includeTargets << grailsScript("_GrailsArgParsing")


target(main: "Converts copyright information.") {
    depends(parseArguments)

    def targetDirectory

    def replaceStr = "/* *****************************************************************************\nCopyright 2009-2012 Ellucian Company L.P. and its affiliates.\n*******************************************************************************/ "
    def replaceStrZul = "<!--*****************************************************************************\nCopyright 2009-2012 Ellucian Company L.P. and its affiliates.\n*********************************************************************************-->"
    def tempStr
    def osPluginDir


    println System.properties['os.name']
    if (!args) {

        if (System.properties['os.name'].toLowerCase().contains('windows'))
            osPluginDir = "\\plugins"
        else
            osPluginDir = "/plugins"

        targetDirectory = new File( "$basedir" )
        println "Processing directory $basedir"
        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" )||file.name.endsWith( ".gsp" ) )  && (file.name != "UpdateCopyright.groovy") && !(file.path.contains(osPluginDir))) {
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
            if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy") && !(file.path.contains(osPluginDir))) {
                if (file.text.indexOf("<!--***************************************") >= 0 )  {
                    tempStr = file.text.substring(file.text.indexOf("<!--***************************************"),file.text.indexOf("****************************************-->") + 43)
                    if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
                    }
            }
        }

        targetDirectory.eachFileRecurse() { file ->
            if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy") && !(file.path.contains(osPluginDir))) {
                if (file.text.indexOf("<!-- ***************************************") >= 0 )  {
                    tempStr = file.text.substring(file.text.indexOf("<!-- **************************************"),file.text.indexOf("*************************************** -->") + 43)
                    if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
                }
            }
        }


        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )
                && (file.name != "UpdateCopyright.groovy") && !(file.path.contains(osPluginDir))) {
            if (file.text.find ("SunGard Higher Education. All Rights Reserved") )
                println "Update Manually $file.name "
            }
        }


        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )
                && (file.name != "UpdateCopyright.groovy") && !(file.path.contains(osPluginDir))) {
            if (!file.text.find ("SunGard Higher Education. All Rights Reserved") && !file.text.find ("Copyright 2009-2012 Ellucian Company L.P. and its affiliates.") )
                println "No Copyrights in $file.name "
                }
            }
        }
    else
    {
        def folder
        if ( args.contains(".git") )
            osPluginDir = args
        else
            osPluginDir = args + ".git"

        if (System.properties['os.name'].toLowerCase().contains('windows'))
            osPluginDir = "\\plugins\\" + osPluginDir
        else
            osPluginDir = "/plugins/" + osPluginDir
        folder = "$basedir" + osPluginDir
        println "Argument is " + args
        println "Processing plugin $folder"
        targetDirectory = new File( "$folder" )

        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" )||file.name.endsWith( ".gsp" ) )) {
                if (file.text.indexOf("/******************************************") >= 0 )  {
                    tempStr = file.text.substring(file.text.indexOf("/******************************************"),file.text.indexOf("******************************************/") + 43)
                    if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                           file.write file.text.replace( tempStr, replaceStr)
                }
                if (file.text.indexOf("/** ***************************************") >= 0 && file.text.indexOf("**************************************** */") >0  )  {
                    tempStr = file.text.substring(file.text.indexOf("/** ***************************************"),file.text.indexOf("**************************************** */") + 43)
                    if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                           file.write file.text.replace( tempStr, replaceStr)
                    }
                }
        }

        targetDirectory.eachFileRecurse() { file ->
            if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy")) {
                if (file.text.indexOf("<!--***************************************") >= 0 )  {
                    tempStr = file.text.substring(file.text.indexOf("<!--***************************************"),file.text.indexOf("****************************************-->") + 43)
                     if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
                    }
            }
        }

        targetDirectory.eachFileRecurse() { file ->
            if (file.name.endsWith( ".zul" )   && (file.name != "UpdateCopyright.groovy") ) {
                if (file.text.indexOf("<!-- ***************************************") >= 0 )  {
                    tempStr = file.text.substring(file.text.indexOf("<!-- **************************************"),file.text.indexOf("*************************************** -->") + 43)
                    if ((tempStr.indexOf("SunGard Higher Education. All Rights Reserved") > 0) && (tempStr.length() < 2000))
                            file.write file.text.replace( tempStr, replaceStrZul)
                    }
                }
        }


        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )  ) {
                if (file.text.find ("SunGard Higher Education. All Rights Reserved") )
                    println "Update Manually $file.name "
            }
        }


        targetDirectory.eachFileRecurse() { file ->
            if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) ||file.name.endsWith( ".zul" ) ||file.name.endsWith( ".gsp" ) )) {
                if (!file.text.find ("SunGard Higher Education. All Rights Reserved") && !file.text.find ("Copyright 2009-2012 Ellucian Company L.P. and its affiliates.") )
                    println "No Copyrights in $file.name "
                }
            }
        }
}

setDefaultTarget "main"
