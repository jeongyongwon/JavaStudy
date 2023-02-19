package springBasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springBasic.member.Grade;
import springBasic.member.Member;
import springBasic.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
    // AppConfig appConfig = new AppConfig();
    // MemberService memberService = appConfig.memberService();

        //스프링 컨테이너 => AppConfig.class에 있는 Bean들을 관라하는 컨테이너가 된다.
        ApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig에서 해당 객체를 찾을 것이다
        // method 이름으로 bean 등록, 타입도 등록
       MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
