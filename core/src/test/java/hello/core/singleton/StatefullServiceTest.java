package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefullServiceTest {
    @Test
    void statefullServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean(StatefullService.class);
        StatefullService statefullService2 = ac.getBean(StatefullService.class);
        //사용자 1
        statefullService1.order("userA", 10000);
        //사용자 2
        statefullService2.order("userB", 20000);

        //사용자 A가 주문 금액을 조회
        int price = statefullService1.getPrice();
        System.out.println("price = " + price);
        //분명 A는 만원을 주문했는데 출력해보면 2만원이 되어있다
    }

    static class TestConfig {

        @Bean
        public StatefullService statefullService() {
            return new StatefullService();
        }
    }
}