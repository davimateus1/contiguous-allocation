package file;

import bitsmap.BitsMap;
import directory.Directory;

public class ReadingAndWriting {
	public void read(File file, byte[] buffer, Directory directory, byte[] disco) {
    	//pega o indice do arquivo especionado na lista de arquivos no diretório
        int index = directory.file_position(file);
        //limpa o buffer percorrendo ele e transformando todas as posições em -1
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = -1;
        }
        //se o indice for livre...
        if (index != -1) {
        	// referenciamos um nó ao início do arquivo especificado
            int node = directory.getFilesStart(index);
            int length = directory.fileLength(file);
            // percorre o diretório referenciando um ponteiro ao nó e preenchendo o buffer
            for (int i = node; i - node < length; i++) {
                buffer[i - node] = disco[i];
            }
            //print buffer
            String msg = "Buffer: ";
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] != -1) {
                    msg += buffer[i];
                }
            }
            System.out.println(msg);
            //print content
            System.out.println("Content: " + buffer);
        } else {
            System.err.println("File not found.");
        }
    }

    

    public void write(File file, BitsMap mapBits, byte[] disc, Directory directory, byte[] buffer) {
    	//referencia um nó ao espaço de um arquivo no mapa de bits
        int node = mapBits.getFreeSpace(file);
        //se ele estiver vazio...
        if (node != -1) {
            System.out.println("Node to be saved -> " + node);
            System.out.println(file.toString());
            if (file != null && buffer != null) {
            	//percorre o tamanho do arquivo e vai adicionando o conteudo ao buffer
                for (int i = 0; i < file.getFileLength(); i++) {
                    buffer[i] = file.getContent()[i];
                }
            }
            //faz a mesma coisa adicionando o conteudo agora armazenado no buffer dentro do disco
            for (int i = node; i - node < file.getFileLength(); i++) {
                disc[i] = buffer[i - node];
            }
          //print buffer
            String msg = "Buffer: ";
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] != -1) {
                    msg += buffer[i];
                }
            }
            //limpa o buffer
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = -1;
            }
            mapBits.map(disc);
            directory.addFile(file);
            directory.addFileStart(node);
        } else {
            System.err.println("Not enough space in disc.");
        }
    }


    public void array_remap(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }

}
