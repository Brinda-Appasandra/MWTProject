From amazoncorretto:8
EXPOSE 8080
ADD target/MWT-1.jar MWT-1.jar
ENTRYPOINT [ "java","-jar","MWT-1.jar" ]