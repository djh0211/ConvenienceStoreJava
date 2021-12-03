package com.majorProject;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import static com.majorProject.CommandMenuMain.printdivide;

public class Main {
    public static final String ID = "yoon";
    public static final int PW = 1111;
    public static boolean check = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommandMenuMain command = new CommandMenuMain();
        InventoryMain inventory = new InventoryMain();
        int commandNum;
        HashMap<String, Employee>employeeHashMap = null;
        Employee em = null;
        String id = "";
        while (true) {
            printdivide();
            System.out.println("<재고 관리 및 직원 관리 프로그램>");
            System.out.println("1. 재고 관리 2. 직원 관리 3. 프로그램 종료");
            System.out.print("입력: ");

            try {
                commandNum = Integer.parseInt(br.readLine());
                printdivide();
                if (commandNum == 1) {

                    if(check == false) {
                        employeeHashMap = command.displayCommandMode(2);
                        System.out.print("고유 아이디 입력 : ");
                        id = br.readLine();
                        em = employeeHashMap.get(id);
                        if (em != null) {
                            check = true;
                        }
                        else {
                            System.out.println("해당 아이디를 가진 직원이 없습니다. 확인 후 다시 입력해주세요.");
                        }
                    }
                    if(check) {
                        System.out.println(employeeHashMap.get(id).getData().getName() + "님 출근하셨습니다!");
                        inventory.Inventorymain();
                    }
                    else{
                        System.out.println("프로그램 초기 화면으로 돌아갑니다");
                    }
                } else if (commandNum == 2) {
                    if (login())
                        command.displayCommandMode(1);
                    else {
                        System.out.println("아이디와 비밀번호가 잘못되었습니다. 다시 입력해주세요. ");
                    }
                } else if (commandNum == 3) {
                    System.out.println("수고하셨습니다 : )");
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                } else {
                    throw new InputTypeErrorException("정상적인 입력이 아닙니다. 다시 입력하십시오");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e) { // 숫자가 아닌 타입
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
}
