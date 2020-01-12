mvn clean install -DskipTests
cd fashion-bestseller
mvn  docker:build 
cd ../hot-deals
mvn docker:build
cd ../toys-bestseller
mvn docker:build
cd ../gateway
mvn docker:build
cd ..
