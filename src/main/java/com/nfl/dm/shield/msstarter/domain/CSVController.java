package com.nfl.dm.shield.msstarter.domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class CSVController {


    public static void main(String[] args) throws IOException {
        CSVController.DCLCounts();
    }

    public static void DCLCounts() throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        List<DCLStorage> beans = null;
        try {
            beans = new CsvToBeanBuilder(new FileReader("CSV_Search_Results_NEW/NFL_Films/YoY/2022/NFL_Films_2022_MASTER.csv"))
                    .withSkipLines(1)
                    .withType(DCLStorage.class)
                    .build()
                    .parse();
            //withSkipLines(1) specifies the number of lines to skip at the beginning of the CSV file.
            //withType(ExampleData.class) specifies the type of object to create for each row of the CSV file. In this case, we are using our ExampleData class.
            //build() creates the CsvToBean object.
            //parse() reads the CSV file and returns a list of ExampleData objects.
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        Map<String, HashMap<LocalDate, List<DCLStorage>>> groupings = new HashMap<>();
        Map<String, HashMap<LocalDate, List<DCLStorage>>> assetTypeMap = new HashMap<>();
        Map<String, HashMap<LocalDate, List<DCLStorage>>> feedTypeMap = new HashMap<>();
        Map<String, HashMap<LocalDate, List<DCLStorage>>> showNameMap = new HashMap<>();
        Map<String, HashMap<LocalDate, List<DCLStorage>>> eventTypeMap = new HashMap<>();
        Map<String, HashMap<LocalDate, List<DCLStorage>>> studioShowElementMap = new HashMap<>();
        for (DCLStorage dclStorage : beans) {
            LocalDate d = dclStorage.getDateCreated().toLocalDate();
            d = d.with(TemporalAdjusters.firstDayOfMonth());
            String contentSource = dclStorage.getContentSource();
            String dCLAssetType = dclStorage.getdCLAssetType();
            String eventType = dclStorage.getEventType();
            String feedType = dclStorage.getFeedType();
            String showName = dclStorage.getShowName();
            String studioShowElement = dclStorage.getStudioShowElement();
            String csPlusAt;
            String csPlusEt;
            String csPlusAtPlusFt;
            String csPlusAtPlusSn;
            String csPlusSse;
            if (contentSource == null || contentSource.isEmpty() || contentSource.trim().equalsIgnoreCase("")) {
                contentSource = "NO CONTENT SOURCE";
                dclStorage.setContentSource("NO CONTENT SOURCE");
            }
            if (dCLAssetType == null || dCLAssetType.isEmpty() || dCLAssetType.trim().equalsIgnoreCase("")) {
                dCLAssetType = "NO ASSET TYPE";
                dclStorage.setdCLAssetType("NO ASSET TYPE");
            }
            if (feedType == null || feedType.isEmpty() || feedType.trim().equalsIgnoreCase("")) {
                feedType = "NO FEED TYPE";
                dclStorage.setFeedType("NO FEED TYPE");
            }
            if (showName == null || showName.isEmpty() || showName.trim().equalsIgnoreCase("")) {
                showName = "NO SHOW NAME";
                dclStorage.setShowName("NO SHOW NAME");
            }
            if (eventType == null || eventType.isEmpty() || eventType.trim().equalsIgnoreCase("")) {
                eventType = "NO EVENT TYPE";
                dclStorage.setEventType("NO EVENT TYPE");
            }
            if (studioShowElement == null || studioShowElement.isEmpty() || studioShowElement.trim().equalsIgnoreCase("")) {
                studioShowElement = "NO STUDIO SHOW ELEMENT";
                dclStorage.setStudioShowElement("NO STUDIO SHOW ELEMENT");
            }
            //Groupings
            if (!groupings.containsKey(contentSource)) {
                groupings.put(contentSource, new HashMap<>());
            }
            if (!groupings.get(contentSource).containsKey(d)) {
                groupings.get(contentSource).put(d, new LinkedList<>());
            }
            groupings.get(contentSource).get(d).add(dclStorage);

            // assetTypeMap
            dclStorage.setCsPlusAt();
            csPlusAt = dclStorage.getCsPlusAt();

            if (!assetTypeMap.containsKey(csPlusAt)) {
                assetTypeMap.put(csPlusAt, new HashMap<>());
            }
            if (!assetTypeMap.get(csPlusAt).containsKey(d)) {
                assetTypeMap.get(csPlusAt).put(d, new LinkedList<>());
            }
            assetTypeMap.get(csPlusAt).get(d).add(dclStorage);

            // feedTypeMap
            dclStorage.setCsPlusAtPlusFt();
            csPlusAtPlusFt = dclStorage.getCsPlusAtPlusFt();

            if (!csPlusAtPlusFt.contains("Exclude")) {
                if (!feedTypeMap.containsKey(csPlusAtPlusFt)) {
                    feedTypeMap.put(csPlusAtPlusFt, new HashMap<>());
                }
                if (!feedTypeMap.get(csPlusAtPlusFt).containsKey(d)) {
                    feedTypeMap.get(csPlusAtPlusFt).put(d, new LinkedList<>());
                }
                feedTypeMap.get(csPlusAtPlusFt).get(d).add(dclStorage);
            }
            // showNameMap
            dclStorage.setCsPlusAtPlusSn();
            csPlusAtPlusSn = dclStorage.getCsPlusAtPlusSn();

            if (!csPlusAtPlusSn.contains("Exclude")) {
                if (!showNameMap.containsKey(csPlusAtPlusSn)) {
                    showNameMap.put(csPlusAtPlusSn, new HashMap<>());
                }
                if (!showNameMap.get(csPlusAtPlusSn).containsKey(d)) {
                    showNameMap.get(csPlusAtPlusSn).put(d, new LinkedList<>());
                }
                showNameMap.get(csPlusAtPlusSn).get(d).add(dclStorage);
            }
            // eventTypeMap
            dclStorage.setCsPlusEt();
            csPlusEt = dclStorage.getCsPlusEt();

            if (!eventTypeMap.containsKey(csPlusEt)) {
                eventTypeMap.put(csPlusEt, new HashMap<>());
            }
            if (!eventTypeMap.get(csPlusEt).containsKey(d)) {
                eventTypeMap.get(csPlusEt).put(d, new LinkedList<>());
            }
            eventTypeMap.get(csPlusEt).get(d).add(dclStorage);

            //studioShowElementMap
            dclStorage.setCsPlusSse();
            csPlusSse = dclStorage.getCsPlusSse();

            if (!studioShowElementMap.containsKey(csPlusSse)) {
                studioShowElementMap.put(csPlusSse, new HashMap<>());
            }
            if (!studioShowElementMap.get(csPlusSse).containsKey(d)) {
                studioShowElementMap.get(csPlusSse).put(d, new LinkedList<>());
            }
            studioShowElementMap.get(csPlusSse).get(d).add(dclStorage);
        }

        String filename = "testfile";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filepath = "CSV_Search_Results_NEW/NFL_Films/YoY/2022/" + filename + "_" + date + ".csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(filepath))) {
            writer.writeNext(new String[]{"Content Source", "Asset Type", "Feed Type", "Show Name", "Event Type", "Studio Show Element", "Date Range MoM", "Size (TB)", "Total Size (TB)"});
            for (String cs : groupings.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(groupings.get(cs));
                double runningTotal = 0.0;
                String myKey = cs;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length + 8];
                    for (int i = 0; i <= keyParts.length - 1; ++i) {
                        record[i] = keyParts[i];
                    }
                    double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                    double gb = b / Double.valueOf(1024 * 1024 * 1024);
                    double tb = gb / 1024.0;
                    runningTotal += tb;
                    record[keyParts.length + 5] = ld.toString();
                    record[keyParts.length + 6] = df.format(tb);
                    record[keyParts.length + 7] = df.format(runningTotal);

                    writer.writeNext(record);

                    String println = "";
                    for (int i = 0; i <= record.length - 1; ++i) {
                        println = println + record[i] + ", ";
                    }
                    println = println.substring(0, println.length() - 2);
                    System.out.print(println);
                    System.out.println();

//                            String[] record = new String[]{cs, "", "", "", ld.toString(), df.format(tb), df.format(runningTotal)};
//                            writer.writeNext(record);
//
//                            System.out.print(cs + ", " + "" + ", " + "" + ", " + ", " + ld + ", " + tm.get(ld).size() + ", " + df.format(tb) + df.format(runningTotal));
//                            System.out.println();

                }
            }
            writer.writeNext(new String[]{});

            for (String at : assetTypeMap.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(assetTypeMap.get(at));
                double runningTotal = 0.0;
//                String[] test = at.split("-");
//                String cs2 = test[0];
//                String at2 = test[1];
                String myKey = at;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length + 7];
                    for (int i = 0; i <= keyParts.length - 1; ++i) {
                        record[i] = keyParts[i];
                    }
                    double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                    double gb = b / Double.valueOf(1024 * 1024 * 1024);
                    double tb = gb / 1024.0;
                    runningTotal += tb;
//                    String[] record = new String[]{cs2, at2, "", ld.toString(), df.format(tb), df.format(runningTotal)};
//                    writer.writeNext(record);
//                    System.out.print(cs2 + ", " + at2 + ", " + "" + ", " + ld + ", " + tm.get(ld).size() + ", " + df.format(tb) + df.format(runningTotal));
//                    System.out.println();
                    record[keyParts.length + 4] = ld.toString();
                    record[keyParts.length + 5] = df.format(tb);
                    record[keyParts.length + 6] = df.format(runningTotal);

                    writer.writeNext(record);
                    String println = "";
                    for (int i = 0; i <= record.length - 1; ++i) {
                        println = println + record[i] + ", ";
                    }
                    println = println.substring(0, println.length() - 2);
                    System.out.print(println);
                    System.out.println();
                }
            }
            writer.writeNext(new String[]{});

            for (String ft : feedTypeMap.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(feedTypeMap.get(ft));
                double runningTotal = 0.0;

                String myKey = ft;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length +6];
                    for (int i = 0; i <= keyParts.length - 1; ++i) {
                        record[i] = keyParts[i];
                    }
                    double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                    double gb = b / Double.valueOf(1024 * 1024 * 1024);
                    double tb = gb / 1024.0;
                    runningTotal += tb;

                    record[keyParts.length +3] = ld.toString();
                    record[keyParts.length +4] = df.format(tb);
                    record[keyParts.length +5] = df.format(runningTotal);

                    writer.writeNext(record);
                    String println = "";
                    for (int i = 0; i <= record.length - 1; ++i) {
                        println = println + record[i] + ", ";
                    }
                    println = println.substring(0, println.length() - 2);
                    System.out.print(println);
                    System.out.println();

                }
            }
            writer.writeNext(new String[]{});

            for (String sn : showNameMap.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(showNameMap.get(sn));
                double runningTotal = 0.0;

                String myKey = sn;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length + 6];
                    for (int i = 0; i <= keyParts.length-1; ++i) {
//                        record[i] = keyParts[i];
                        if (i == 2) {
                            record[i] = " ";
                            record[i+1] = keyParts[i];
                        } else {
                            record[i] = keyParts[i];
                        }
                    }
                        double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                        double gb = b / Double.valueOf(1024 * 1024 * 1024);
                        double tb = gb / 1024.0;
                        runningTotal += tb;

                        record[keyParts.length + 3] = ld.toString();
                        record[keyParts.length + 4] = df.format(tb);
                        record[keyParts.length + 5] = df.format(runningTotal);

                        writer.writeNext(record);
                        String println = "";
                        for (int i = 0; i <= record.length -1; ++i) {
////                            println = println + record[i] + ", ";
//                            if (i == 2) {
//                                println = println + " " + ", ";
//                                println = println + record[i+1];
//                            } else {
                                println = println + record[i] + ", ";
//                            }
                        }
                            println = println.substring(0, println.length() - 2);
                            System.out.print(println);
                            System.out.println();

                        }
                    }
            writer.writeNext(new String[]{});

            for (String et : eventTypeMap.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(eventTypeMap.get(et));
                double runningTotal = 0.0;
                String myKey = et;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length + 7];
                    for (int i = 0; i <= keyParts.length-1; ++i) {
                        if (i == 1) {
                            record[i] = " ";
                            record[i+3] = keyParts[i];
                        } else {
                            record[i] = keyParts[i];
                        }
                    }
                    double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                    double gb = b / Double.valueOf(1024 * 1024 * 1024);
                    double tb = gb / 1024.0;
                    runningTotal += tb;

                    record[keyParts.length + 4] = ld.toString();
                    record[keyParts.length + 5] = df.format(tb);
                    record[keyParts.length + 6] = df.format(runningTotal);

                    writer.writeNext(record);
                    String println = "";
                    for (int i = 0; i <= record.length - 1; ++i) {
                        println = println + record[i] + ", ";
                    }
                    println = println.substring(0, println.length() - 2);
                    System.out.print(println);
                    System.out.println();
                }
            }

            writer.writeNext(new String[]{});

            for (String sse : studioShowElementMap.keySet()) {
                TreeMap<LocalDate, List<DCLStorage>> tm = new TreeMap<>();
                tm.putAll(studioShowElementMap.get(sse));
                double runningTotal = 0.0;

                String myKey = sse;
                String[] keyParts = myKey.split("-");
                for (LocalDate ld : tm.keySet()) {
                    String[] record = new String[keyParts.length + 7];

                    for (int i = 0; i <= keyParts.length-1; ++i) {

                        if (i == 1) {
                            record[i] = " ";
                            record[i+4] = keyParts[i];
                        } else {
                            record[i] = keyParts[i];
                        }
                    }
                    double b = tm.get(ld).stream().collect(Collectors.summingDouble(DCLStorage::getFileSize));
                    double gb = b / Double.valueOf(1024 * 1024 * 1024);
                    double tb = gb / 1024.0;
                    runningTotal += tb;

                    record[keyParts.length + 4] = ld.toString();
                    record[keyParts.length + 5] = df.format(tb);
                    record[keyParts.length + 6] = df.format(runningTotal);

                    writer.writeNext(record);
                    String println = "";
                    for (int i = 0; i <= record.length - 1; ++i) {
                        println = println + record[i] + ", ";
                    }
                    println = println.substring(0, println.length() - 2);
                    System.out.print(println);
                    System.out.println();
                }
            }
        }
    }
}
        // TODO




