> 	객체 생성에 관여하는 생성자가 private를 가지면 객체를 생성할 수 없음


## non-static 멤버인 경우 (인스턴스 메서드)
-  객체생성 후 접근이 가능하다
```java
Cozy cozy = new Cozy();
cozy.getName();
```

## static 멤버인 경우 (클래스 메서드)
-  객체 성성 없이 접근이 가능하다
-  클래스를 사용하는 시점에 static 멤버는 먼저 자동으로 메모리에 로딩됨
```java
Cozy.getName();
```

**static 의 대표적인 예시가 System.out.println이다**
