FROM openjdk:11

# Create project directories
RUN mkdir /usr/src/appjava
RUN mkdir /usr/src/projjava
RUN chmod -R 777 /usr/src

# Transfer project files
COPY . /usr/src/appjava

RUN rm /usr/src/appjava/Dockerfile

RUN javac -cp -d /usr/src/appjava/Eu.java

WORKDIR /usr/src/projjava
VOLUME  /usr/src/projjava

ENTRYPOINT ["java", "-cp", "/usr/src/appjava", "Eu"]
