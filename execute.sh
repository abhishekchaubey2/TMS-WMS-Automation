#java -jar target/express-api-automation-1.0-SNAPSHOT.jar -config=dev/$1.config -f=$2
#mvn test -DSuiteFile=$1
#mvn test -DinpEnv=qa -DinpSuiteFile=sanity.xml
#mvn test -DSuiteFile=${suiteXmlFile}
#mvn test -Dtestng.dtd.file=${suiteXmlFile}
#mvn clean test -DSuiteFile=sanity.xml
mvn clean test -Denv=$1 -DSuiteFile=$2
