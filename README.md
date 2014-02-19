AssemAssist
===========

An assembly line assistment system

import project into eclipse
	get Egit
	import via egit
	edit .project file replace buildSpec/natures with this 
		<buildSpec>
        		<buildCommand>
        		<name>org.eclipse.jdt.core.javabuilder</name>
        		<arguments>
        		</arguments>
        		</buildCommand>
		</buildSpec>
		<natures>
    		<nature>org.eclipse.jdt.core.javanature</nature>
		</natures>
	
