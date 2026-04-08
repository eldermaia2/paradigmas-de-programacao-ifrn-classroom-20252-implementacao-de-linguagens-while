# ATIVIDADE – VERSIONAMENTO SEMÂNTICO E LTS

---

## Pergunta

Identifique se o versionamento semântico e o LTS estão disponíveis em dois dos componentes de software (bibliotecas, estruturas, SO) do seu software. Traga evidências (link) para as suas respostas.

---

## Resposta

### 1. ANTLR

O ANTLR é uma ferramenta utilizada para geração de parsers e está presente no projeto analisado.

O ANTLR utiliza versionamento no formato **MAJOR.MINOR.PATCH**, como pode ser observado nas versões 4.13.1 e 4.13.2, o que caracteriza o uso de versionamento semântico (SemVer).

**Evidência:**  
https://github.com/antlr/antlr4/releases  

Em relação ao suporte de longo prazo (LTS), o ANTLR não possui uma política oficial. O projeto segue um modelo de lançamentos contínuos, sem indicação de versões com suporte estendido.

---

### 2. Java

O Java é a linguagem utilizada na implementação do projeto.

O Java adota um modelo de versionamento estruturado e organizado, com evolução controlada entre versões, semelhante aos princípios do versionamento semântico.

**Evidência:**  
https://www.oracle.com/java/technologies/javase/versioning-naming.html  

Além disso, o Java possui versões com suporte de longo prazo (LTS), que recebem atualizações e correções por vários anos.

**Evidência:**  
https://www.oracle.com/java/technologies/java-se-support-roadmap.html  

Exemplos de versões LTS incluem:
- Java 8  
- Java 11  
- Java 17  
- Java 21  

---

## Conclusão

- O ANTLR utiliza versionamento semântico, mas não possui suporte LTS.  
- O Java utiliza versionamento estruturado e possui suporte LTS oficial.  

Dessa forma, observa-se que nem todos os componentes do software analisado oferecem suporte de longo prazo, embora ambos adotem práticas organizadas de versionamento.