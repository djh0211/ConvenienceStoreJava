package com.majorProject;

import java.util.Scanner;

class Inp {
    public Inp(){}
    public static String inputId(){
        Scanner sc = new Scanner(System.in);
        String id = null;
        int flag = 0;
        while(true){
            id = sc.next();
            if(!isCorrectId(id)){
                System.out.print("retry!! : ");
                flag++;
            }
            else{
                break;
            }
        }
        return id;
    }
    public static boolean isCorrectId(String id){
        if(id.length() == 10){
            return isIdForm(id);
        }
        else{
            return false;
        }
    }
    public static boolean isIdForm(String id){

        for(int i=0; i<id.length(); i++){
            char c = id.toCharArray()[i];
            if('0'<= c && c<='9' || 'a' <= c && c <= 'z' || ('A' <= c && c <= 'Z')) {
                ;
            } else{
                return false;
            }
        }
        return true;
    }
    public int inpNum(){
        Scanner sc = new Scanner(System.in);
        String inpNumber;
        int flag = 0;
        do{
            inpNumber = sc.next();
            if((inpNumber.length() != 1 || !('0' <= inpNumber.toCharArray()[0] && inpNumber.toCharArray()[0] <= '9'))){
                if(flag > 0){
                    return 100;
                }
                System.out.println("종료하시려면 \"100\"을 입력해주세요");
                System.out.print("다시 입력해주세요! : ");
                flag++;
            }
        }while(inpNumber.length() != 1 || !('0' <= inpNumber.toCharArray()[0] && inpNumber.toCharArray()[0] <= '9'));


        return Integer.parseInt(inpNumber);
    }
}
