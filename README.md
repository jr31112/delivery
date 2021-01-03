# 다형성

## 역할과 구현을 분리

* 역할과 구현으로 구분하면 단순해지고, 유연해지며 변경도 편리해진다.
* 클라이언트는 대상의 역할(인터페이스)만 알면됨
* 클라이언트는 구현 대상의 내부 구조를 몰라도 됨
* 클라이언트는 구현 대상의 내부 구조가 변경되도 영향을 받지 않음
* 클라이언트는 구현 대상 자체를 변경해도 영향을 받지 않음

## 자바에서 역할 구현 분리

* 자바의 다형성을 활용(interface), 물론 상속도 가능하지만 interface가 더 나음
    * 역할 : 인터페이스
    * 구현 : 인터페이스를 구현한 클래스, 구현 객체
* 객체를 설계할 때 역할과 구현을 명확히 분리
* 객체 설계시 역할을 먼저 부여하고, 그 역할을 수행하는 구현 객체 만듬

### 장단점

* 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경 가능
* 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경
* 인터페이스 자체가 변한다면 클라이언트 서버 모두에 큰 변경이 발생



# SOLID 5원칙

> SRP 단일 책임 원칙
>
> OCP 개방 폐쇄 원칙
>
> LSP 리스코프 치환 원칙
>
> ISP 인터페이스 분리 원칙
>
> DIP 의존관계 역전 원칙

객체 지향의 핵심은 다형성

다형성 만으로는 쉽게 부품을 갈아 끼우듯 개발할 수 없으며 구현 객체를 변경할 때 클라이언트 코드가 함께 변형된다 -> OCP, DIP 위배

## SRP 단일 책임 원칙

한 클래스는 하나의 책임만 가져야 한다.

변경이 있을 때 파급 효과가 작으면 단일 책임 원칙을 잘 지킨것

## OCP 개방 폐쇄 원칙

소프트웨어 요소는 확장에서는 열려 있으나 변경에는 닫혀 있어야 한다.

다형성을 활용해서 원칙을 지키고자 노력해야함

인터페이스를 구현한 새로운 클래스를 하나 만들어 새로운 기능을 구현

구현 객체를 변경하려면 클라이언트 코드를 변경 -> 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요!



## LSP 리스코프 치환 원칙

프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 잇어야 한다.

하위 클래스는 인터페이스 규약을 지켜야 한다는 것 -> 다형성을 지원하기 위한 원칙

단순히 컴파일이 아니라 규약을 지켜서 원래 설계한 기능을 수행할 수 있도록 해야함



## ISP 인터페이스 분리 원칙

특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 보다 나음

인터페이스를 분리하면 어떤 클라이언트가 변해도 다른 클라이언트에 영향을 주지 않는다.

인터페이스가 명확해 지고 대체 가능성이 높아진다.



## DIP 의존관계 역전 원칙

추상화에 의존하며 구체화에 의존하지 않는다.

역할(추상화)에 의존하여 구현체의 변경을 용이하게 수행하게 해야한다.



## 스프링 -> 객체 지향 설계

인터페이스를 도입하면 추상화란 비용이 발생 -> 확장 가능성이 없다면 구체화된 클래스를 직접 사용하고 향후 필요할때 리팩토링해서 인터페이스를 도입.

# Member, Order Serivce 개발
작성하는 코드에 대해서 



# 지금 까지 코드의 문제점

## DIP문제

![image-20210103143500957](./dist/DIP위반.jpg)

코드상으로 `OrderServiceImpl`이 `DiscountPolicy`를 의존하는 것이 아니라 `FixDiscountPolicy`, `RateDiscountPolicy`에 의존하여 구체(구현) 클래스를 의존하고 있다. -> DIP문제

또한, 다음과 같이 코드가 구성될 경우 null point exception이 발생한다.

```java
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```

이 문제를 해결하려면 누군가가 클라이언트인 `OrderServiceImpl` 에 `DiscountPolicy` 의 구현 객체를 대신 생성하고 주입해주어야 한다.



## AppConfig 를 활용한 해결

애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 이용하여 관심사를 분리하고 주입하게 한다.

생성자 주입 방법을 이용해 해결

* `MemberRepository` 인터페이스만 의존한다.
* `MemberServiceImpl` 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
* `MemberServiceImpl` 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(`AppConfig`)에서 결정된다.
* `MemberServiceImpl` 은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

```java
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    
    public MemberServiceImpl(MemberRepository memberRepository) {
   		this.memberRepository = memberRepository;
    }
    
    public void join(Member member) {
    	memberRepository.save(member);
    }
    
    public Member findMember(Long memberId) {
    	return memberRepository.findById(memberId);
    }
}
```

![image-20210103174151577](./dist/의존성 주입.jpg)

* 객체의 생성과 연결은 `AppConfig` 가 담당한다.
* DIP 완성: `MemberServiceImpl` 은 `MemberRepository` 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
* 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
* `appConfig` 객체는 `memoryMemberRepository` 객체를 생성하고 그 참조값
* `MemberServiceImpl`을 생성하면서 생성자로 전달한다.
* 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 `DI`(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.



* `AppConfig`는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
  * `MemberServiceImpl`
  * `MemoryMemberRepository`
  * `OrderServiceImpl`
  * `FixDiscountPolicy`
* `AppConfig`는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
  * `MemberServiceImpl` -> `MemoryMemberRepository`
  * `OrderServiceImpl` -> `MemoryMemberRepository` , `FixDiscountPolicy`



## 리펙토링

중복 코드를 제거하고 역할에 따른 구현을 하기 위해 진행

아래 그림의 모든 모습을 한눈에 보여줘야 하며 각 기능에 대해 중복이 없어야함.

![image-20210103175923851](./dist/AppConfig_Refactoring.jpg)