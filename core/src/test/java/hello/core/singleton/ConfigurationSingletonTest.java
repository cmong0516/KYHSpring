package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new
                AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService",
                MemberServiceImpl.class);

        OrderServiceImpl orderService = ac.getBean("orderService",
                OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository",
                MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new
                AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$50587f02
        //일반 클래스라면 class hello.core.AppConfig 에서 끝나야한다.
        //spring이 CGLIB라는 바이트 조작 프로그램을 사용해서 AppConfig를 상속받는 임의의 다른 클래스를 만들고
        //만들어진 그 임의의 클래스가 빈으로 등록된 것이다.
        //이 과정을 통해 스프링 컨테이너에 등록되어 있지 않으면 등록하고 , 등록되어 있다면 등록된 객체를 사용한다.
        //AppConfig 를 상속받기에 AppConfig 로 찾을수 있다.
    }
}
