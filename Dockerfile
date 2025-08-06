# Use the official Maven 3.8.4 image with OpenJDK 8 on a slim Linux distribution as the base image.
FROM maven:3.8.4-openjdk-8-slim

# Set the working directory inside the container to /express-api-automation.
WORKDIR /express-api-automation

# Update the package list and then install Python 3.9.
RUN apt-get update && apt-get install -y python3.9 && \

# Install the 'net-tools' package, which includes networking tools like 'ifconfig'.
    apt-get install -y net-tools && \

# Install Python package manager (pip) for Python 3 and install the 'boto3' library.
    apt-get install -y python3-pip && pip3 install boto3

# Copy the project's Maven project object model (pom.xml) to the working directory.
COPY pom.xml /express-api-automation

# Download and cache the project's dependencies using Maven.
RUN mvn dependency:go-offline

# Copy all the files from the project directory to the working directory in the container.
COPY . /express-api-automation

# Give execute permissions to the 'entrypoint.sh' script.
RUN chmod +x /express-api-automation/entrypoint.sh

# Define the command to run when the container starts. In this case, it runs the 'entrypoint.sh' script.
CMD ["/express-api-automation/entrypoint.sh"]
