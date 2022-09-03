package alocacaocontigua;

import java.util.Arrays;
import java.util.Objects;

public class File {
    private final String nome;
    private final byte[] conteudo;

    public File(String nome, byte[] conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }
    public File(String nome){
        this.nome = nome;
        conteudo = new byte[100];
    }

    public String getNome() {
        return this.nome;
    }

    public int getTamanho() {
        return this.conteudo.length;
    }

    public byte[] getConteudo() {
        return this.conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() == this.getClass()) {
            File other  = (File) o;
            return other.getNome().equals(this.getNome());
        } else {
            return false;
        }
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Arrays.hashCode(this.conteudo);
        return hash;
    }

    @Override
    public String toString() {
        return "Nome do arquivo criado: " + getNome() + "\nConteúdo gerado em Bytes: " + conteudo + '.';
    }
}

