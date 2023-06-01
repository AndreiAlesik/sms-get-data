package org.example;

public class Files {
    private String fileName;
    private Long fileSize;

    private String collectionName;
    public Files(String fileName, Long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public Files(String fileName, Long fileSize, String collectionName) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionName = collectionName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
