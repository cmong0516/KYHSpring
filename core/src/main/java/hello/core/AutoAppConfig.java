package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //시작위치 basePackages
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        //지정하지 않으면 ?? @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작위치.
        //권장하는 방법 : 설정 정보 클래스(@ComponentScan , @Configuration 이 붙은)를 프로젝트 최상단에 두는것.
        //현재 폳더에선 com.hello에 둬야함.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//여기저기 Configuration을 해놔서... 그거 필터로 거르고 가져올거임.
//@ComponentScan : @Component 붙어있는애들 자동으로 가져올거임.
public class AutoAppConfig {

}
