#!/bin/bash
# Test runner script that handles path with apostrophe

cd "$(dirname "$0")"

# Build classpath
MAVEN_CLASSPATH=$(mvn dependency:build-classpath -DincludeScope=test -q | tail -1)
TARGET_CLASSES="target/classes"
TARGET_TEST_CLASSES="target/test-classes"
FULL_CLASSPATH="${MAVEN_CLASSPATH}:${TARGET_CLASSES}:${TARGET_TEST_CLASSES}"

# Compile first
echo "=== Compiling project ==="
mvn clean compile test-compile -q

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo ""
echo "=== Running Tests ==="
echo ""

# Run TestNG with the suite files
java -cp "${FULL_CLASSPATH}" \
     -Denv=qa \
     org.testng.TestNG \
     -testclass com.delhivery.TMS_WMS.testModules.TmsWmsLtlTest \
     -testclass com.delhivery.TMS_WMS.testModules.WmsOrderTest \
     -testclass com.delhivery.TMS_WMS.testModules.FTLFlow \
     -groups "LTL,WMS,FTL,Integration" \
     -verbose 2

echo ""
echo "=== Test Execution Complete ==="

