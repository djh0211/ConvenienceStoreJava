package com.majorProject;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Manager_EmployeeInfo_Delete{
    HashMap<String, Employee>employeeMap;
    String path;
    String id;
    public Manager_EmployeeInfo_Delete(HashMap<String, Employee>employeeMap, String path, String id){
        this.employeeMap = employeeMap;
        this.path = path;
        this.id = id;
    }
    public void employeeDelete(){
        try {
            File file = new File(this.path);
            Scanner sc = new Scanner(file);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String result = "";
            String[] idStr = this.employeeMap.get(this.id).toString().split(",");
            EmployeeInfoData data = new EmployeeInfoData(idStr[1], idStr[2], idStr[3], idStr[4], idStr[5]);
            /*
                temp[0] => ID, temp[1] => 이름, temp[2] => 생년월일 temp[3] => 성별
                temp[4] => 핸드폰번호, temp[5] => 계약월수 temp[6] => 계약남은일수
                temp[7] => 총근무시간 temp[8] => 현재근무중여부
            */

            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                data.setCurrentWorking("F");
                data.setUid(this.id);
                if (temp.contains(this.employeeMap.get(this.id).toString())) {
                    result += temp.replaceAll(this.employeeMap.get(this.id).toString(), data.toString()) + "\n";
                } else {
                    result += temp + "\n";
                }
            }
            new FileOutputStream(this.path).close();
            bw.write(result);
            bw.flush();
            System.out.println("정상적으로 삭제 되었습니다.");
            System.out.println();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
