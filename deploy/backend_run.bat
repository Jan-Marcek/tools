rem switch to java 7 
set JAVA_HOME=c:\Program Files\Java\jdk1.7.0_45
set PATH=%JAVA_HOME%\bin;%PATH%

set JPDA_ADDRESS=8000
set JAVA_OPTS=-DconfigFileLocation=c:\Users\janci\.odcs\frontend.properties -agentlib:jdwp=transport=dt_socket,address=%JPDA_ADDRESS%,server=y,suspend=n
set JAR=e:\eea\comsode\UnifiedViews\Core\backend\target\backend-1.1.0-SNAPSHOT.jar
java %JAVA_OPTS% -jar %JAR%