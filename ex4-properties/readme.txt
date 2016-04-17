* build
gw -Dorg.gradle.debug=true

* project
gw properties child:properties | grep version
gw properties child:properties -PjunitVersion=1 | grep version