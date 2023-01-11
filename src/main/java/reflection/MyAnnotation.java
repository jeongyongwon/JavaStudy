package reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited //상속받은 자식도 annotation을 받을 수 있다
public @interface MyAnnotation {
    //클래스까진 남는데, 메모리에선 읽지 않음
    //그래서 Retenttion을 붙이면 Class도 읽는다

    //기본값도 가진다
    String name() default "yongwon";

    int number() default 1;

    //Annotation에서 값을 넣을 때 value로 주면 key를 적어줄 필요가 없다
    String value() default "yongking";


}
