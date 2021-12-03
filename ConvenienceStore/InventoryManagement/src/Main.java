import java.io.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        //사용자 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        InventoryManager im = InventoryManager.getInstance();

        while (true) {
            System.out.println("1. 상품등록");
            System.out.println("2. 상품조회");
            System.out.println("3. 재고수정");
            System.out.println("4. 상품삭제");
            System.out.println("5. 뒤로가기");
            System.out.print("선택하셔요\t");

            int inventoryMenu = -1;
            try {
                inventoryMenu = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                //정수 값이 아닐 경우
                System.out.println("메뉴 선택 오류");
            }

            if (inventoryMenu != -1) {
                switch (inventoryMenu) {
                    case 1:
                        //상품등록
                        System.out.println();
                        System.out.println("등록하고자 하는 제품명을 입력하세요");
                        System.out.print("제품명 입력>\t");
                        String itemName = br.readLine();

                        //앞 뒤 공백 허용 X
                        if (!itemName.trim().equals(itemName)) {
                            System.out.println("제품명 입력 오류");
                            break;
                        }
                        //영어 소문자만 허용
                        if (!itemName.matches("^[a-z]*$")) {
                            System.out.println("제품명 입력 오류");
                            break;
                        }

                        //문자열 길이 1이상 20이하
                        if (itemName.length() < 1 || itemName.length() > 20) {
                            System.out.println("제품명 입력 오류");
                            break;
                        }

                        System.out.print("가격 입력>\t");
                        String input = br.readLine();

                        //선행 0 허용 X
                        if (input.charAt(0) == '0') {
                            System.out.println("가격 입력 오류");
                            break;
                        }

                        int itemPrice;
                        try {
                            itemPrice = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println("가격 입력 오류");
                            break;
                        }

                        //가격 0이상 1,000,000이하
                        if (itemPrice < 0 || itemPrice > 1000000) {
                            System.out.println("가격 입력 오류");
                            break;
                        }

                        im.addItem(String.valueOf(im.getLastIndex() + 1), itemName, itemPrice, 0, false);

                        System.out.println("제품이 등록되었습니다.");
                        System.out.println();
                        break;
                    case 2:
                        //상품조회
                        System.out.println();
                        System.out.println("조회하고자 하는 검색어를 입력하세요");
                        System.out.print("검색어 입력>\t");
                        String searchKeyword = br.readLine();

                        im.searchItem(searchKeyword);
                        System.out.println();
                        break;
                    case 3:
                        //재고수정
                        System.out.println();
                        System.out.println("조회하고자 하는 검색어를 입력하세요");
                        System.out.print("검색어 입력>\t");
                        String updateKeyword = br.readLine();

                        HashMap<Integer, String> updateMap = im.searchItemWithCount(updateKeyword);

                        System.out.println("찾으려는 번호를 입력하세요");
                        System.out.print("번호 입력>\t");
                        int updateCount = Integer.parseInt(br.readLine());

                        if (!updateMap.containsKey(updateCount)) {
                            System.out.println("번호 입력 오류");
                            break;
                        }

                        im.searchItemByIndex(updateMap.get(updateCount));

                        System.out.println();
                        System.out.print("변경할 수량을 입력하세요\t");

                        String updateInput = br.readLine();
                        if (updateInput.charAt(0) == '0') {
                            System.out.println("변경할 수량 입력 오류");
                            break;
                        }

                        int updateInventoryCount;
                        try {
                            updateInventoryCount = Integer.parseInt(updateInput);
                        } catch (NumberFormatException e) {
                            //정수 변환 오류
                            System.out.println("변경할 수량 입력 오류");
                            break;
                        }

                        //변경할 수량 0이상 1,000,000이하
                        if (updateInventoryCount < 0 || updateInventoryCount > 1000000) {
                            System.out.println("변경할 수량 입력 오류");
                            break;
                        }

                        im.updateItemInventoryCount(updateMap.get(updateCount), updateInventoryCount);
                        im.searchItemByIndex(updateMap.get(updateCount));


                        System.out.println();
                        break;
                    case 4:
                        //상품삭제
                        System.out.println();
                        System.out.println("삭제하고자 하는 검색어를 입력하세요");
                        System.out.print("검색어 입력>\t");
                        String removeKeyword = br.readLine();

                        HashMap<Integer, String> removeMap = im.searchItemWithCount(removeKeyword);

                        System.out.println();
                        System.out.println("삭제하려는 번호를 입력하세요");
                        System.out.print("번호 입력>\t");
                        int removeCount = Integer.parseInt(br.readLine());

                        if (!removeMap.containsKey(removeCount)) {
                            System.out.println("번호 입력 오류");
                            break;
                        }

                        im.searchItemByIndex(removeMap.get(removeCount));
                        im.removeItem(removeMap.get(removeCount));
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        //1~5 아닌 값 선택 시
                        System.out.println();
                        System.out.println("메뉴 선택 오류");
                        System.out.println();
                }
            }
        }
    }
}
