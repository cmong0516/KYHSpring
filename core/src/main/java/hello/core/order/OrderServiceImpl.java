package hello.core.order;

import hello.core.discount.DiscountPolicy;
//구현체 의존 안할거임 ㅇㅇ
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //VIP회원일 경우 1000원 할인
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //VIP회원일 경우 10% 할인
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //1. 역할과 구현을 분리했다
    //2. 다형성을 활용하여 인터페이스와 구현 객체를 분리했다.
    //3. OCP, DIP 같은 객체지향 설계 원칙 (SOLID)을 충실히 준수했다.  ---->> X !!
    //왜 ??   ----->     discountPolicy 는 interface DiscountPolicy를 의존하고 있으면서 구현 클래스(객체) RateDiscountPolicy , FixDiscountPolicy 를 같이 의존하고 있다!!
    //interface 의존 하라니까 왜 클래스 의존함 ??      ;;;;      DIP위반임      ;;;;
    //너 방금 위에 코드 변경했자늠 ㅇㅇ OCP위반임                   ;;;;
    //어떻게 해결할거임 ??
    private final DiscountPolicy discountPolicy;
    //일케 선언하고... 누가 구현객체 생성해서 주입 해주면 된다는디 DI ??
    //AppConfig 에서 주입할거니까 생성자 만들기.


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
