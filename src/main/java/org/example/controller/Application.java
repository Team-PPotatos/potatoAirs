package org.example.controller;

import org.example.Airline;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu;
        Airline airline = new Airline();

        do {
            System.out.println("----- ✈Potato-Air 예매 프로그램✈ -----");
            System.out.println("1. 로그인하기");
            System.out.println("2. 티켓 예매하기");
            System.out.println("3. 예매 취소하기");
            System.out.println("4. 예매 내역 조회하기");
            System.out.println("5. 비밀번호 변경하기");
            System.out.println("6. 프로그램 종료");
            System.out.print("원하시는 메뉴 입력해주세요. : ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    airline.longin();
                    break;
                case 2:
                    airline.ticketReserve();
                    break;
                case 3:
                    airline.ticketCancel();
                    break;
                case 4:
                    airline.reserveListShow();
                    break;
                case 5:
                    airline.changePassword();
                    break;
                case 6:
                    System.out.print("정말로 시스템을 종료하시겠습니까? (예:y / 아니오:n) : ");
                    String yesOrNo = sc.next();
                    if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
                        System.out.println("Potato-Air 예매 프로그램을 종료합니다. 저희 Potato-Air를 이용해주셔서 갑사합니다.");
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
