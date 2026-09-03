class VerificacaoValidacao {
    var perguntas = ValidacaoClienteFilme()
    fun verificandoValidacao(validoOuNao : OpcoesValidoNaoValido) {
        if (validoOuNao == OpcoesValidoNaoValido.VALIDO){
            println("Compra realizada com sucesso!")
            println("Detalhes da compra:")
            println("Filme: Homem Aranha")
            println("Sessão: 3D")
            println("Aproveite o filme!")
            Init().barra()
            perguntas.perguntarNovaTentativa()
        }
        else if (validoOuNao == OpcoesValidoNaoValido.NAO_VALIDO){
            perguntas.tentarComprarNovamente()
        }
    }
}