package org.example.service;

import org.example.domain.Airline;
import org.example.domain.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {
    Scanner sc = new Scanner(System.in);
//    User[] userInfo = new User[20];
    ArrayList<User> userInfo = new ArrayList<>();               // 모든 회원 정보

//    Airline[] flightInfo = new Airline[20];

    ArrayList<Airline> flightInfo = new ArrayList<>();          // 모든 예약 정보


    Airline airline = new Airline();    // 항공사 DB 확인용


    User loginUser = new User();

    /* iterator 를 사용하여 순회하기 때문에 index 가 없어도 된다. */
    // int idx = 0;
    // int flight_idx = 0;

    /* isMember 에서 찾은 사람의 번호(i) 저장 (객체 저장 방식으로 변경했기 때문에 필요 X)*/
    // int LoginCorrectPersonNum;

    /* 예약 삭제 번호 리스트 (index 로 순회하지 않기 때문에 nullPointerException 발생도 없다) */
    //    ArrayList<Integer> canceledNum = new ArrayList<>();

    public boolean isMember(String id, String pwd) {    // 로그인 할 때
        boolean check = false;                          // 입력한 로그인 정보가 회원 DB에 존재하는가?
        Iterator<User> userIterator = userInfo.iterator();          // userInfo 순회

        while (userIterator.hasNext()) {
            // System.out.println(id + pwd);
            User user = userIterator.next();    // 확인할 User 객체
            if (id.equals(user.getId()) && pwd.equals(user.getPwd())) {
                loginUser = user;       // 로그인 정보를 찾음
                check = true;
                break;
            }
        }
        return check;
    }

//    public int getIdx(String pwd) {               // id를 활용
//        while (userIterator.hasNext()) {
//            if (pwd.equals(userInfo[i].getPwd())) {
//                return i;
//            }
//        }
//        return 31;
//    }

    public boolean containsBlank(String str) {   // 공백 포함 시 false, 없다면 true
        if (str.isEmpty() || str.contains(" ")) {               // 같은 아이디일 시
            System.out.println("공백은 포함될 수 없습니다!");
            return true;
        }
        return false;
    }

    public boolean exsistingId(String id) {          // 입력한 id 정보가 회원 DB에 이미 존재하는가?
        Iterator<User> userIterator = userInfo.iterator();          // userInfo 순회
        while (userIterator.hasNext()) {
            // System.out.println(id + pwd);
            if (id.equals(userIterator.next().getId())) {   // 같은 id를 찾음
                return true;
            }
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

            /* 공백 또는 id가 이미 존재 */
            if (exsistingId(id)){
                System.out.println("id가 이미 존재합니다!");
                continue;
            }

            if (containsBlank(id)) continue;

            System.out.print("비밀번호를 입력해주세요. : ");
            pwd = sc.nextLine();
            if (containsBlank(pwd)) continue;

            System.out.print("이름을 입력해주세요. : ");
            name = sc.nextLine();
            if (containsBlank(name)) continue;

            break;
        }

        userInfo.add(new User(id, pwd, name, 500000));
        // System.out.println(idx);
        System.out.println("회원 가입을 완료 했습니다.");
    }

    public void login() {
        if (userInfo.isEmpty()) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        while (true) {                                  // 일치하지 않음
            System.out.print("아이디를 입력해주세요 : ");
            String id = sc.nextLine();

            System.out.print("비밀번호를 입력해주세요 : ");
            String pwd = sc.nextLine();

            // 작성된 계정정보의 islogin이 true라면, 이미 로그인되어있습니다 & break 추가 예정
            String userId = id;     // id를 isMember가 잘 읽지 못함
            String userPwd = pwd;   // 잘 읽게 해주는 다리 역할을 해주는 변수

            if (!isMember(userId, userPwd)) {
                System.out.println("로그인에 실패했습니다. 아이디와 비밀번호가 일치하지 않습니다.");
                break;
            } else {    // 해당 객체를 loginUser에 저장
                loginUser.setLogin(true);
                System.out.println("로그인에 성공했습니다.");
                return;
            }
        }
    }

    public void showFlight() {
        if (userInfo.isEmpty()) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        /* 아예 한 번도 로그인을 하지 않거나 로그아웃 시 */
        if (loginUser == null || !loginUser.isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

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
                return;
            }

        String userId = loginUser.getId();

        /* 예약해서 0으로 바꾸는 함수 */
        airline.setSeats(planeNum - 1, seatNum - 1, 0);

        /* 회원 가입때의 id를 중복 불가능으로 만들었기 때문에 id로 flightInfo 를 조회할 수 있다. */
        flightInfo.add(new Airline(planeNum, seatNum, userId));
        System.out.println(userId + "회원님의 " + planeNum + "번째 비행기의 " + seatNum + "번째 좌석 예약이 완료되었습니다.");
    }






    public void delFlight() {
        if (userInfo.isEmpty()) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

        /* 아예 한 번도 로그인을 하지 않거나 로그아웃 시 */
        if (loginUser == null || !loginUser.isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

//        if (flightInfo.isEmpty()){  // 예약한 내역 자체가 X
//            System.out.println("예약한 내역이 없습니다.");
//            return;
//        }                 // 중복

        showFlightInfo();
        System.out.print("몇 번 예약을 취소하겠습니까? : ");
        int cancelNum = sc.nextInt() - 1;    // 1번 -> 0번으로 인식

        System.out.print("해당 예약을 취소합니까? (예: 1 아니오: 0) : ");
        int ans = sc.nextInt();


        if (ans == 0){
            System.out.println("취소 하지 않습니다.");
            return;
        }

        //canceledNum.add(cancelNum);     // canceledNum 삭제

        /* 현재 로그인 되어있는 user의 예매 내역 list */
        List<Airline> loginUserFlight = findFlightInfo(loginUser.getId());

        /* ArrayList를 index로 접근 */
        int planeNum = loginUserFlight.get(cancelNum).getPlaneNum() - 1;
        int seatNum = loginUserFlight.get(cancelNum).getSeatNum() - 1;

//        System.out.println(planeNum + " " + seatNum);
        airline.setSeats(planeNum, seatNum, 1);     // 예약을 취소해서 다시 1으로 바꾸는 함수

        flightInfo.remove(loginUserFlight.get(cancelNum));       // 해당 예약 내역 삭제
        System.out.println("취소를 완료 했습니다.");
    }




    public List<Airline> findFlightInfo(String id) {    // 해당 id의 예약 내역을 List로 return해주는 함수
        List<Airline> flightInfoArr = new ArrayList<>();

        Iterator<Airline> flightiterator = flightInfo.iterator(); // flightInfo 순회
        while (flightiterator.hasNext()) {
//            System.out.println(i);

//            if (canceledNum.contains(i)) {        // 삭제한 인덱스는 pass (NULL POINTER EXCEPTION)
//                continue;                         // iterator 사용으로 삭제 인덱스 신경 쓸 필요 없음
//            }
            Airline air = flightiterator.next();
            if (id.equals(air.getUserId())) {    // flightInfo를 순회하다 해당 id로 예약한 항공 예약이 보일 시
                flightInfoArr.add(air);          // 해당 airline 객체를 array에 추가
            }
        }
        return flightInfoArr;
    }

    public void showFlightInfo() {      // 메뉴의 예매 내역 조회
        if (loginUser == null || !loginUser.isLogin()) {
            System.out.println("로그인이 필요한 기능입니다!");
            return;
        }

        for(Airline air : findFlightInfo(loginUser.getId())) {  // 해당 id의 예약 내역의 리스트
            System.out.println(air.getPlaneNum() + "번 비행기의 " + air.getSeatNum() + "번 좌석");
        }
    }

    public User findUserFromId(String id) {          // 입력한 id 정보로 대응되는 유저를 찾는 함수

        Iterator<User> userIterator = userInfo.iterator();          // userInfo 순회
        while (userIterator.hasNext()) {
            // System.out.println(id + pwd);
            User foundUser = userIterator.next();
            if (id.equals(foundUser.getId())) {        // 같은 id를 찾음
                return foundUser;
            }
        }
        return null;
    }


    public void newPassword() {
        if (userInfo.isEmpty()) {  // 회원 정보 존재 X
            System.out.println("회원가입을 먼저 진행해주세요.");
            return;
        }

//        if (!loginUser.isLogin()) {
//            System.out.println("로그인이 필요한 기능입니다!");
//            return;
//        }

        System.out.print("아이디를 입력해주세요. : ");
        String id = sc.nextLine();

        /* id를 통해 해당 유저를 찾는다. */
        User foundUser = findUserFromId(id);

        if (foundUser == null) {     // 회원 정보에 없는 id
            System.out.println("존재하지 않는 id입니다!");
            return;
        }
        String foundPwd = foundUser.getPwd();   // id를 통해 해당 pwd 값을 가져온다.

        System.out.print("현재 비밀번호를 입력해주세요. : ");
        String pass = sc.nextLine();

        // boolean checkPw = userInfo[userNum].getPwd().equals(pass);

        // int userNum = getIdx(pass);  // 비밀번호로 userinfo의 인덱스를 가져온다 (id로 가져오게 변경)

        if (!foundPwd.equals(pass)) {
            System.out.println("비밀번호를 잘못 입력 하셨습니다.");
            return;
        }

        System.out.print("변경할 비밀번호를 입력해주세요 : ");
        String newPassword = sc.nextLine();

        foundUser.setPwd(newPassword);
        System.out.println("비밀번호를 " + newPassword + "로 변경했습니다.");
    }
}
