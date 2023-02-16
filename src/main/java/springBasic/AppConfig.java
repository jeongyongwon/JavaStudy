package springBasic;

import springBasic.discount.FixDiscountPolicy;
import springBasic.member.MemberService;
import springBasic.member.MemberServiceImpl;
import springBasic.member.MemoryMemberRepository;
import springBasic.order.OrderService;
import springBasic.order.OrderServiceImpl;

public class AppConfig {

    //외부에서 생성자를 주입하게됨
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}
