package file;

import java.util.Arrays;
import java.util.Objects;

public class File {
    private final String fileName;
    private final byte[] fileContent;

    public File(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }
    public File(String fileName){
        this.fileName = fileName;
        fileContent = new byte[200];
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getFileLength() {
        return this.fileContent.length;
    }

    public byte[] getContent() {
        return this.fileContent;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() == this.getClass()) {
            File other  = (File) o;
            return other.getFileName().equals(this.getFileName());
        } else {
            return false;
        }
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.fileName);
        hash = 71 * hash + Arrays.hashCode(this.fileContent);
        return hash;
    }

    @Override
    public String toString() {
        return "File name: " + getFileName() + " |  Encoded content: " + fileContent;
    }
}

