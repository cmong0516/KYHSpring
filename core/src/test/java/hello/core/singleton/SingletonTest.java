package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    //스프링 없는 순수한 DI컨테이너 에서는 요청을 할때마다 객체가 새로 생성된다.
    //이를 효율적으로 변경하기 위해선 객체를 1개만 생성하고 공유하도록 한다.
    //위의 방법을 하기위해 사용하는것이 싱글톤이다.
    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void puerContainer() {
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출 할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출 할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        //참조값이 다름을 확인 (다른 객체라는걸 확인)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 과 memberService2 는 달라야한다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        //isSameAs 는 주소값을 isEquerTo 는 내용을 비교한다.
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1.조회 : 호출 할때마다 객체를 생성
        //MemberService memberService1 = appConfig.memberService();
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회 : 호출 할때마다 객체를 생성
        //MemberService memberService2 = appConfig.memberService();
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);
        //참조값이 다름을 확인 (다른 객체라는걸 확인)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 과 memberService2 는 달라야한다.
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
