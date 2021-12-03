package yoon.majorproject;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class Manager {
    public HashMap<String, Employee> employeeMap = new HashMap<>();
    private static Scanner sc;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static File file  =new File("C:\\\\Users\\\\82106\\\\study\\\\hello-spring\\\\CovenienceStore\\employeedata.txt");
    static {
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    public Manager() {
    }
    public Manager(int num){
        ManagerMenu();
    }
    public void AddEmployee() {
        System.out.println();
        System.out.println("#인적사항 입력");
        System.out.println("<직원 추가>");
        try {
            System.out.print("이름 : ");
            String name = br.readLine();
            if (!name.matches("^[가-힣]*$")) {
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있는지 확인 후 다시 입력하십시오.");
            }
            System.out.print("성별(M/m/W/w) : ");
            String sex = br.readLine();

            if (!sex.equals("M") && !sex.equals("m") && !sex.equals("W") && !sex.equals("w")) {
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있느지 확인 후 다시 입력하십시오.");
            }

            System.out.print("생년월일(YYMMDD) : ");
            String birth = br.readLine();
            if (!birth.matches("\\d{2}((0[1-9]|1[012]))(0[1-9]|[12][0-9]|3[01])")) {
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. 입력 형식을 확인 후 다시 입력하십시오.");
            }
            System.out.print("휴대폰 번호('-'를 빼고 입력 해주세요. ) : ");
            String phone = br.readLine();
            if (!phone.matches("^\\d{3}\\d{3,4}\\d{4}$")) {
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. '-'문자가 포함되어 있는지 확인 후 다시 입력하십시오.");
            }

            System.out.print("계약 기간(개월수) : ");
            String contractMonths = br.readLine();
            if (!contractMonths.matches("[1-9]{1}|^1[0-9]{1}$|^2[0-4]{1}$")) { // 1차 기획서에 0~24 -> 1 ~24로 변경
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. 다시 입력하십시오.");
            }

//            System.out.println(uid); // 동일 uid 체크
            EmployeeInfoData eid = new EmployeeInfoData(name, birth, sex, phone, contractMonths);
            Employee employee = new Employee(eid);
            employeeMap.put(eid.getUid(), employee);
            editData(1, employee);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputTypeErrorException e) {
            System.out.println(e.getMessage());
            return;
        }


    }

    private void editData(int mode, Employee data) {
        try {

            Scanner sc2 = new Scanner(file);
            BufferedWriter bw2 = new BufferedWriter( new FileWriter(file, true));
            switch (mode) {
                case 1: // 직원 추가
                    bw2.write(String.valueOf(data)+"\n");
                    bw2.flush();
                    System.out.println("정상적으로 추가 되었습니다.");
                    System.out.println();
                    break;
                case 2: // 직원 인적사항 수정
                    String result ="";
                    while (sc2.hasNextLine()) {
                        String temp = sc2.nextLine();
                        if (temp.contains(data.getData().getUid())) {
                            result += data+"\n";
                        } else {
                            result += temp+"\n";
                        }
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(result);
                    bw.flush();
                    System.out.println("정상적으로 수정 되었습니다.");
                    System.out.println();
                    break;
                case 3: //직원 계약기간 수정
                    String result2 ="";
                    while (sc2.hasNextLine()) {
                        String temp = sc2.nextLine();
                        if (temp.contains(data.getData().getUid())) {
                            result2 += data+"\n";
                        } else {
                            result2 += temp+"\n";
                        }
                    }
                    BufferedWriter bw3 = new BufferedWriter(new FileWriter(file));
                    bw3.write(result2);
                    bw3.flush();
                    System.out.println("정상적으로 수정 되었습니다.");
                    System.out.println();
                    break;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public void RemoveEmployee () {
        }

        public void ModifyEmployee () {
            try {
                System.out.print("수정할 직원의 ID값 입력 : ");
                String uid = br.readLine();
                Employee em = employeeMap.get(uid);
                if(em==null) {
                    System.out.println("정상적인 입력이 아닙니다. 다시 입력하십시오. "); // 해당 고유아이디를 가진 직원이 없습니다. 확인후 다시 입력해주세요.-> 변경
                    System.out.println();
                }
               else {
                    System.out.println();
                    System.out.println("1. 인적사항 수정\t 2. 계약기간 수정\t3. 돌아가기");
                    System.out.print("입력 : ");
                    int n = Integer.parseInt(br.readLine());
                    switch (n) {
                        case 1:
                            personalDataEdit(em);
                            break;
                        case 2:
                            contractDataEdit(em);
                            break;
                        case 3:
                            return;
                        default:
                            throw new InputTypeErrorException("정상적인 입력이 아닙니다. 다시 입력하십시오.");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InputTypeErrorException e) {
                System.out.println(e.getMessage());
            }catch (NumberFormatException e) {
                System.out.println("정상적인 입력이 아닙니다. 다시 입력하십시오.");
            }

        }

    private void contractDataEdit(Employee em) {
        try {
            EmployeeInfoData eid = em.getData();
            System.out.println("#변경 전 인적사항");
            System.out.println("계약기간(개월수): " + eid.getContractMonths());
            System.out.println("남은계약기간(일수): "+eid.getEndDays());
            System.out.print("계약기간을 연장하시겠습니까?(Y/N): ");
            String check = br.readLine();
            if (!check.equals("Y") && !check.equals("N")) {
                throw new InputTypeErrorException("정상적인 입력이 아닙니다. 대소문자를 확인 후 다시 입력하십시오.");
            }
            if (check.equals("N")) {
                System.out.println();
                return;
            }
            else {
                System.out.println("--------------------------------------------");
                System.out.print("추가 할 계약기간 입력(개월수): ");

                String contractMonths = br.readLine();
                if (!contractMonths.matches("[1-9]{1}|^1[0-9]{1}$|^2[0-4]{1}$")) { // 1차 기획서에 0~24 -> 1 ~24로 변경
                    throw new InputTypeErrorException("정상적인 입력이 아닙니다. 최소 계약기간은 1개월부터\n" +
                            "최대 계약기간은 24개월 입니다.");
                }
                eid.setContractMonths(contractMonths);
                eid.setEndDays(contractMonths);
                em.setData(eid);
                editData(3, em);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InputTypeErrorException e) {
            System.out.println(e.getMessage());
        }

    }

    private void personalDataEdit (Employee em){
            try {
                EmployeeInfoData eid = em.getData();
                System.out.println("#변경 전 인적사항");
                System.out.println("이름: " + eid.getName());
                System.out.println("성별: " + eid.getSex());
                System.out.println("생년월일: " + eid.getBirth());
                System.out.println("핸드폰 번호: " + eid.getPhoneNum());
                System.out.print("변경하시겠습니까?(Y/N): ");
                String check = br.readLine();
                if (!check.equals("Y") && !check.equals("N")) {
                    throw new InputTypeErrorException("정상적인 입력이 아닙니다. 대소문자를 확인 후 다시 입력하십시오.");
                }
                if (check.equals("N")) return;
                else {
                    System.out.println("#인적사항 입력");
                    System.out.print("이름: ");
                    String name = br.readLine();
                    if (!name.matches("^[가-힣]*$")) {
                        throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있느지 확인 후 다시 입력하십시오.");
                    }
                    System.out.print("성별(M/m/W/w) : ");
                    String sex = br.readLine();
                    if (!sex.equals("M") && !sex.equals("m") && !sex.equals("W") && !sex.equals("w")) {
                        throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있느지 확인 후 다시 입력하십시오.");
                    }
                    System.out.print("생년월일(YYMMDD) : ");
                    String birth = br.readLine();
                    if (!birth.matches("\\d{2}((0[1-9]|1[012]))(0[1-9]|[12][0-9]|3[01])")) {
                        throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있느지 확인 후 다시 입력하십시오.");
                    }
                    System.out.print("휴대폰 번호('-'를 빼고 입력 해주세요. ) : ");
                    String phone = br.readLine();
                    if (!phone.matches("^\\d{3}\\d{3,4}\\d{4}$")) {
                        throw new InputTypeErrorException("정상적인 입력이 아닙니다. 특수문자나 숫자가 포함되어 있느지 확인 후 다시 입력하십시오.");
                    }
                    eid.setPersonalData(name, sex, birth, phone);
                    em.setData(eid);
                    editData(2, em);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InputTypeErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        public void findEmployee () {

        }

        public static Manager getInstance () {
            return new Manager();
        }

        public void ManagerMenu () {
            while (true) {
                System.out.println("<관리자 모드>");
                System.out.println("1. 직원 추가 2. 직원 조회 3. 직원 정보 수정 4. 직원 삭제 5. 관리자 모드 종료");
                System.out.print("입력 : ");

                int choice = 0;
                try {
                    choice = Integer.parseInt(br.readLine());

                    switch (choice) {
                        case 1:
                            AddEmployee();
                            break;
                        case 2:
                            RemoveEmployee();
                            break;
                        case 3:
                            ModifyEmployee();
                            break;
                        case 4:
                            findEmployee();
                            break;
                        case 5:
                            System.out.println();
                            sc.close();
                            return;
                        default:
                            throw new InputTypeErrorException("정상적인 입력이 아닙니다. 다시 입력하십시오.");
                    }
                }
             catch (IOException e) {
                 System.out.println(e.getMessage());
            }
                catch (InputTypeErrorException e) {
                    System.out.println(e.getMessage());
                }
                catch (NumberFormatException e) {
                    System.out.println("정상적인 입력이 아닙니다. 다시 입력하십시오.");
                }
            }
        }
    }
