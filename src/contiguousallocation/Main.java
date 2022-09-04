package contiguousallocation;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import bitsmap.BitsMap;
import directory.Directory;
import file.File;
import file.ReadingAndWriting;

public class Main {

    public static void main(String[] args) {
        byte[] disc = new byte[200];
        byte[] buffer = new byte[200];
        
        BitsMap bitsMap;
        Directory directory = new Directory();
        Scanner in = new Scanner(System.in);
        int op;
        String fileName, fileContent;
        File file;
        ReadingAndWriting rw = new ReadingAndWriting();
      //remapeia os espaços nos arrays setando os espaços como -1 (livres)
        rw.array_remap(disc);
        rw.array_remap(buffer);

        do {
        	System.out.println("1 - Write a file");
            System.out.println("2 - Read a file");
            System.out.println("3 - Delete especific file");
            System.out.println("4 - List all files");
            System.out.println("0 - Exit");
            System.out.print("-> ");
            op = in.nextInt();
            in.nextLine();

            bitsMap = new BitsMap(disc);
            switch (op) {
                case 1:
                	System.out.println("Insert file name: ");
                    fileName = in.nextLine();
                    System.out.println("Insert file content");
                    fileContent = in.nextLine();
                    try {
                        file = new File(fileName, convert_to_byte(fileContent));
                        rw.write(file, bitsMap, disc, directory, buffer);
                    } catch (UnsupportedEncodingException e) {
                        System.err.println("Failed to encode file!");
                    }
                    break;                   
                case 2:
                    
                    System.out.print("Insert file name: ");
                    fileName = in.nextLine();
                    file = new File(fileName);
                    rw.read(file, buffer, directory, disc);
                    break;
                case 3:
                	System.out.println("Insert file name: ");
                	fileName = in.nextLine();
                	file = new File(fileName);
                	if(!directory.getFiles().contains(file)) {
                		System.err.println("File not found.");
                	}else {
                		directory.removeFile(file);
                		System.out.println( file + " deleted.");
                	}
                	break;
                case 4:
                	if(directory.getFiles().size() == 0) {
                		System.err.println("Empty list");
                	}else {
                		System.out.println("ALL FILES IN BLOCKS");
                    	System.out.println(directory.getFiles());
                	}  
                	break;
                case 0:
                    System.out.println("Goodbye :)");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (op != 0);

        in.close();
    }
  
    
    
    public static byte[] convert_to_byte(String content) throws UnsupportedEncodingException {
        byte[] byteArray = content.getBytes("UTF-8");
        return byteArray;
    }
    
}
