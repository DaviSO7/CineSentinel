# CineSentinel 🎬

Sistema de linha de comando (CLI) em Kotlin que simula a validação de compra de ingressos de cinema, verificando dados do cliente e do filme antes de confirmar a venda.

## Overview

O CineSentinel pede as informações do cliente (nome e idade) e do filme desejado (nome, classificação etária e tipo de sessão), e então valida se a compra pode ser realizada com base em três **business rules**.

1. **Movie availability** — atualmente só há um filme cadastrado no sistema: *Homem Aranha*.
2. **Session type** — a sessão precisa ser do tipo `3D`.
3. **Minimum age** — a idade do cliente precisa ser igual ou superior a 16 anos.

Se qualquer uma dessas condições não for atendida (ou se algum dado estiver `null`/vazio), a venda é negada (**invalid purchase**) e o motivo específico é exibido ao usuário. Caso contrário, a compra é confirmada (**valid purchase**) e o sistema pergunta se o usuário deseja realizar outra compra.

## Tech Stack

- **Kotlin** (JVM) — plugin version `2.3.0`
- **Gradle** (Kotlin DSL) com **toolchain** JDK 25
- **JUnit** (via `kotlin("test")`) configurado para testes, embora ainda não existam **unit tests** implementados no projeto

## Project Structure

```
CineSentinel/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── src/main/kotlin/
    ├── CineSentinel.kt              # Entry point (main function)
    ├── Init.kt                      # Orquestra o fluxo: pede dados, valida, exibe resultado
    ├── PedirInfo.kt                 # Coleta os dados do cliente e do filme via terminal (input handler)
    ├── InfoCliente.kt               # Data class Cliente (nome, idade)
    ├── InfoFilme.kt                 # Data class Filme (nome, classificação, tipo)
    ├── ValidacaoClienteFilme.kt     # Validation logic da compra
    ├── VerificacaoValidacao.kt      # Trata o resultado da validação (approved/denied)
    ├── MotivosInvalidez.kt          # Mensagens de motivo de recusa (rejection reasons)
    ├── OpcoesValidoNaoValido.kt     # Enum: VALIDO / NAO_VALIDO (valid/invalid)
    └── OpcoesMotivosInvalidez.kt    # Enum: motivos de invalidez (não utilizado atualmente na lógica)
```

## Application Flow

```
main() → Init.iniciar()
  1. Exibe welcome message
  2. PedirInfo.pedirInfoCliente() → coleta nome e idade (customer input)
  3. PedirInfo.pedirInfoFilme() → coleta filme, classificação e tipo (movie input)
  4. ValidacaoClienteFilme.validarClienteFilme() → runs validation against business rules
  5. VerificacaoValidacao.verificandoValidacao():
       - Se VALID → confirma a compra e pergunta se quer comprar de novo
       - Se INVALID → exibe o motivo e pergunta se quer tentar novamente (retry)
```

Em ambos os casos (compra aprovada ou negada), o usuário pode optar por reiniciar o fluxo digitando `S` (yes), ou encerrar digitando `N` (no).

## How to Run

**Prerequisites**: JDK 25 instalado (ou use o Gradle **wrapper**, que resolve o toolchain automaticamente).

```bash
# Clone the repository
git clone https://github.com/DaviSO7/CineSentinel.git
cd CineSentinel

# Run the project
./gradlew run
```

> **Note**: não há uma **run task** explícita no `build.gradle.kts` atual (apenas o plugin `kotlin("jvm")`). Para executar diretamente, você pode compilar e rodar a classe `CineSentinelKt` pela sua IDE (IntelliJ), ou adicionar o **`application` plugin** ao Gradle para habilitar `./gradlew run`.

## Business Rules (Summary)

| Rule | Condition to approve |
|---|---|
| Movie | Nome do filme deve ser `"Homem Aranha"` |
| Session type | Tipo do filme deve ser `"3D"` |
| Minimum age | Idade do cliente ≥ 16 anos |
| Required fields | Nome, idade, nome do filme, classificação e tipo não podem ser `null`/vazios |

## Improvement Points

- **Hardcoded movie catalog**: hoje só existe um filme válido (`"Homem Aranha"`) fixo no código. Poderia evoluir para um **movie catalog** (lista de filmes).
- **Unused enum**: `OpcoesMotivosInvalidez` existe mas não é usado no **validation flow** — os motivos são chamados diretamente via `MotivosInvalidez()`.
- **Static age check**: a checagem de idade está fixa em 16 anos, mas o campo `classificacaoFilme` (12, 16 ou 18) não é comparado dinamicamente com a idade do cliente.
- **Exception handling**: `readln().toInt()` pode lançar uma **exception** se o usuário digitar um valor não numérico.
- **Automated testing**: a dependência do JUnit já está configurada, mas ainda não há **unit tests** escritos.
- Adicionar o **`application` plugin** do Gradle para permitir `./gradlew run` diretamente.

## License

Not specified in the repository.