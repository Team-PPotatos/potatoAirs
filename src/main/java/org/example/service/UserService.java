package org.example.service;

import org.example.domain.Airline;
import org.example.domain.User;

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

    public boolean isMember(String id, String pwd) {

        for (int i = 0; i < idx; i++) {
            // System.out.println(id + pwd);
            if (id.equals(userInfo[i].getId()) && pwd.equals(userInfo[i].getPwd())) {
                check = true;
                if (userInfo[i].isLogin()) {
                    System.out.println("이미 로그인 상태입니다.");
                } else {
                    userInfo[i].setLogin(true);
                }
                break;
            }
        }
        return check;
    }

    public int getIdx(String pwd) {
        int index = 31;
        for (int i = 0; i < idx; i++) {
            if (pwd.equals(userInfo[i].getPwd())) {
                return i;
            }
        }
        return index;
    }


    public void joinUser() {
        System.out.println("회원가입을 진행합니다.");
        System.out.print("아이디를 입력해주세요. : ");
        String id = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요. : ");
        String pwd = sc.nextLine();
        System.out.print("이름을 입력해주세요. : ");
        String name = sc.nextLine();

        userInfo[idx] = new User(id, pwd, name, 500000);
        idx++;
        // System.out.println(idx);
        System.out.println("회원 가입을 완료 했습니다.");
    }

    public void login() {
        if (userInfo[0] == null) {
            System.out.println("회원가입을 먼저 진행해주세요.");
        } else {
            System.out.print("아이디를 입력해주세요 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호를 입력해주세요 : ");
            String pwd = sc.nextLine();
            if (isMember(id, pwd)) {
                System.out.print("로그인에 성공헸습니다.");
            } else {
                System.out.print("로그인에 실패했습니다. 아이디와 비밀번호가 일치하지 않습니다.");
            }
        }
    }

    public void showFlight() {
        System.out.print("회원이신가요? (예 : 1, 아니오 : 2) : ");
        int ans = sc.nextInt();
        if (ans == 2) {
            System.out.println("회원가입을 먼저 진행해주세요.");
        } else {
            loop:
                while (true) {
                    System.out.println();
                    System.out.println("====================== 항공편 목록 ====================== \n");
                    for (String[] i : airline.getPlanes()) {
                        // System.out.println("\n");
                        for (String j : i) {
                            System.out.print(j.toString());
                        }
                        System.out.println("\n");
                    }
                    System.out.println("========================================================");
                    System.out.print("\n 몇번째 항공권을 예매하시겠습니까? : ");
                    int planeNum = sc.nextInt();
                    System.out.println("\n ============================");
                    for (int i : airline.getSeats()[planeNum - 1]) {
                        System.out.print(" " + i + " ");
                    }
                    System.out.println(" \n ============================");
                    System.out.print("\n 몇번째 좌석을 예매하시겠습니까? : ");

                    int seatNum = sc.nextInt();
                    sc.nextLine(); // 버퍼 비우는 역할
                    if (airline.getSeats()[planeNum - 1][seatNum - 1] == 0) {
                        System.out.println("이미 예약된 좌석입니다. 다른 좌석을 이용해주세요.");
                    } else {
                        while (true) {
                            System.out.print("비밀번호를 입력해주세요: ");
                            // sc.nextLine();
                            String pass = sc.nextLine();
                            int userNum = getIdx(pass);

                            if (userNum == 31) {
                                System.out.println("비밀번호를 잘못 입력하셨습니다. 다시 입력하세요.");
                            } else {
                                airline.setSeats(planeNum - 1, seatNum - 1, 0);     // 예약해서 0으로 바꾸는 함수
                                flightInfo[flight_idx] = new Airline(planeNum, seatNum, userNum);
                                flight_idx++;
                                System.out.println(userNum + "번 회원님의 " + planeNum + "번째 비행기의 " + seatNum + "번째 좌석 예약이 완료되었습니다.");
                                break loop;
                        }
                    }
                }
            }
        }

    }

    public void delFlight() {
        sc.nextLine();
        while (true) {
            System.out.print("비밀번호를 입력해주세요. : ");
            String pass = sc.nextLine();
            int userNum = getIdx(pass);
            if (userNum == 31) {
                System.out.println("비밀번호를 잘못 입력 하셨습니다.");
                break;
            } else {
                if(!userInfo[userNum].isLogin()){
                    System.out.println("로그인을 먼저 진행해주세요.");
                    break;
                } else {
                    if (flightInfo[0] == null) {
                        System.out.println("예매한 내역이 없습니다.");
                        break;
                    }
                    int index = findFlightInfo(userNum);
                    int planeNum = flightInfo[index].getPlaneNum();
                    int seatNum = flightInfo[index].getSeatNum();
                    System.out.println(planeNum + "번 비행기의 " + seatNum + "번 좌석");

                    System.out.println("해당 항공권의 예매를 취소하시겠습니까? : \n  예: 1 아니오: 0");
                    int ans = sc.nextInt();

                    if (ans == 0) {
                        System.out.println("취소 하지 않습니다.");
                        break;
                    } else {
                        airline.setSeats(planeNum - 1, seatNum - 1, 1);     // 예약을 취소해서 다시 1으로 바꾸는 함수
                        flightInfo[index] = null;
                        System.out.println("취소를 완료 했습니다.");
                        break;
                    }
                }
            }
        }
    }

    public int findFlightInfo(int userNum) {
        int index = 34;
        for (int i = 0; i < flight_idx; i++) {
            if (userNum == flightInfo[i].getUserNum()) {
                return i;
            }
        }
        return index;
    }

    public void showFlightInfo() {
        while (true) {
            System.out.print("비밀번호를 입력해주세요. : ");
            String pass = sc.next();
            int userNum = getIdx(pass);
            if (userNum == 31) {
                System.out.println("비밀번호를 잘못 입력 하셨습니다.");
                break;
            } else {
                if (flightInfo[0] == null) {
                    System.out.println("예매한 내역이 없습니다.");
                    break;
                }
                int index = findFlightInfo(userNum);
                System.out.println(flightInfo[index].getPlaneNum() + "번 비행기의 " + flightInfo[index].getSeatNum() + "번 좌석");
                break;
            }
        }
    }

    public void newPassword() {
        sc.nextLine();
        System.out.print("아이디를 입력해주세요. : ");
        String id = sc.nextLine();
        while (true) {
            System.out.print("비밀번호를 입력해주세요. : ");
            String pass = sc.nextLine();
            int userNum = getIdx(pass);
            if (userNum == 31) {
                System.out.println("비밀번호가 일치하지 않습니다.");
            } else {
                boolean checkPw = userInfo[userNum].getPwd().equals(pass);
                boolean checkId = userInfo[userNum].getId().equals(id);
                if (!checkPw || !checkId) {
                    System.out.println("아이디 또는 비밀번호를 잘못 입력 하셨습니다.");
                } else {
                    System.out.print("변경할 비밀번호를 입력해주세요 : ");
                    String newPassword = sc.nextLine();

                    userInfo[userNum].setPwd(newPassword);
                    System.out.println("비밀번호를 " + newPassword + "(으)로 변경했습니다.");
                    break;
                }
            }
        }
    }
}