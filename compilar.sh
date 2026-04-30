#!/bin/bash

# Gera o parser com ANTLR
java -jar ./lib/antlr-4.13.1-complete.jar -package plp.enquanto.parser ./src/plp/enquanto/parser/Enquanto.g4

# Cria pasta bin (evita erro se não existir)
mkdir -p bin

# Compila com detecção de código deprecated (ESSENCIAL)
javac -Xlint:deprecation -Werror \
-cp ./lib/antlr-runtime-4.13.1.jar \
-d bin \
$(find ./src -name "*.java")

# Copia dependência para o jar final
cp ./lib/antlr-runtime-4.13.1.jar while.jar

# Empacota aplicação
jar --create --file while.jar \
--main-class plp.enquanto.Principal \
-C bin .