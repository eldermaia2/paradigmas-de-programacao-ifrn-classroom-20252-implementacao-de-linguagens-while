# ATIVIDADE – VERSIONAMENTO SEMÂNTICO E LTS

---

## Pergunta

Identifique se o versionamento semântico e o LTS estão disponíveis em dois dos componentes de software (bibliotecas, estruturas, SO) do seu software. Traga evidências (link) para as suas respostas.

Não serão considerados os softwares ilustrados em sala de aula (ubuntu, java, python, node, angular).

---

## Resposta

### 1. ANTLR

O ANTLR é uma ferramenta utilizada para geração de parsers e está presente no projeto analisado.

O ANTLR utiliza versionamento no formato **MAJOR.MINOR.PATCH**, como pode ser observado nas versões 4.13.1 e 4.13.2, o que caracteriza o uso de versionamento semântico (SemVer).

**Evidência:**  
https://github.com/antlr/antlr4/releases  

Em relação ao suporte de longo prazo (LTS), o ANTLR não possui uma política oficial. O projeto segue um modelo de lançamentos contínuos, sem indicação de versões com suporte estendido.

---

### 2. .NET

O .NET é uma plataforma de desenvolvimento mantida pela Microsoft, amplamente utilizada para construção de aplicações.

O .NET utiliza um modelo de versionamento estruturado semelhante ao versionamento semântico, com versões como 6.0, 7.0 e 8.0.

**Evidência:**  
https://learn.microsoft.com/en-us/dotnet/core/versions/  

Além disso, o .NET possui versões com suporte de longo prazo (LTS), que recebem atualizações por vários anos.

**Evidência:**  
https://learn.microsoft.com/en-us/dotnet/core/versions/#lts-versions  

Exemplos de versões LTS incluem:
- .NET 6  
- .NET 8  

---

## Conclusão

- O ANTLR utiliza versionamento semântico, mas não possui suporte LTS.  
- O .NET utiliza versionamento estruturado e possui suporte LTS oficial.  

Dessa forma, observa-se que nem todos os componentes do software analisado oferecem suporte de longo prazo, embora ambos adotem práticas organizadas de versionamento.