mvn clean compile jib:build -f ./auth/pom.xml
mvn clean compile jib:build -f ./account/pom.xml
mvn clean compile jib:build -f ./inventory/pom.xml
mvn clean compile jib:build -f ./order/pom.xml
mvn clean compile jib:build -f ./payment/pom.xml
