package alocacaocontigua;

import java.util.ArrayList;

public class Directory {
    private ArrayList<File> arquivos;
    private ArrayList<Integer> inicios;

    public Directory(){
        this.arquivos = new ArrayList<File>();
        this.inicios = new ArrayList<Integer>();
    }

    public int getInicio(int index) {
        return this.inicios.get(index);
    }

    public void addInicio(int inicio) {
        this.inicios.add(inicio);
    }

    public void addArquivo(File file) {
        if(file != null) 
            this.arquivos.add(file);
    }

    public int posicaoArquivo(File file) {
        return arquivos.indexOf(file);
    }

    public int tamanhoArquivo(File file) {
        int index = posicaoArquivo(file);
        if(index != -1) {
            return arquivos.get(index).getTamanho();
        }
        return -1;
    }

    public void removeArquivo(File file) {
        if(file !=  null)
            this.arquivos.remove(file);
    }
}