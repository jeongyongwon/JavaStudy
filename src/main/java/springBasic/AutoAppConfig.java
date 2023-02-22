package springBasic;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//Configuration이 붙은 annotation이 붙은 bean 객체 주입하는 클래스들을 필터링한다
//ComponentScan 과 충돌하지 않기 위해
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
}
