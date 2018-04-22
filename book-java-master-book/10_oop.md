# 10. 객체지향 즐기기

## 기본형과 참조형의 값 전달하기

### 기본형과 참조형의 값 전달 방법

- Call by value: 값 복사
- Call by referrence: 참조 복사



인수에 객체를 전달했을 때 이를 수정하면 호출자에게도 영향이 미치게 된다.

따라서, 필자는 아래와 같이 한다고 한다.

- 원칙적으로 인수 객체의 수정은 피한다
- 반환값이 void의 경우 인수 객체를 수정해도 좋다.
- 반환값이 void 이외의 경우 인수 객체를 변경해서는 안된다.



### 변경 가능한 클래스의 장점과 단점

불변 객체는 인수로 넘길때 값이 변하지 않을 것이라는 안정감을 가질 수 있다.

그러나, 객체가 메모리에 대량으로 많이 생길 수 있다.

즉, 루프 처리 안에서 불변 객체 클래스의 값이 변경되는 것은 피하는 것이 좋다.



## 객체의 라이프 사이클 파악하기

### 세 종류의 라이프 사이클

- 로컬 변수
  - 블록 내
- 인스턴스 변수
  - 객체의 필드, 상태
- 클래스 변수
  - 클래스의 static 필드 



## 인터페이스와 추상 클래스를 활용하여 설계하기

아래와 같이 **다형성**을 통해 List 인터페이스를 사용한 코드는 그 구현에 관계없이 동일한 '인터페이스'로 사용할 수 있다.

**즉, 동일한 특성을 가진 클래스를 여러개 만들 수 있다.**

```java
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.add(3);
System.out.println(list);
```

```
List<Integer> list = new LinkedList<>();
list.add(1);
list.add(2);
list.add(3);
System.out.println(list);
```



#### 인터페이스와 추상 클래스

- 인터페이스
  - 인스턴스 변수를 가질 수 없다. 상수를 가질 수 있다.
  - 자바 8부터는 인터페이스가 '디폴트 구현' 형태로 메서드의 구현을 가질 수 있다.
- 추상 클래스
  - abstract 메서드를 선언할 수 있다.  추상 클래스 자체는 인스턴스를 생성할 수 없다. 그 외에는 클래스와 같다.



#### # 인터페이스의 성질

- 설계자 관점에서 인터페이스는 '특성'의 정의
- 동일한 특성을 가진 클래스를 여러개 구현체로 만든다.
- 구현자 관점에서 인터페이스는 '클래스에 대한 액세스를 제한하는 제약'
  - 인터페이스를 구현한 클래스에서 인터페이스에서 정의한 클래스 외에 public 메서드를 작성했다 하더라도 외부에서 클래스의 타입을 정확하게 지정하지 않은 상태에서 호출하는 것을 막을 수 있다.



#### # 추상 클래스의 성질

- 설계자 관점에서 추상 클래스는 말 그대로 클래스를 추상화 한 것이다.
  - 여러 클래스에서 동일한 부분을 슈퍼 클래스로 잘라 내어 추상화 해 공통화한 것이다.
- 구현자 관점에서 추상 클래스는 클래스의 양식(form)이다.
  - 공통 부분은 구현되어 있으며, 처리를 바꿀 필요가 있는 부분만 abstract로 되어 있으므로 상속 받은 부분에서는 필요한 부분만 개발하면 된다.



#### # 실제 고려

- 인터페이스는 '정의'에 사용
- 추상 클래스는 '뼈대'나 '공통 처리'에 사용



예를 들면, "네트워크를 통해 서비스를 호출하는 기능"을 구현 할때

- 인터페이스를 사용하지 않으면 서비스를 호출하는 로직이 통신 방식에 의존이 생기게 된다.
- 반면, 통신 처리를 인터페이스화 시키면 이를 호출하는 서비스 로직은 네트워크 통신 방식이 HTTP 인지 SOAP 인지 다른 방법인지 신경쓰지 않아도 된다.
- 어디까지나, 네트워크를 대상으로 하는 서비스일 때 '무엇을 건네면 무엇이 반환되는가'라는 정의만 알면 충분한 것이다.
- 반대로 이것을 추상클래스로 하면 구현이 포함되어 종속성이 강해져서 나중에 수정이 힘들어진다.



또 예를 들어, 유저 등록, 목록 검색, 삭제를 실시하는 인터페이스가 있다고 할 때

```java
public interface UserManagementService {
    void register(User user);
    List<UserDto> list();
    void delete(Integer userId);
}
```

외부의 서비스를 HTTP로 호출하는 클래스는 

```java
public class HttpUserManagementService implements UserManagementService {
    
}
```

데이터에이스에 읽고 쓰는 클래스는

```java
public class DatabaseUserManagementService implements UserManagementService {
    
}
```

라고 작성 할 수 있다.



또한, 공통된 구현을 처리하고 싶다면 아래와 같이 인터페이스와 구현 클래스 사이에 추상 클래스를 둘 수 있다.

```java
public abstract class AbstractUserManagementService implements UserManagementService {
    protected UserDto convertFrom(User user) {
        // User 클래스로부터 UserDto 클래스로 변환 처리
    }
}

public class HttpUserManagementService implements AbstractUserManagementService {
    public List<UserDto> list() {
        // convertFrom()을 사용한 처리
    }
}

public class DatabaseUserManagementService implements AbstractUserManagementService {
    public List<UserDto> list() {
        // convertFrom()을 사용한 처리
    }
}
```



#### # 나쁜 패턴

인터페이스를 만든 후, 공통 처리 등의 구현이 필요해져서 추가 구현을 할 때

인터페이스를 추상클래스로 바꾸는 것은 나쁜 패턴이다.

구현 클래스 간에 의존 관계가 강해져 버려서 나중에 처리의 변경이 어려워 질 수 있기 때문이다.

구현을 추가하고 싶다면 AbstractUserManagementService와 같이 인터페이스는 그대로 하고, 추상 클래스를 그 사이에 넣도록 하는 것이 좋다. 그래야 호출자의 수정이 적어진다.



### 인터페이스의 디폴트 구현

자바 8부터는 인터페이스가 디폴트 구현을 가질 수 있다.

```java
// java.util.List 인터페이스에 추가된 디폴트 구현
public interface List<E> extends Collection<E> {
    default void sort(Comparator<? super E> c) {
        Collections.sort(this, c);
    }
}
```

배경은 이렇다.

자바 8에서는 Stream API가 추가되었는데, 이로 인해 리스트에 인터페이스에 대한 추가 기능들이 많아졌다.

그러나, 자바의 인터페이스의 개념 특성 상 모든 메서드를 구현해야 하기 때문에 리스트 인터페이스를 상속받고 사용하는 모든 클래스에 수정이 생겨버리게 된다. 자바 7 이하의 하위 호환이 깨져버리게 되는 것이다.

따라서, 하위 호환을 위해 디폴트 구현을 추가하게 된 것이다.



### 인터페이스의 static 메서드

```java
public interface Bar {
    String say();
    
    static Bar newInstance(String message) {
        return new DefaultBar(message);
    }
}
```

```java
class DefaultBar implements Bar {
    private String message;
    
    DefaultBar(String message) {
        this.message = message;
    }
    
    public String say() {
        return this.message;
    }
}
```

```java
Bar bar = Bar.newInstance("Hello Bar!");
System.out.println(bar.say());
```



구현체를 반환하는 팩토리 메서드를 인터페이스에 정의할 수 있게 되었다.

> 팩토리 메서드: 객체를 생성해서 반환해주는 메서드