package main.singleton;

public class App {
    public static void main(String[] args) {
        /* 싱글톤 패턴
        * 인스턴스를 오직 한개만 제공하는 클래스
        */

        Settings settings = Settings.INSTANCE;
    }
}
