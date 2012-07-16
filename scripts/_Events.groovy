/**
 * Copies the generated persistence.xml file from the projectWorkDir to the target directory
 * so that it may be found on the classpath.
 * This is a workaround that is not supposed to be needed, and thus it may be removed when 
 * the hibernate-jpa-provider is able to place the generated file onto the classpath directly. 
 **/
eventCompileEnd = {  kind ->
    // This check is here to ensure that I only generate the banner-codenarc.jar when compiled within the
    // banner-codenarc project.
    if (new File( "BannerCodenarcGrailsPlugin.groovy" ).exists()) {
        def ant = new AntBuilder()

        ant.jar(destfile: 'lib/banner-codenarc.jar') {
            fileset(dir: 'target/classes', erroronmissingdir:false) {
                include( name: 'net/hedtech/banner/codenarc/rule/**' )
            }
            fileset(dir: '.') {
                include(name: 'rulesets/**')
                include(name: 'codenarc-messages.properties')
            }
        }
	}
}
