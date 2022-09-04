package directory;

import java.util.ArrayList;

import file.File;

public class Directory {
    private ArrayList<File> files;
    private ArrayList<Integer> filesStart;

    public Directory(){
        this.files = new ArrayList<File>();
        this.filesStart = new ArrayList<Integer>();
    }
    
    public ArrayList<File> getFiles(){
    	return files;
    }

    public int getFilesStart(int index) {
        return this.filesStart.get(index);
    }

    public void addFileStart(int inicio) {
        this.filesStart.add(inicio);
    }

    public void addFile(File file) {
        if(file != null) 
            this.files.add(file);
    }

    public int file_position(File file) {
        return files.indexOf(file);
    }

    public int fileLength(File file) {
        int index = file_position(file);
        if(index != -1) {
            return files.get(index).getFileLength();
        }
        return -1;
    }

    public void removeFile(File file) {
        if(file !=  null)
            this.files.remove(file);
    }
}