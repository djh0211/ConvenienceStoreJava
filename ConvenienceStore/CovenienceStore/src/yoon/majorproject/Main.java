package yoon.majorproject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static final String ID = "yoon";
    public static final int PW = 1111;
    public static boolean check = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



    public static void main(String[] args) {
//        month2Days();

        while (true) {
            System.out.println("1. 재고관리 2. 관리자 모드 3. 퇴근하기");
            System.out.print("입력 : ");

            int choice = 0;
            try {
                choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        // 프로그램 실행 후 첫 실행인 경우에는 본인의 고유 아이디를 입력하여 재고 관리 기능
                        //을 수행할 수 있는 창으로 이동
                        if(check == false) {
                            Manager manager = Manager.getInstance();
                            System.out.print("고유 아이디 입력 : ");
                            String id = br.readLine();
                            Employee em = manager.employeeMap.get(id);
                            if (em != null) {
                                check = true;
                            }
                        }
                        else {
                            //직원관리 플로우 시작
                        }

                        break;

                    case 2:
                        System.out.println();
                        if (login()) {
                            System.out.println();
                            new Manager(1);
                        }
                        else {
                            System.out.println("아이디와 비밀번호가 잘못되었습니다. 다시 입력해주세요. ");
                        }
                        break;
                    case 3:
                        System.out.println("수고하셨습니다 : )");
                        br.close();
                        System.exit(0);
                    default:
                        throw new InputTypeErrorException("정상적인 입력이 아닙니다. 다시 입력하십시오");
                }
            } catch (IOException e) { // ioexception
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) { // 숫자가 아닌 타입
                System.out.println("정상적인 입력이 아닙니다. 다시 입력하십시오");
            } catch (InputTypeErrorException e) { // 1~3이외의 값
                System.out.println(e.getMessage());
            }
        }

    }

    private static boolean login() {
        System.out.println("<관리자 로그인>");
        String id = "";
        int pw = 0;
        try {
            System.out.print("관리자 ID : ");
            id = br.readLine();
            System.out.print("관리자 PW : ");
            pw = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (id.equals(ID) && pw == PW) {
            return true;
        }
        return false;
    }
    //    public static void month2Days() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(df.format(cal.getTime()));
//        cal.add(Calendar.MONTH,2);
//        System.out.println(df.format(cal.getTime()));
//    }

}
