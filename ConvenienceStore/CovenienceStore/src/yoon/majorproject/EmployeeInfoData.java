package yoon.majorproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class EmployeeInfoData {
    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    private String uid;
    private String name;
    private String birth;
    private String sex;
    private String phoneNum;
    private String contractMonths;
//    private String startDays;
    private String endDays;
    private int totalWorkHours;
    private String currentWorking;

    @Override
    public String toString() {
        //startDays추가
        String str = this.uid + "," + name + "," + birth + "," + sex + "," + phoneNum + "," + contractMonths +"," + endDays + "," + totalWorkHours + "," + currentWorking;

        return str;
    }


    public EmployeeInfoData(String name, String birth, String sex, String phoneNum, String contractMonths) {
        this.uid = getUID();
        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.contractMonths = contractMonths;
//        this.startDays = getCurrentDays();
//        month2Days(this.contractMonths);
        this.endDays = String.valueOf(Integer.parseInt(contractMonths) * 30);
        this.totalWorkHours = 0;
        this.currentWorking = "Y";
    }

//    private String getCurrentDays() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        return df.format(cal.getTime());
//    }

//    private String month2Days(String contractMonths) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(df.format(cal.getTime()));
//
//        cal.add(Calendar.MONTH,2);
//        System.out.println(df.format(cal.getTime()));
//        return null;
//    }


    public void setEndDays(String endDays) {
        int newEndDays = Integer.parseInt(this.endDays)+(Integer.parseInt(endDays)*30);
        this.endDays = String.valueOf(newEndDays);
    }

    public EmployeeInfoData(String uid, String name, String birth, String sex, String phoneNum, String contractMonths, String endDays, int totalWorkHours, String currentWorking) {
        //String startDays추가
        this.uid = uid;
        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.contractMonths = contractMonths;
//        this.startDays = startDays;
        this.endDays = endDays;
        this.totalWorkHours = totalWorkHours;
        this.currentWorking = currentWorking;
    }

    private String getUID() {
        String uid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        return uid;

    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getContractMonths() {
        return contractMonths;
    }

    public String getEndDays() {
        return endDays;
    }

    //    private String month2Days(String months) {
//
//
    public String getUid() {
        return uid;
    }

    public void setPersonalData(String name, String sex, String birth, String phone) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.phoneNum = phone;
    }

    public void setContractMonths(String contractMonths) {
        int newContractDays = Integer.parseInt(this.contractMonths) + Integer.parseInt(contractMonths);
        this.contractMonths = String.valueOf(newContractDays);
    }

}
