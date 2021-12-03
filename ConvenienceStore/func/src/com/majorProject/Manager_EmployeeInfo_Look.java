package com.majorProject;

import java.util.HashMap;

public class Manager_EmployeeInfo_Look {
    HashMap<String, Employee>employeeMap;
    public Manager_EmployeeInfo_Look(HashMap<String, Employee>employeeMap){
        this.employeeMap = employeeMap;
    }
    public void employeeLook(){
        for (String key: this.employeeMap.keySet()){
            if(!employeeMap.get(key).toString().split(",")[8].equals("F")){
                System.out.println(employeeMap.get(key));
            }
        }
    }
    public boolean oneEmployeeLook(String id){
        if(this.employeeMap.containsKey(id)){
            System.out.println(employeeMap.get(id));
            return true;
        }
        else{
            System.out.println("요청하신 직원의 정보가 없습니다!");
            return false;
        }
    }
}
