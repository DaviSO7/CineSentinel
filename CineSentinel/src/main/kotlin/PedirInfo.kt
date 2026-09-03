class PedirInfo {
    fun pedirInfoCliente() : Cliente{
        print("Insira seu nome: ")
        val nome = readln()
        print("Insira sua idade: ")
        val idade = readln().toInt()
        return Cliente(nome, idade)
    }
    fun pedirInfoFilme() : Filme{
        println("Insira o nome do filme: ")
        val nomeFilme = readln()
        println("Insira a classificação etária do filme (12, 16, 18): ")
        val classificacaoEtariaFilme = readln().toInt()
        println("Insira o tipo do filme (2D ou 3D): ")
        val tipoFilme = readln()
        return Filme(nomeFilme, classificacaoEtariaFilme, tipoFilme)
    }
}