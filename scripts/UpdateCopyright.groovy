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

    def list = []
    list.add ("2010 SunGard Higher Education.  All Rights Reserved.\n\n CONFIDENTIAL BUSINESS INFORMATION\n\n THIS PROGRAM IS PROPRIETARY INFORMATION OF SUNGARD HIGHER EDUCATION\n AND IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF,\n NOR USED FOR ANY PURPOSE OTHER THAN THAT WHICH IT IS SPECIFICALLY PROVIDED\n WITHOUT THE WRITTEN PERMISSION OF THE SAID COMPANY")
    list.add ( "© 2010 SunGard Higher Education.  All Rights Reserved.\n\n CONFIDENTIAL BUSINESS INFORMATION\n\n THIS PROGRAM IS PROPRIETARY INFORMATION OF SUNGARD HIGHER EDUCATION\n AND IS NOT TO BE COPIED, REPRODUCED, LENT, OR DISPOSED OF,\n NOR USED FOR ANY PURPOSE OTHER THAN THAT WHICH IT IS SPECIFICALLY PROVIDED\n WITHOUT THE WRITTEN PERMISSION OF THE SAID COMPANY")
    list.add ( "Copyright 2009-2012 SunGard Higher Education. All Rights Reserved.\r\n This copyrighted software contains confidential and proprietary information of \r\n SunGard Higher Education and its subsidiaries. Any use of this software is limited \r\n solely to SunGard Higher Education licensees, and is further subject to the terms \r\n and conditions of one or more written license agreements between SunGard Higher \r\n Education and the licensee in question. SunGard is either a registered trademark or\r\n trademark of SunGard Data Systems in the U.S.A. and/or other regions and/or countries.\r\n Banner and Luminis are either registered trademarks or trademarks of SunGard Higher \r\n Education in the U.S.A. and/or other regions and/or countries.")
    list.add ( "Copyright 2011-2012 Ellucian.")
    list.add ( "Copyright 2009-2012 SunGard Higher Education. All Rights Reserved.\r\n This copyrighted software contains confidential and proprietary information of\r\n SunGard Higher Education and its subsidiaries. Any use of this software is limited\r\n solely to SunGard Higher Education licensees, and is further subject to the terms\r\n and conditions of one or more written license agreements between SunGard Higher\r\n Education and the licensee in question. SunGard is either a registered trademark or\r\n trademark of SunGard Data Systems in the U.S.A. and/or other regions and/or countries.\r\n Banner and Luminis are either registered trademarks or trademarks of SunGard Higher\r\n Education in the U.S.A. and/or other regions and/or countries.")

    def replaceStr = "Copyright 2009-2012 Ellucian Company L.P. and its affiliates. "

    println "Processing directory $basedir"
    targetDirectory.eachFileRecurse() { file ->
        if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) )&& ( file.name != "UpdateCopyright.groovy") && !(file.path =~ "/plugins")) {
            println "Processing $file.name "

            list.eachWithIndex() { key, i -> if (file.text.find(key))
                                                file.write file.text.replaceAll( key, replaceStr)
            }
        }
    }

    targetDirectory.eachFileRecurse() { file ->
        if ((file.name.endsWith( ".groovy" ) ||file.name.endsWith( ".java" ) )&& ( file.name != "UpdateCopyright.groovy") && !(file.path =~ "/plugins")) {
         if (file.text.find ("Copyright 2009-2012 SunGard Higher Education") )
            println "Update Manually $file.name "
        }
    }
}

setDefaultTarget "main"