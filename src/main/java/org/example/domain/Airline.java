package org.example.domain;

public class Airline{

    private String airline; // 항공사

    private int planeNum;
    private int seatNum;

    private int userNum;

    String[] plane1 = {" 아시아나 항공 ", " 12:30분 출발 ", " 2:40분 도착 ", "인천(ICN)  =>  삿포로(CTS)"};
    String[] plane2 = {" 대한 항공 ", " 19:30분 출발 ", " 22:10분 도착 ", "인천(ICN)  =>  하노이(HAN)"};
    String[] plane3 = {" 제주 항공 ", " 21:10분 출발 ", " 23:45분 도착 ", "인천(ICN)  =>  하노이(HAN)"};
    String[] plane4 = {" 티웨이 항공 ", " 8:30분 출발 ", " 14:00분 도착 ", "인천(ICN)  =>  괌(GUM)"};
    String[] plane5 = {" 대한 항공 ", " 10:55분 출발 ", " 17:25분 도착 ", "인천(ICN)  =>  런던(LHR)"};

    int[] seats1 = {1, 1, 0, 0, 0, 0, 0, 0, 1, 1};
    int[] seats2 = {0, 1, 0, 0, 0, 0, 0, 0, 1, 0};
    int[] seats3 = {1, 1, 0, 1, 1, 1, 1, 1, 1, 1};
    int[] seats4 = {1, 1, 1, 1, 0, 0, 0, 0, 1, 1};
    int[] seats5 = {0, 1, 0, 1, 0, 1, 0, 0, 1, 1};

    private int[][] seats = {seats1, seats2, seats3, seats4, seats5};

    private String[][] planes = {plane1, plane2, plane3, plane4, plane5};

    public Airline(){}
    public Airline(int planeNum, int seatNum, int userNum){
        this.planeNum = planeNum;
        this.seatNum = seatNum;
        this.userNum = userNum;
    }

    public int[][] getSeats(){
        return seats;
    }

    public void setSeats(int planeNum, int seatNum, int reserve){   // 항공편, 좌석 번호를 받아 1 또는 0 set
        this.seats[planeNum][seatNum] = reserve;
    }
    public String[][] getPlanes(){
        return planes;
    }

    public void setPlanes(String[][] planes){
        this.planes = planes;
    }

    public int getPlaneNum() {
        return planeNum;
    }

    public void setPlaneNum(int planeNum) {
        this.planeNum = planeNum;
    }

    public int getSeatNum() { return seatNum; }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }
}