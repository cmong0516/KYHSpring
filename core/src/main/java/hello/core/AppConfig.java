package hello.core;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AppConfig 로 인해 사용영역과  구성영역이 명확히 분리되어있으며
//수정할 부분이 생겨도 사용부만 수정하면 된다 !!

//@Configuration : 자 이게 설정파일이야.
@Configuration
public class AppConfig {

//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
    //* MemberServiceImple안에 구현체 MemoryMemberRepository가 주입되어 들어간다.
    //* 구현체 의존을 피하기 위해 리턴타입이 interface MemberRepository인 memberRepository메서드를 생성하여 구현체를 리턴해준다.
    //* 따라서 아래의 코드처럼 구현체가 아닌 interface 를 의존하도록 반경해주며 중복제거도 같이 해준다.
//    }

    //@Bean memberService라는 이름으로 Spring 컨테이너에 이거 올릴거야.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //Bean memberRepository라는 이름으로 Spring 컨테이너에 이거 올릴거야.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //Bean orderService 라는 이름으로 Spring 컨테이너에 올릴거야.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
//이처럼 코드를 구현체가 아닌 interface 역할에 의존하게 만들경우
    // FixDiscoundPolicy(vip면 1000원 할인) 에서 RateDiscountPolicy(vip면 10%할인) 으로 변경할때
    // 아래의 return 부분만 변경해주면 된다.

    //Bean discountPolicy 라는 이름으로 Spring 컨테이너에 올릴거야.
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
