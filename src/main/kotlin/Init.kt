class Init {
    val pedirInfo = PedirInfo()
    var infoCliente : Cliente? = null
    var infoFilme : Filme? = null

    fun iniciar(){
        println("Cine Sentinel")
        barra()
        println("Seja bem vindo!")
        barra()
        infoCliente = pedirInfo.pedirInfoCliente()
        barra()
        infoFilme = pedirInfo.pedirInfoFilme()
        barra()
        val validacao = ValidacaoClienteFilme().validarClienteFilme(infoCliente, infoFilme)
        VerificacaoValidacao().verificandoValidacao(validacao)
    }

    fun barra(){
        println("-----------------------------------------------------------------------------------")
    }
}