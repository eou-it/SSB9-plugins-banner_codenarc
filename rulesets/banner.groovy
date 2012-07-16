import net.hedtech.banner.codenarc.rule.ResourceCodeExists

ruleset {

    description 'Ruleset for Banner specific rules'


    ruleset( 'rulesets/basic.xml' )
    ruleset( 'rulesets/exceptions.xml' )
    ruleset( 'rulesets/imports.xml' )
    ruleset( 'rulesets/grails.xml' )
    ruleset( 'rulesets/unused.xml' )
    ruleset( 'rulesets/logging.xml')

    rule( ResourceCodeExists ) {
        priority = 1
    }
}
