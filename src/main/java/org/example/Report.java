package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report {


    public static Long calcTotalSize(List<Files> inputFiles) {

        Long totalSize = 0L;
        for (Files temp : inputFiles)
            totalSize += temp.getFileSize();
        return totalSize;
    }

//    public static List<String> collectionReturning(List<Files> inputFiles) {
//        List<Files> filteredList = new ArrayList<>();
//        for (Files temp : inputFiles) {
//            if (!temp.getCollectionName().isEmpty())
//                filteredList.add(temp);
//        }
//        HashMap<String, Integer> report = new HashMap<>();
//        for (int i = 0; i < filteredList.size(); i++) {
//            String temp = (filteredList.get(i)).getCollectionName();
//            Integer tempSize=0;
//
//        }
//    }
}
