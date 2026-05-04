#!/bin/bash

# Gera parser ANTLR
java -jar ./lib/antlr-4.13.1-complete.jar -package plp.enquanto.parser ./src/plp/enquanto/parser/Enquanto.g4

# Cria pasta bin
mkdir -p bin

# Compila com detecção de deprecated
javac -Xlint:deprecation -Werror \
-cp ./lib/antlr-runtime-4.13.1.jar \
-d bin \
$(find ./src -name "*.java")

# 🔥 REMOVE jar antigo (ESSENCIAL)
rm -f while.jar

# 🔥 CRIA jar corretamente (ESSENCIAL)
jar --create --file while.jar \
--main-class plp.enquanto.Principal \
-C bin .

# 🔥 ADICIONA dependência ANTLR dentro do jar
jar --update --file while.jar -C lib antlr-runtime-4.13.1.jar