/*******************************************************************************
Copyright 2009-2012 Ellucian Company L.P. and its affiliates.
*******************************************************************************/
includeTargets << grailsScript("Init")

target(main: "This builds a jar of the test directory of CodeNarc and pulls it into lib") {
    def codeNarcSrcDir = "$args/src/test/groovy"
    def ant = new AntBuilder()
    ant.jar(baseDir:codeNarcSrcDir, destFile:"lib/codenarc-test.jar", update:true )
}

setDefaultTarget(main)
