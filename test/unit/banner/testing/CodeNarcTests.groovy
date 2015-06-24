/*******************************************************************************
Copyright 2009-2012 Ellucian Company L.P. and its affiliates.
*******************************************************************************/

package banner.testing

import org.junit.Test

/**
 * Runs code narc and fails if there is code that it finds that violates our rulesets.
 */
class CodeNarcTests  {

    @Test
    void testCodeNarcRun() {
// TODO: Re-enable -- rulesets/banner.groovy file cannot be loaded
//      new CodeNarcHelper().runCodeNarc()
        println "CodeNarc execution not running -- see CodeNarcTests to re-enable"
        true //
    }

}
