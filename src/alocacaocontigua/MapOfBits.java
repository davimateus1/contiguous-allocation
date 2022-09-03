package alocacaocontigua;

import java.util.ArrayList;

public final class MapOfBits {

    private final ArrayList<Integer> blocosLivres;
    private final byte[] blocos;

    public MapOfBits(byte[] disco) {
        blocosLivres = new ArrayList<>();
        blocos = new byte[disco.length];
        this.remapearBloco(blocos);
        this.mapear(disco);
    }

    public void mapear(byte[] blocos) {
        for (int i = 0; i < blocos.length; i++) {
            if (blocos[i] == -1) {
                blocosLivres.add(i);
            }
        }
    }

    public ArrayList<Integer> getBlocosLivres() {
        return blocosLivres;
    }

    private void remapearBloco(byte[] blocos) {
        for (int i = 0; i < blocos.length; i++) {
            blocos[i] = -1;
        }
    }

    public int getEspacoLivre(File arquivo) {
        int tamanhoArquivo = arquivo.getTamanho();
        int contSeguidos = 0;
        if (!blocosLivres.isEmpty()) {
            int tamanhoLivre = blocosLivres.size();
            for (int i = 1; i < tamanhoLivre; i++) {
                if (blocosLivres.get(i) == (blocosLivres.get(i - 1) + 1)) {
                    contSeguidos++;
                    if (contSeguidos >= tamanhoArquivo) {
                        return (blocosLivres.get(i) - contSeguidos + 1);
                    }
                } else {
                    contSeguidos = 0;
                }
            }
        }
        return -1;
    }
}
