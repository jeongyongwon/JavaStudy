package springBasic;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//Configuration annotation이  붙은 bean 객체 주입하는 클래스들이 ComponentScan 과 충돌하지 않기 위해 필터링한다
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

//패키지 단위로 설정할 수 있다
@ComponentScan(
        basePackages = {"springBasic"}
)
public class AutoAppConfig {
}
