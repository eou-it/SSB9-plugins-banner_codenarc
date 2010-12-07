package com.sungardhe.banner.codenarc.rule

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule
/**
 * Created by IntelliJ IDEA.
 * User: rrullo
 * Date: Dec 3, 2010
 * Time: 10:13:23 AM
 * To change this template use File | Settings | File Templates.
 */
class ResourceCodeExistsTest extends AbstractRuleTestCase {

    void testSuccessScenario() {
        def source = "def foo = \"@@r1:myResourceCode@@"
        assertNoViolations( source )
    }


    void testFailedScenario() {
        // Mimic a resource code that was mispelled.  Notice 'resource' spelled 'resourse'.
        def source = "def foo = \"@@r1:myResourseCode@@"
        assertSingleViolation( source, 0, null, "The resourceCode 'myResourseCode' was not found in the resource files." )
    }


    protected Rule createRule() {
        return new ResourceCodeExists()
    }
    
}
