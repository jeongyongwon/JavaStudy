package springBasic;

import springBasic.discount.DiscountPolicy;
import springBasic.discount.FixDiscountPolicy;
import springBasic.discount.RateDiscountPolicy;
import springBasic.member.MemberRepository;
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
                memberRepository(),
                discountPolicy());
    }

    /*
    * 역할, 구현을 더욱더 분리하게 되었다. 이제는 변경 시 아래의 코드만 수정하면 된다
    * */
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
