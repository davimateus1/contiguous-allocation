package bitsmap;

import java.util.ArrayList;

import file.File;

public final class BitsMap {

    private final ArrayList<Integer> freeBlocks;
    private final byte[] blocks;

    public BitsMap(byte[] disc) {
        freeBlocks = new ArrayList<>();
        blocks = new byte[disc.length];
        this.remapBlocks(blocks);
        this.map(disc);
    }

    public void map(byte[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == -1) {
                freeBlocks.add(i);
            }
        }
    }

    public ArrayList<Integer> getFreeBlocks() {
        return freeBlocks;
    }

    private void remapBlocks(byte[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = -1;
        }
    }

    public int getFreeSpace(File file) {
        int fileLength = file.getFileLength();
        int count = 0;
        // se os blocos nao estiverem livres...
        if (!freeBlocks.isEmpty()) {
            int freeSize = freeBlocks.size();
            //percorre a lista de blocos livres
            for (int i = 1; i < freeSize; i++) {
            	//se o bloco i for igual a ele mesmo (para conferir)
                if (freeBlocks.get(i) == (freeBlocks.get(i - 1) + 1)) {
                	//adiciona ao contador
                    count++;
                    //caso esse contador seja maior ou igual ao tamanho do arquivo, retornamos o espaço ocupado
                    if (count >= fileLength) {
                        return (freeBlocks.get(i) - count + 1);
                    }
                    //caso não seja livre, o contador recebe zero e por fim retornamos o espaço livre
                } else {
                    count = 0;
                }
            }
        }
        return -1;
    }
}
