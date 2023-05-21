package org.example.service;

import org.example.domain.Airline;
import org.example.domain.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    User[] userInfo = new User[20];
    Airline[] flightInfo = new Airline[20];
    Airline airline = new Airline();


    User user = new User();
    Scanner sc = new Scanner(System.in);

    int idx = 0;
    int flight_idx = 0;

    boolean check = false;
    int LoginCorrectPersonNum;     // isMember 에서 찾은 사람의 번호(i) 저장
    ArrayList<Integer> canceledNum = new ArrayList<>(); // 예약 삭제 번호 리스트 (참조 막는 용)

    public boolean isMember(String id, String pwd) {    // 로그인 할 때
        for (int i = 0; i < idx; i++) {
            // System.out.println(id + pwd);
            if (id.equals(userInfo[i].getId()) && pwd.equals(userInfo[i].getPwd())) {
                LoginCorrectPersonNum = i;
                check = true;
                break;
            }
        }
        return check;
    }

    public int getIdx(String pwd) {
        for (int i = 0; i < idx; i++) {
            if (pwd.equals(userInfo[i].getPwd())) {
                return i;
            }
        }
        return 31;
    }

    public boolean containsBlank(String str) {   // 공백 포함 시 false, 없다면 true
        if (str.isEmpty() || str.contains(" ")) {               // 같은 아이디일 시
            System.out.println("공백은 포함될 수 없습니다!");
            return true;
        }
        return false;
    }


    public void joinUser() {
        String id;
        String pwd;
        String name;

        System.out.println("회원가입을 진행합니다.");

        while (true) {
            System.out.print("아이디를 입력해주세요. : ");
            id = sc.nextLine();
            if (containsBlank(id)) continue; // 공백

            System.out.print("비밀번호를 입력해주세요. : ");
            pwd = sc.nextLine();
            if (containsBlank(pwd)) continue;

            System.out.print("이름을 입력해주세요. : ");
            name = sc.nextLine();
            if (containsBlank(name)) continue;

            break;
        }

        userInfo[idx] = new User(id, pwd, name, 500000);
        idx++;
        // System.out.println(idx);
        System.out.println("회원 가입을 완료 했습니다.");
    }

    public void login() {
        if (userInfo[0] == null) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        if (userInfo[LoginCorrectPersonNum].isLogin()) {        // 이미 로그인 되어있는 상태
            System.out.println("이미 로그인 상태입니다.");
            return;
        }

        System.out.print("아이디를 입력해주세요 : ");
        String id = sc.nextLine();

        System.out.print("비밀번호를 입력해주세요 : ");
        String pwd = sc.nextLine();

        while (!isMember(id, pwd)){                                  // 일치하지 않음
            System.out.println("로그인에 실패했습니다. 아이디와 비밀번호가 일치하지 않습니다.");
            System.out.print("아이디를 입력해주세요 : ");
            id = sc.nextLine();

            System.out.print("비밀번호를 입력해주세요 : ");
            pwd = sc.nextLine();
        }

        userInfo[LoginCorrectPersonNum].setLogin(true); // while문을 빠져나왔다 == 로그인 정보가 있다.
        System.out.println("로그인에 성공했습니다.");
    }

    public void showFlight() {
        if (userInfo[0] == null) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }


        // 로그인 여부 검사
        if (!userInfo[LoginCorrectPersonNum].isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("====================== 항공편 목록 ====================== \n");
            for (String[] i : airline.getPlanes()) {
                // System.out.println("\n");
                for (String j : i) {
                    System.out.print(j);
                }
                System.out.println("\n");
            }
            System.out.println("========================================================");
            System.out.print("\n 몇 번째 항공권을 예매하시겠습니까? : ");
            int planeNum = sc.nextInt();
            System.out.println("\n ============================");
            for (int i : airline.getSeats()[planeNum - 1]) {
                System.out.print(" " + i + " ");
            }
            System.out.println(" \n ============================");
            System.out.print("\n 몇 번째 좌석을 예매하시겠습니까? : ");

            int seatNum = sc.nextInt();
            sc.nextLine(); // 버퍼 비우는 역할
            if (airline.getSeats()[planeNum - 1][seatNum - 1] == 0) {
                System.out.println("이미 예약된 좌석입니다. 다른 좌석을 이용해주세요.");
                break;
            }
            int userNum = LoginCorrectPersonNum;
            airline.setSeats(planeNum - 1, seatNum - 1, 0);     // 예약해서 0으로 바꾸는 함수
            flightInfo[flight_idx] = new Airline(planeNum, seatNum, userNum);
            flight_idx++;
            System.out.println(userNum + "번 회원님의 " + planeNum + "번째 비행기의 " + seatNum + "번째 좌석 예약이 완료되었습니다.");
            break;
        }
    }






    public void delFlight() {
        if (userInfo[0] == null) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        if (!userInfo[LoginCorrectPersonNum].isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

        if (flightInfo[0] == null){
            System.out.println("예약한 내역이 없습니다.");
            return;
        }



        showFlightInfo();
        System.out.print("몇 번 예약을 취소하겠습니까? : ");
        int cancelNum = sc.nextInt() - 1;    // 1번 -> 0번으로 인식

        System.out.print("해당 예약을 취소합니까? (예: 1 아니오: 0) : ");
        int ans = sc.nextInt();


        if (ans == 0){
            System.out.println("취소 하지 않습니다.");
            return;
        }
        canceledNum.add(cancelNum);     // 삭제 시 생기는 null 참조 방지 리스트
        int planeNum = flightInfo[cancelNum].getPlaneNum() - 1;
        int seatNum = flightInfo[cancelNum].getSeatNum() - 1;

        System.out.println(planeNum + " " + seatNum);
        airline.setSeats(planeNum, seatNum, 1);     // 예약을 취소해서 다시 1으로 바꾸는 함수
        flightInfo[cancelNum] = null;
        System.out.println("취소를 완료 했습니다.");
    }




    public List<Integer> findFlightInfo(int userNum) {    // 여러 개일 수 있다.
        List<Integer> flightInfoArr = new ArrayList<>();
        for (int i = 0; i < flight_idx; i++) {
//            System.out.println(i);

            if (canceledNum.contains(i)) {        // 삭제한 인덱스
                continue;
            }

            if (userNum == flightInfo[i].getUserNum()) {
                flightInfoArr.add(i);
            }
        }
        return flightInfoArr;
    }

    public void showFlightInfo() {      // 메뉴의 예매 내역 조회
        if (flightInfo[0] == null) {
            System.out.println("예약한 내역이 없습니다.");
            return;
        }

        for(int i : findFlightInfo(LoginCorrectPersonNum)) {
            System.out.println(flightInfo[i].getPlaneNum() + "번 비행기의 " + flightInfo[i].getSeatNum() + "번 좌석");
        }
    }


    public void newPassword() {
        if (userInfo[0] == null) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        if (!userInfo[LoginCorrectPersonNum].isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

        System.out.print("아이디를 입력해주세요. : ");
        String id = sc.nextLine();

        System.out.print("현재 비밀번호를 입력해주세요. : ");
        String pass = sc.nextLine();

        int userNum = getIdx(pass);
        boolean checkPw = userInfo[userNum].getPwd().equals(pass);
        boolean checkId = userInfo[userNum].getId().equals(id);

        if (!checkPw || !checkId ) {
            System.out.println("아이디 또는 비밀번호를 잘못 입력 하셨습니다.");
        } else {
            System.out.print("변경할 비밀번호를 입력해주세요 : ");
            String newPassword = sc.nextLine();

            userInfo[userNum].setPwd(newPassword);
            System.out.println("비밀번호를 " + newPassword + "로 변경했습니다.");
        }
    }
}