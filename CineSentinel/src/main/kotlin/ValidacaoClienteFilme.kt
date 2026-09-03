class ValidacaoClienteFilme {

    fun validarClienteFilme(infoCliente: Cliente?, infoFilme: Filme?): OpcoesValidoNaoValido {
        val dadosIncompletos = infoCliente?.nome.isNullOrEmpty() ||
                infoCliente?.idade == null ||
                infoFilme?.nomeFilme.isNullOrEmpty() ||
                infoFilme?.tipoFilme.isNullOrEmpty() ||
                infoFilme?.classificacaoFilme == null

        if (dadosIncompletos) return OpcoesValidoNaoValido.NAO_VALIDO

        return when {
            infoFilme?.nomeFilme != "Homem Aranha" -> {
                MotivosInvalidez().motivoFilme()
                OpcoesValidoNaoValido.NAO_VALIDO
            }
            infoFilme.tipoFilme != "3D" -> {
                MotivosInvalidez().motivoSessao()
                OpcoesValidoNaoValido.NAO_VALIDO
            }
            (infoCliente?.idade ?: 0) < 16 -> {
                MotivosInvalidez().motivoMenorIdade()
                OpcoesValidoNaoValido.NAO_VALIDO
            }
            else -> OpcoesValidoNaoValido.VALIDO
        }
    }
    fun tentarComprarNovamente() {
        println("Você deseja tentar efetuar a compra novamente?")
        print("Digite (S/N): ")
        when (readln().trim().lowercase()) {
            "s" -> Init().iniciar()
            "n" -> return
        }
    }

    fun perguntarNovaTentativa() {
        println("Você deseja efetuar outra compra?")
        print("Digite (S/N): ")
        when (readln().trim().lowercase()) {
            "s" -> Init().iniciar()
            "n" -> return
        }
    }
}