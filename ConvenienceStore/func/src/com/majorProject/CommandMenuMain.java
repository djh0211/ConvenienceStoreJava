package com.majorProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class CommandMenuMain {

    public HashMap<String, Employee> displayCommandMode(int c) {

        Inp inp = new Inp();
        String path = "/Users/kimhyungkyu/IdeaProjects/func/EmployeeInfo.txt";  //필요에따라 수정바람!
        HashMap<String, Employee> employeeMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File file  =new File(path);
        Scanner sc = null;
        while (true) {
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            {
                while (sc.hasNextLine()) {
                    String[] temp = sc.nextLine().split(",");
                 /*
                //temp[6] => 시작날짜 추가
                temp[0] => ID, temp[1] => 이름, temp[2] => 생년월일 temp[3] => 성별
                temp[4] => 핸드폰번호, temp[5] => 계약월수, temp[6] => 남은 기간,
                temp[7] => 전체 근무 시간 temp[8] => 현재 근무 여부
                 */

                    if(!temp[0].equals("")) {
                        EmployeeInfoData eid = new EmployeeInfoData(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], Integer.parseInt(temp[7]), temp[8]);
                        employeeMap.put(temp[0], new Employee(eid));
                    }


                }
            }
            if(c == 2){
                return hashReturnFunc(employeeMap);
            }
            System.out.println("<관리자 모드>");
            System.out.println("1. 직원 추가\t 2. 직원 조회\t 3. 직원 정보 수정\t 4. 직원 삭제\t 5. 관리자 모드 종료");
            System.out.print("메뉴 선택: ");
            Manager_EditAndAdd manager_editAndAdd = new Manager_EditAndAdd(employeeMap, path);  //편집 추가 클래스 1,3번 메뉴에서 호출
            int commandNum = inp.inpNum();  //메뉴 선택 NUM //inp class내부에 있는 inpNum함수 자체적으로 구현
            if (commandNum == 1) {
                manager_editAndAdd.AddEmployee(path);   //추가 하는 매니저 클래스 내부에 추가함수 실행 텍스트 파일에 직원 추가
            } else if (commandNum == 2) {
                //look
                int lookCommandNum = lookEmployeeDisplay(employeeMap);  //look func실행 후 리턴 값은 2 내부적으로 출력함!
//                if(lookCommandNum == 1){
//                    printdivide();
//                    System.out.println("직원1 정보 출력, 삭제 요망!"); //1명의 직원 정보 출력 함수
//                    printdivide();
//                }
                if(lookCommandNum == 2){
                    printdivide();
                    System.out.println("관리자 모드로 돌아갑니다");    //작업 마치고 나와서 관리자 모드로 돌아감
                    printdivide();
                }
                else{
                    wrongInfoDisplay();
                }
            } else if (commandNum == 3) {
                manager_editAndAdd.ModifyEmployee();    //edit_add클래스 내부에 있는 편집함수 실행
            } else if (commandNum == 4) {
                //delete
                String deleteCommandStr = deleteDisplay(employeeMap, path); //해시맵은 관리자 모드 첫번째 라인에서 매번 업데이트 시켜주므로 항상 최신의 employee hash맵이 넘어간다
                //정보가 존재할 경우 반환 값은 id가 된다
                if (deleteCommandStr != null) { //return value가 null이 아닌 경우 id로 간주한다. id인지 아닌지는 Inp클래스에 id판별함수를 실행하므로
                    // 10자리 id값 그러한 직원이 존재하는지는 이미 판변 완료
                    while(true) {
                        System.out.println("정말 삭제하시겠습니까?");
                        System.out.println("1.YES 2. NO");
                        System.out.print("입력: ");
                        int deleteConfirmCommandNum = inp.inpNum(); //1이 입력되면 삭제함수 실행 직원의 정보를 T->F로 변경한다
                        if (deleteConfirmCommandNum == 1) {
                            //삭제함수 실행: doingDeleteInfo() --> Working: True --> False
                            //deleteCommandStr를 id로 삭제 진행
                            Manager_EmployeeInfo_Delete manager_employeeInfo_delete = new Manager_EmployeeInfo_Delete(employeeMap, path, deleteCommandStr);
                            manager_employeeInfo_delete.employeeDelete();
                            printdivide();
                            System.out.println("삭제 완료 되었습니다..");
                            printdivide();
                            break;
                        } else if (deleteConfirmCommandNum == 2) {
                            printdivide();
                            System.out.println("삭제가 진행되지 않았습니다");
                            System.out.println("관리자 모드로 돌아갑니다");
                            printdivide();
                            break;
                            //다시 되돌아감!, 관리자 모드로 되돌아 간다.
                        } else {
                            wrongInfoDisplay();
                        }
                    }
                }   //정보 존재
                else {
                    printdivide();
                    System.out.println("입력하신 정보가 존재하지 않습니다");
                    System.out.println("관리자 모드로 돌아갑니다");
                    printdivide();
                }   //정보 미 존재, 관리자 모드로 돌아감


            } else if (commandNum == 5) {
                //commandModeExit
                System.out.println("메인 메뉴로 돌아갑니다..");
                break;  //main으로 돌아감
            }
        }
        return null; //관리자 모드 종료
    }

    //직원정보: “고유 아이디”, “이름”, “생년월일”, ”성별”, “핸드폰 번호”, ” 계약기간”, “근무시간”, “근무여부”

    private static String deleteDisplay(HashMap<String, Employee>employeeMap, String path) {
        Manager_EmployeeInfo_Look employeeInfo_look = new Manager_EmployeeInfo_Look(employeeMap);
        String employeePersonalID;
        Inp inp = new Inp();
        int commandNum;
        while (true) {
            System.out.println("<직원 삭제>");
            System.out.println("1. 삭제할 직원의 ID 입력");
            System.out.println("2. 돌아가기");
            System.out.print("입력: ");
            commandNum = inp.inpNum();
            if (commandNum == 2) {
                return null;
            } else if (commandNum == 1) {
                System.out.print("삭제할 직원의 ID값을 입력: ");
                employeePersonalID = Inp.inputId();
                printdivide();
                if(employeePersonalID == null){
                    return null;
                }
//                func --> 정보 존재 함수 실행
//                int infoCorrect = infoCorrectFunc();
                boolean infoCorrect = employeeInfo_look.oneEmployeeLook(employeePersonalID);

//                정보 존재
                if (infoCorrect) {
                    return employeePersonalID;
                }
                //정보 미존재
                else {
                    printdivide();
                    System.out.println("일치하는 직원의 정보가 존재하지 않습니다");
                    printdivide();
                    return null;
                }
            } else {
                wrongInfoDisplay();
            }
        }
    }

    private static int lookEmployeeDisplay(HashMap<String, Employee>employeeMap) {
        Inp inp = new Inp();
        Manager_EmployeeInfo_Look manager_employeeInfo_look = new Manager_EmployeeInfo_Look(employeeMap);
        String employeePersonalId;
        int commandNum;

        while (true) {
            System.out.println("<직원 조회>");
            System.out.println("1. 직원 조회");
            System.out.println("2. 돌아가기");
            System.out.print("입력: ");
            commandNum = inp.inpNum();

            if (commandNum == 2) {
                return 2;   //관리자 모드로 돌아간다
            }
            else if (commandNum == 1) {
                System.out.println("1. 전체 직원 명단 조회 2. 직원 검색");
                System.out.print("메뉴 입력: ");
                int searchCommandNum = inp.inpNum();

                if (searchCommandNum == 2) {
                    System.out.print("조회할 직원의 ID값을 입력: ");
                    employeePersonalId = Inp.inputId();
                    printdivide();

                    boolean infoCorrect = manager_employeeInfo_look.oneEmployeeLook(employeePersonalId);

//                정보 존재
                    if (infoCorrect) {
                        return 2;
                    }
                    //정보 미존재
                    else return 2;

                }
                else if (searchCommandNum == 1) {
                    printdivide();
                    manager_employeeInfo_look.employeeLook();   //전체명단 출력
                    printdivide();
                    return 2;   //관리자 모드로 돌아간다
                }
                else {
                    wrongInfoDisplay();
                }
            }
            else {
                wrongInfoDisplay();
            }
        }
    }

    public static void wrongInfoDisplay(){
        printdivide();
        System.out.println("정상적인 입력이 아닙니다.");
        System.out.println("다시 입력하십시오.");
        printdivide();
    }
    public static void printdivide(){
        System.out.println("================");
    }

    public static HashMap<String, Employee>hashReturnFunc(HashMap<String, Employee>employeeHashMap){
        return employeeHashMap;
    }
}
