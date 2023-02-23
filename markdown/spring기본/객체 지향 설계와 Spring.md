# 객체 지향 설계와 Spring



## Spring 핵심 개념

- `Java` 기반의 프레임워크
- 좋은 객체 지향 애플리케이션을 개발할 수 있게 해주는 프레임워크



## 좋은 객체 지향 프로그램이란

> **객체 지향 프로그래밍** 이란 프로그램을 **유연**하고 **변경**이 용이하게 만들기 때문에 대규모 소프트웨어 개발에 많이 사용됨 => **다형성** => **역할**과 **구현**으로 **세상**을 구분
>
> 역할과 구현으로 구분하면 단순해짐, 유연, 변경 편리
>
> 역할 = 인터페이스
>
> 구현 = 클래스, 구현 객체



## 다형성의 본질

- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경할 수 있다.



## 스프링과 객체 지향

- 다형성이 가장 중요
- 스프링은 다형성을 극대화해서 이용할 수 있게 도와줌



## 좋은 객체 지향 설계의 5가지 원칙(SOLID)

### SRP ( Single Responsibiiity Principle) - 단일책임의 원칙

- 클래스는 하나의 책임만 가져야한다
- 책임은 클 수도, 작을 수도 있다
- 중요한 기준으로 변경임. 변경이 있을 때 파급 효과가 적으면, 단일 책임 원칙을 잘 따른 것

### OCP (Open/Close principle) - 개방_폐쇄 원칙

- 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다
- 다형성을 잘 지키라는 말이다.

### LSP - 리스코프 치환원칙

- 객체는 프로그램의 정확성을 꺠뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야함
- 단순히 컴파일을 성공하는 것을 넘어서 하위클래스는 인터페이스의 규약을 지켜야한다는 소리
- 예를 들어 엑셀이라는 인터페이스를 구현할 떄 뒤로 가는 기능으로 구현하는 것은 LSP 위반

### ISP - 인터페이스 분리 원칙

- 특정 클라이언트를 위한 인터페이스 여러개가 범용 인터페이스 하나보다 낫다
- 예를들어 자동차 인터페이스보다 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트는 운전자 클라이언트, 정비사 클라이언트로 분리
- 인터페이스가 명확해지고 대체 가능성이 높아진다.

### DIP - 의존관계 역전 원칙

- 프로그래머는 역할에 의존해야지, 구현에 의존하면 안된다.
- 예를들어 주문번호를 생성하는 메서드가 있다고 가정할 때, service 입장에서는 주문번호를 생성한다는
사실에 초점을 맞춰야지, 주문번호를 어떻게 생성하는 지 알 필요가 없다. 설사 내용을 바꾸더라도
인지 못하여야 한다

## 

---

## 제어의 역전 (Inversion of Control)

- 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다.
- 그러나  AppConfig와 같이 구현 객체는 자신의 로직 실행만 담당하고, 어떤 구현 객체들이 내부에서 실행될지 모른다. 
- 그리고 그 흐름의 제어는 AppConfig가 가지고 있다.
- 프레임워크와, 라이브러리 중 IOC에 부합하는 건 프레임워크이다



### 의존관계 주입

- 의존 관계는 **정적인 클래스 의존 관계**와, 실행 시점에 결정되는 **동적인 객체(인스턴스) 의존 관계** 둘을 분리해서 생각해야함
  - 정적인 클래스 의존관계는 외부에서 주입되는 객체에 의존하는 사실은 알지만, **실제로 어떤 구현 객체가 주입되는지 모른다.**
  - 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
  - 스프링은 Ioc 컨테이너 또는 DI 컨테이너가 이러한 역할을 해준다.

​		

------

## 스프링 컨테이너

- `ApplicationContext`를 **스프링 컨테이너**라 한다.
- 기존에는 개발자가 `AppConfig`로 DI 했지만, 이젠 스프링 컨테이너가 한다.
- 스프링 컨테이너는 `@Configuration` 내에 `@Bean`으로 어노테이션이 붙은 메서드의 반환 객체를 스프링 컨테이너에 등록한다.
- 스프링 컨테이너는 빈을 조회할 때 자식 클래스들까지 다 조회한다.



### BeanFactory

- 스프링 컨테이너의 최상위 인터페이스
- 스프링 빈을 관리하고 조회하는 역할
- `getBean` 제공



**ApplicationContext**

- BeanFactory 기능을 모두 상속받아서 제공

- 그 외에도 4가지의 인터페이스를 구현한다
  1.  메시지 소스를 활용한 국제화 기능 : 나라에 따라 언어 설정
  2. 환경변수 : 로컬, 개발, 운영등을 구분해서 처리
  3. 애플리케이션 이벤트 : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
  4. 편리한 리소스 조회 : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회



---



## 싱글톤 컨테이너

> 스프링이 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성한다.
>
> => 메모리 낭비가 심하다.
>
> => **해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다**
>
> => 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
>
> => private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야함



```java
public class SingletonService {
	 	//1. static 영역에 객체를 딱 1개만 생성해둔다.
	 private static final SingletonService instance = new SingletonService();
	 /*
     	 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록
	 	허용한다.
	 */

	 public static SingletonService getInstance() {
		 return instance;
	 }
	 //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
	 private SingletonService() {
	
     }
    
	 public void logic() {
		 System.out.println("싱글톤 객체 로직 호출");
	 }
}
```



### 싱글톤 패턴의 문제점

- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감
- DIP를 위반한다. (구체 클래스에 의존한다.)
- OCP 위반
- 내부 속성 변경 및 초기화 어렵
- 유연성이 떨어짐



### 그러나 스프링 컨테이너가 싱글톤 패턴의 문제점을 아주 잘 해결해준다.  스프링 빈이 바로 싱글톤으로 관리되는 빈이다.



### 싱글톤 방식의 주의점

- 상태를 유지하게 설계하면 안된다.
- 가급적 읽기만 가능해야 한다.
- 특정 클라이언트에 의존적인 필드가 발생하면 안됨





---



## 다양한 의존관계 주입 방법

> 크게 4가지가 있다.
>
> - 생성자 주입
> - 수정자 주입
> - 필드 주입
> - 일반 메서드 주입



### 생성자 주입

- 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
- 불변, 필수 의존관계에 사용

```java
@Component
public class OrderServiceImpl implements OrderService {
	 private final MemberRepository memberRepository;
	 private final DiscountPolicy discountPolicy;
 
     @Autowired //생성자가 하나일 때는 생략해도 된다.
	 public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy 
		discountPolicy) {
	 this.memberRepository = memberRepository;
	 this.discountPolicy = discountPolicy;
 }
}
```



### 수정자 주입

- 선택, 변경 가능성이 있는 의존 관계에 사용

```java
@Component
public class OrderServiceImpl implements OrderService {
 private MemberRepository memberRepository;
 private DiscountPolicy discountPolicy;
    @Autowired(required = false) //주입할 Bean이 등록안되어있어도 동작하게 만들 때
     public void setMemberRepository(MemberRepository memberRepository) {
     	this.memberRepository = memberRepository;
     }
    
	 @Autowired
     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		 this.discountPolicy = discountPolicy;
	 }
}    
```



### 필드 주입

- 코드가 간결해서 좋지만, DI 프레임워크가 없으면 아무것도 할 수 없다.
- 테스트할 때 `NPE` 가 발생할 것이다. => `@SpringBootTest` 처럼 스프링 컨테이너 테스트를 통합한 경우 가능하다고 한다.
- 그냥 사용하지 말자 (테스트 코드나, 비즈니스 로직과 관련없는 부분은 써도 된다.)

```java
@Component
public class OrderServiceImpl implements OrderService {
     @Autowired
     private MemberRepository memberRepository;
     @Autowired
     private DiscountPolicy discountPolicy;
}
```





### **결과적으로 생성자 주입을 선택하라** 👀

#### 불변

- 대부분의 의존관계 주입은 한번 일어나기 때문에, 일반적으로 종료 전까지 변할 일도 없고 변하면 안된다.
- 수정자 주입을 사용하면 setter 메서드를 public으로 열어둬야한다.
  - 변경하면 안되는 메서드를 열어두는 것은 좋은 행위는 아니다.
  - 결국 setter 로 선언해주는 행위를 한다는 것 자체가 누락시켜 `NPE`를 발생시킬 가능성이 높아짐



### final 키워드

- 생성자 주입을 사용하면 필드에 `final` 키워드를 사용할 수 있다.
- 혹여나 생성자에 값이 설정되지 않더라도 컴파일 시점에 할당하도록 에러를 내준다.

```java
@Component
public class OrderServiceImpl implements OrderService {
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy; 
    
	 @Autowired
	 public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy 
		discountPolicy) {
	 this.memberRepository = memberRepository;
         //discountPolicy가 누락되었기 때문에 컴파일시 에러가 날 것이다.
	 }
	 //...
}
```





### lombok의 `@RequiredArgsConstructor`



#### 기존코드

```java
@Component
public class OrderServiceImpl implements OrderService {
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy; 
    
	 @Autowired
	 public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy 
		discountPolicy) {
	 	this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;         
	 }

}
```

#### `@RequiredArgsConstructor` 적용코드

```java
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
     //생성자 주입을 알아서 해준다.
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;
    
}
```



