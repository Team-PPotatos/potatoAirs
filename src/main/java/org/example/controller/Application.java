package org.example.controller;

import org.example.service.UserService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserService();

        Scanner sc = new Scanner(System.in);
        int menu;

        do {
            System.out.println();
            System.out.println("----- ✈Potato-Air 예매 프로그램✈ -----");
            System.out.println();
            System.out.println("1. 회원가입 하기");
            System.out.println("2. 로그인하기");
            System.out.println("3. 티켓 예매하기");
            System.out.println("4. 예매 취소하기");
            System.out.println("5. 예매 내역 조회하기");
            System.out.println("6. 비밀번호 변경하기");
            System.out.println("7. 프로그램 종료");
            System.out.print("원하시는 메뉴 입력해주세요. : ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    userService.joinUser();
                    break;
                case 2:
                    userService.login();
                    break;
                case 3:
                    userService.showFlight();
                    break;
                case 4:
                    userService.delFlight();
                    break;
                case 5:
                    userService.showFlightInfo();
                    break;
                case 6:
                    userService.newPassword();
                    break;

                case 7:
                    System.out.print("정말로 시스템을 종료하시겠습니까? (예:y / 아니오:n) : ");
                    String yesOrNo = sc.next();
                    if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
                        System.out.println("Potato-Air 예매 프로그램을 종료합니다. 저희 Potato-Air를 이용해주셔서 감사합니다.");
                        return;
                    }
                    break;
                default:
                    System.out.println("존재하지 않는 업무 서비스입니다. 다시 입력해주세요.");
                    break;
            }
        } while (true);
    }
}
