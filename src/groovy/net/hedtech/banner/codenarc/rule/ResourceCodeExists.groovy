/*******************************************************************************
Copyright 2009-2012 Ellucian Company L.P. and its affiliates.
*******************************************************************************/
package net.hedtech.banner.codenarc.rule

import org.codenarc.rule.AbstractRule
import org.codenarc.source.SourceCode
import org.codenarc.rule.Violation
import org.apache.commons.io.FileUtils

/**
 * Validates that a resource code in code exists in a resource bundle.
 */
class ResourceCodeExists extends AbstractRule {
    String name = "ResourceCodeExists"

    int priority = 2


    void applyTo(SourceCode sourceCode, List violations) {

        def matcher = sourceCode.text =~ /@@r1:(\w*)@@/

        while (matcher.find()) {
            def index = matcher.start()
            def resourceCode = matcher.group().substring( 5, matcher.group().length() - 2 )

            // Check to see if the resourceCode exists in the i18n directory.  Search for ".$resourceCode=" in all *.properties.
            def baseDir = System.properties[ "base.dir" ]
            if (!(new File( "$baseDir/grails-app/i18n/messages.properties" ).text.contains( ".$resourceCode=" ))) {
                def lineNumber = sourceCode.getLineNumberForCharacterIndex( index )

                violations << new Violation( rule:this,
                    lineNumber:lineNumber,
                    sourceLine:matcher.group(),
                    message:"The resourceCode '$resourceCode' was not found in the resource files.")
            }
        }
    }
}
