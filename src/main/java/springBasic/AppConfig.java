package springBasic;

import springBasic.discount.DiscountPolicy;
import springBasic.discount.RateDiscountPolicy;
import springBasic.member.MemberRepository;
import springBasic.member.MemberService;
import springBasic.member.MemberServiceImpl;
import springBasic.member.MemoryMemberRepository;
import springBasic.order.OrderService;
import springBasic.order.OrderServiceImpl;
import org.springframework.context.annotation.*;
import springBasic.singleton.StatefulService;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl((MemoryMemberRepository) memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public StatefulService statefulService() {
        return new StatefulService();
    }
}