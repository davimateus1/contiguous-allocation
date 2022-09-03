package alocacaocontigua;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class AlocacaoContigua {

    public static void main(String[] args) {
        byte[] disco = new byte[15];
        byte[] buffer = new byte[15];
        remapeaArrays(disco);
        remapeaArrays(buffer);
        MapOfBits mapBits;
        Directory directory = new Directory();
        Scanner scan = new Scanner(System.in);
        int escolha;
        String nome, conteudo;
        File file;

        do {
            menu();
            escolha = scan.nextInt();
            scan.nextLine();

            mapBits = new MapOfBits(disco);
            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do arquivo:");
                    nome = scan.nextLine();
                    file = new File(nome);
                    ler(file, buffer, directory, disco);
                    break;
                case 2:
                    System.out.println("Digite o nome do arquivo:");
                    nome = scan.nextLine();
                    System.out.println("Digite o que deseja escrever no arquivo:");
                    conteudo = scan.nextLine();
                    try {
                        file = new File(nome, convertToByte(conteudo));
                        escrever(file, mapBits, disco, directory, buffer);
                    } catch (UnsupportedEncodingException e) {
                        System.err.println("falha ao tentar converter o conteudo");
                    }
                    break;
                case 0:
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);

        scan.close();
    }

    public static void ler(File arquivo, byte[] buffer, Directory directory, byte[] disco) {
        int index = directory.posicaoArquivo(arquivo);
        limpaBuffer(buffer);
        if (index != -1) {
            int node = directory.getInicio(index);
            int tamanho = directory.tamanhoArquivo(arquivo);
            for (int i = node; i - node < tamanho; i++) {
                buffer[i - node] = disco[i];
            }
            imprimeBuffer(buffer);
            try {
                imprimeConteudo(buffer);
            } catch (UnsupportedEncodingException e) {
                System.err.println("Falha ao tentar exibir o conteudo em Texto");
            }
        } else {
            System.err.println("Arquivo não encontrado.");
        }
    }

    private static void carregaNoBuffer(byte[] buffer, File file) {
        if (file != null && buffer != null) {
            for (int i = 0; i < file.getTamanho(); i++) {
                buffer[i] = file.getConteudo()[i];
            }
        }
    }

    private static void limpaBuffer(byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = -1;
        }
    }

    public static void escrever(File file, MapOfBits mapBits, byte[] disco, Directory directory, byte[] buffer) {
        int node = mapBits.getEspacoLivre(file);
        if (node != -1) {
            System.out.println("Node a ser salvo: " + node);
            System.out.println(file.toString());
            carregaNoBuffer(buffer, file);
            for (int i = node; i - node < file.getTamanho(); i++) {
                disco[i] = buffer[i - node];
            }
            imprimeDisco(disco);
            limpaBuffer(buffer);
            mapBits.mapear(disco);
            directory.addArquivo(file);
            directory.addInicio(node);
        } else {
            System.err.println("Não há espaço suficiente no disco.");
        }
    }

    public static void menu() {
        System.out.println("1 - Ler arquivo");
        System.out.println("2 - Escrever no arquivo");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção:");
    }

    public static byte[] convertToByte(String conteudo) throws UnsupportedEncodingException {
        byte[] byteArray = conteudo.getBytes("UTF-8");
        return byteArray;
    }

    private static void imprimeBuffer(byte[] buffer) {
        String mensagem = "Buffer: ";
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != -1) {
                mensagem += buffer[i];
            }
        }
        System.out.println(mensagem);
    }

    private static void imprimeDisco(byte[] disco) {
        String mensagem = "Disco: ";
        for (int i = 0; i < disco.length; i++) {
            if (disco[i] != -1) {
                mensagem += disco[i];
            }
        }
        System.out.println(mensagem);
    }

    public static void imprimeConteudo(byte[] buffer) throws UnsupportedEncodingException {
        String texto = new String(buffer, "UTF-8");
        System.out.println("Conteudo: " + texto);
    }

    private static void remapeaArrays(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }
}
