package main.singleton;

public class Settings {
    // new를 사용해서 밖에서 새로운 생성자를 사용하지 못하게 하려면 private 생성자로 만들어준다.
    private Settings() {}

    // global하게 접근하기 위해서 static으로 만들어준다.
    // 하지만 new로 만들어주기때문에 만들때마다 다른 객체로 생성 된다.
    public static Settings getInstance() {
        return new Settings();
    }

    // 이렇게 생성한다면 밖에서 아무리 생성해도 같은 인스턴스가 생성 가능하다.
    // 하지만 단점은 멀티스레드 환경에서 문제가 발생한다.
    private static Settings instance;

    public static Settings getSameInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Thread-safe 하게 만드는 방법
    // sychronized 키워드 사용
    // 멀티스레드에서도 하나의 스레드만 들어올수 있게 설정
    // 단점: synchronized 키워드에 동기화처리하는 방법때문에 성능저하 우려
    public static synchronized Settings getSyncInstance() {
        if (instance == null) {
            return new Settings();
        }
        return instance;
    }

    // 성능저하가 문제 될것 같고 비용이 비싸지않다라고 판단되면,
    // 단, 만들어 놨는데 쓰지 않으면 좋지 않다.
    private static final Settings INSTANCE = new Settings();

    public static Settings getSyncInstance() {
        if (instance == null) {
            return new Settings();
        }
        return instance;
    }

    // 사용이 될때 만들고 싶고, synchronized 비용이 신경 쓰인다면,
    // double checked locking 기법이 있다. 하지만 이 방법을 개선한 방법 존재
    // static inner class 사용하는 방법
    // 멀티 스레드 방식에서도 Thread-safe하며, 필요로 할때만 instance를 생성한다.
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getSyncInstance() {
        return SettingsHolder.INSTANCE;
    }

}

// 안전하고 단순하게 구현하는 방법
// 리플렉션에 안전, 직렬화-역직렬화에 안전
// 단점은 미리 만들어진다. 로딩하는 순간 만들어진다.
public enum Settings {
    INSTANCE;

}
