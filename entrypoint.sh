#!/bin/sh
mvn clean test -Denv=${ENV} -DsuiteXmlFile=${TYPE}.xml
cat test-output/SparkReport/Index.html


if [ -f test-output/SparkReport/Index.html ]
then
    echo "sending email"
    python3 attachment.py
fi

tail -f /dev/null