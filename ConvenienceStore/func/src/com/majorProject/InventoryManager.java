package com.majorProject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManager {
    private File inventoryDB;
    private static BufferedReader fr;
    private ArrayList<Item> items;

    //싱글톤
    private static InventoryManager instance;

    public static InventoryManager getInstance() throws IOException {
        if (instance == null) {
            return new InventoryManager(new File("InventoryDB.txt"));
        }
        return instance;
    }

    public InventoryManager(File inventoryDB) throws IOException {
        this.inventoryDB = inventoryDB;
        this.items = new ArrayList<>();

        //파일 없으면 생성
        if (!this.inventoryDB.exists()) {
            this.inventoryDB.createNewFile();
        }

        fr = new BufferedReader(new FileReader(this.inventoryDB));
        String line;
        while ((line = fr.readLine()) != null) {
            String[] record = line.split(",");
            addItem(record[1],
                    record[0],
                    Integer.parseInt(record[2]),
                    Integer.parseInt(record[3]),
                    false);
        }
    }

    public int getLastIndex() {
        if (items.size() == 0) {
            return 0;
        }
        return Integer.parseInt(items.get(items.size() - 1).getINDEX());
    }

    public void addItem(String index, String itemName, int itemPrice, int inventoryCount, boolean isRemoved) throws IOException {
        Item item = new Item(index, itemName, itemPrice, inventoryCount, isRemoved);
        items.add(item);
        updateDB(this.inventoryDB);
    }

    public void removeItem(String index) throws IOException {
        for (Item item : items) {
            if (item.getINDEX().equals(index)) {
                item.setRemoved(true);
            }
        }
        updateDB(this.inventoryDB);
        System.out.println("삭제되었습니다.");
    }

//    public void showAllItems() {
//        for (Item item : items) {
//            System.out.println(item);
//        }
//    }

    public void searchItem(String keyword) {
        for (Item item : items) {
            if (item.isRemoved()) {
                continue;
            }
            if (item.getItemName().contains(keyword) || item.getINDEX().equals(keyword)) {
                System.out.println(item);
            }
        }
    }

    public HashMap<Integer, String> searchItemWithCount(String keyword) {
        HashMap<Integer, String> searchIndexMap = new HashMap<>();
        int count = 1;
        for (Item item : items) {
            if (item.isRemoved()) {
                continue;
            }
            if (item.getItemName().contains(keyword) || item.getINDEX().equals(keyword)) {
                System.out.println(count +". "+ item);
                searchIndexMap.put(count++, item.getINDEX());
            }
        }
        return searchIndexMap;
    }

    public void searchItemByIndex(String searchIndex) {
        for (Item item : items) {
            if (item.getINDEX().equals(searchIndex)) {
                System.out.println(item);
            }
        }
    }

//    public void updateItemPrice(String index, int itemPrice) throws IOException {
//        for (Item item : items) {
//            if (item.getINDEX().equals(index)) {
//                item.setItemPrice(itemPrice);
//            }
//        }
//        updateDB(this.inventoryDB);
//    }

    public void updateItemInventoryCount(String index, int inventoryCount) throws IOException {
        for (Item item : items) {
            if (item.getINDEX().equals(index)) {
                item.setInventoryCount(inventoryCount);
            }
        }
        updateDB(this.inventoryDB);
    }

    public void updateDB(File file) throws IOException {
        BufferedWriter fw = null;
        try {
            fw = new BufferedWriter(new FileWriter(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fw != null) {
            for (Item item : items) {
                if (item.isRemoved()) {
                    continue;
                }
                fw.write(item.toString());
                fw.newLine();
                fw.flush();
//                fw.close();
            }
        }
    }
}
