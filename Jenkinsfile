node {
  jdk = tool name: 'JDK18'
  gradle = tool name: 'GRADLE'

  echo `printenv`
  echo `pwd`
  env.JAVA_HOME = "${jdk}"

  echo "jdk installation path is: ${jdk}"

  // next 2 are equivalents
  sh "${jdk}/bin/java -version"

  // note that simple quote strings are not evaluated by Groovy
  // substitution is done by shell script using environment
  sh '$JAVA_HOME/bin/java -version'
  sh "${gradle}/bin/gradle --version"

}