#!/bin/bash

# Limpa builds anteriores
rm -rf bin
rm -f while.jar

# Gera parser ANTLR
java -jar ./lib/antlr-4.13.1-complete.jar \
-package plp.enquanto.parser \
./src/plp/enquanto/parser/Enquanto.g4

# Cria pasta bin
mkdir -p bin

# Compila tudo
javac -Xlint:deprecation -Werror \
-cp "./lib/antlr-runtime-4.13.1.jar" \
-d bin \
$(find ./src -name "*.java")

# 🔥 DEBUG (IMPORTANTE)
echo "Conteúdo do bin:"
find bin

# Cria jar corretamente
jar --create --file while.jar \
--main-class plp.enquanto.Principal \
-C bin .

# 🔥 DEBUG (IMPORTANTE)
echo "Conteúdo do JAR:"
jar tf while.jar