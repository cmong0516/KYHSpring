package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 private static 으로 선언
    //다른데서 여러번 객체를 생성하지 못하게 하려고 !!
    //SingletonService 를 불러내려면 getInstance()메서드를 이용해야만 한다.
    //이때 getInstance()메서드는 항상 같은 instance를 반환한다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

//싱글톤의 단점
//1. 싱글톤 구현에 필요한 코드작성
//2. 추상에 의존하지 않고 구현에 의존한다 -- DIP위반
//3. 구현에 의존하기 때문에 OCP를 위한발 가능성이 높다
//4. 테스트하기 어렵다
//5. 유연성이 떨어진다.

//싱글톤 방식의 주의할점
//1. 객체 인스턴스를 하나만 생성하여 공유하기 때문에 상태를 유지하게 설계하면 안된다.
//2. 무상태(stateless)로 설계해야한다.
//- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
//- 특정 클라이언트가 값을 변경할수 있는 필드가 있으면 안된다 Only Read
//3. 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할수 있다.