# 6. 예외 공략하기

## 예외의 기본

작성한 프로그램에 기대하지 않은 동작이 발생한 것을 "예외"라고 한다.

다른 언어에서도 볼 수 있지만, 자바에서도 이러한 예외를 인식하고 대응할 수 있는 구조가 준비되어있다.



#### 예외의 종류

- Checked Exception(Compiletime Exception)
  - 주로 프로그램 작성 시에 예상할 수 예상할 수 있는 비정상 상태를 통지하기 위해 사용
  - 검사 예외를 사용하면 예상 되는 비정상 상태에 대응하는 처리가 있는지 컴파일 시에 체크할 수 있어서 견고한 애플리케이션 작성이 가능
  - SQLException, IOException
- Unchecked Exception(Runtime Exception)
  - 주로 프로그램 작성 시에 예상할 수 없는 오류를 통지하기 위해 사용
  - 포착하지 않는 경우, 무조건 호출원에서 발생
  - 반드시 catch할 필요도 없고 throw 할 필요도 없다. 아무것도 안하면 자동적으로 호출원으로 전파된다. 결국 아무데서도 catch 되지 않은 결과는 스레드의 종료로 이루어진다. 스레드를 시작한 것은 JVM이다. 그러므로 해당 스레드가 메인 스레드라면 애플리케이션 자체가 종료해버린다.
  - ArithMeticException, NullPointerException
- Error
  - 예외와 달리 시스템의 동작을 계속할 수 없는 '치명적' 오류
  - Error는 프로그램 레벨에서 포착해야하는 것이 아님
  - Error는 Runtime Exception 과 같이 catch할 필요도 throw 하지 않아도 된다는 것이 비슷하게 보이지만, 
    **Error는 포착해야할 것이 아니라는 점에서 다르다**
  - OutOfMemoryError



## 예외 처리에서 혼란에 빠지지 않기 위한 포인트

### 오류 코드를 return하지 않기

예외 매커니즘이 없는 언어라면 오류 코드를 return하는 코드가 사용되는 케이스가 많을 수 있다.

그러나, 자바에서는 예외 매커니즘이 언어의 사양에 제공되므로 오류가 발생하면 예외를 발생시키고, 정상적으로 처리가 종료하면 그 객체를 반환값으로 하면 된다.

**단, isXxx 메서드 같이 boolean 타입을 리턴하는 경우는 예외를 throw 하지 않도록 하는 것이 좋다.**

### 예외를 제거하지 않기

catch만 하고, 아무것도 안하면 안된다.

- 로그출력을 잊지 말라.
  - 콜 스택 트레이스 챙기는 것 잊지말기
- 처리를 계속할지 판단하라.
  - catch 블록까지 처리가 넘어간 시점에서 객체 초기화가 안되어있지는 않은지

### 공포의 throws Exception 감염

'복수의 예외'가 발생하더라도 예외의 선언이나 포착이 귀찮기 때문이라서 메서드의 시그내처에 throws Exception 이라고 기술하는 경우가 있다.

이것은 나중에 비극이 될 수도 있다. 다음과 같은 공포의 감염을 일으킨다.

#### 호출하는 곳에서 Exception을 포착해야 한다

호출하는 곳에서 catch하지 않으면 상위 호출원은 왜 예외를 호출해야하는지 모르게 될 수 있다.



#### 도중에 IOException 등 구체적인 예외가 발생한다 해도 Exception에 휩쓸려 버린다

Exception 클래스는 모든 예외의 부모 클래스이므로 IOException 등의 예외가 발생해도 throws Exception 으로 선언이 가능하다.

이렇게 되면 호출하는 쪽은 Exception이 실제로는 IOException의 예외였다 해도 그 종류를 이해해서 적절한 코드를 작성하기 어려울 수 있다.



#### 도중에 RuntimeException이 발생해도 Exception에 휩쓸려 버린다

- Runtime Exception으로 처리해도 상관없을 경우라도 Checked Exception 과 동일한 레벨로 불필요하게 체크하게 됨
  - 본래 포착해서는 안될 예외도 의도치 않게 포착할 수도 있다
- Checked Exception이라고 생각해서 처리를 하고 있으면 실행 시 예외가 발생한 경우에 잘못된 처리를 실시할 지도 모름
- 만약 호출원에서 포착해서 상위로 넘기지 않으면 실제로 처리가 필요한 실행이더라도 예외를 놓칠 수 있음

여기서, 포인트는 Checked 인지 Unchecked 인지 나눠서 처리해야 하는 경우를 나눠서 고려하자는 것이다.

##### # 그래도 Exception 을 catch 해야 할 때는 언제인가?

- 호출한 곳(Callee)이 throws Exception 한 경우
  - 오래된 라이브러리나 다른 사람이 작성한 코드에서 Exception 을 던지는 경우가 있다.
  - 이런 경우에는 독자적인 예외로 랩핑해서 throw 하는 처리를 적용할 수 있다. 
- 무언가 문제가 발생하더라도 처리를 계속할 필요가 있는 경우
  - 쓰레드 풀에 반환되어야 하는 작업 쓰레드 같은 경우를 예로 들 수 있다.
  - 작업 쓰레드가 쓰레드 풀에 반환되는 것이 보장되기 위해서는 작업 쓰레드는 도중에 어떤 문제가 발생하더라도 처리가 종료되서는 안된다.
  - 그러므로 이러한 경우에는 쓰레드 처리의 가장 바깥 쪽에서 Exception을 포착하여 쓰레드 수행 중 문제가 생기더라도 쓰레드 풀에는 반환이 될 수 있도록 처리할 수 있다.

### 어느 계층에서 예외를 포착해서 처리해야 하는가?

예외를 취급할 때 catch 할지, 상위로 throw 시킬지 판단할 필요가 있다. 단순히, 만드는 사람별로 기분별로 제각각으로 처리하면 통제가 안된다.

그럼, 어떤 기준으로 처리하는 것이 적절할까? 기본적으로 아래 두가지로 나눌 수 있다.

- 예외가 발생하는 또는 예외가 발생할 가능성이 있는 장소
  - 이 경우에는 개별적으로 판단하려고 하면 대상이 많아지게 되고, 중복이 많아져서 동일한 변경을 여러 군데 해줘야할 수도 있다
- 예외 처리의 흐름을 판단하는 장소
  - 처리를 계속할지 아니면 중지할지를 이 부분에서 판단하는 방식이다
  - 프로그램 규모가 커질수록 가능한 한 상위의 호출 계층에서 예외를 포착하는 식으로 하는 것이 좋다

### 예외의 트렌드

#### # Checked Exception 보다도 Runtime Exception을 사용한다

프레임워크에서 제공하는 독자적인 예외는 대부분 Runtime Exception이 많다.

예외 처리 자체를 간단하게 하는 목적과 예외를 일일이 개별로직으로 처리하지 않아도 되므로 비지니스 로직의 코드도 간단하게 된다는 장점이 있다.

자바 8에서는 Stream API를 처리하기 위해 UncheckedIOException이 추가되었고, 프레임워크에 따라 독자적인 예외로 SQLRuntimeExeption 등의 예외 래퍼가 만들어질 수 있음.



#### # 람다식 안에서 발생한 예외의 취급

람다 안에서 처리중에 예외가 발생할 수 있다.

그것이 Checked Exception이라면 컴파일 에러가 난다. 그러므로, catch를 해줘야 한다.



만약, RuntimeException이라면 람다를 호출한 처리로 예외가 throw 된다.

그러나, parallelStream 메서드를 사용해서 병렬 스트림 처리를 한 경우, 모든 예외를 수취할 수 있는 것이 아니다.

**그러므로, 람다 안에서 모든 예외를 처리하는 것이 좋다.**

```java
try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(W_FILENAME))) {
    lines.forEach(s -> {
        try {
            writer.write(s + '\n');
        } catch(IOException ex) {
            System.out.println("IOException in lamda.");
            ex.printStackTrace(System.out);
            throw new UncheckedIOException(ex);
        }
    });
} catch (IOException | RuntimeException ex) {
    System.out.println("Exception in Writer-try.");
    ex.printStackTrace(System.out);
    throw ex;
}
```



#### # Optional 클래스 도입에 의한 장점

Optional 클래스가 도입된 이유는 자바가 NullPointerException을 발생하기 쉬운 언어인 점을 개선하기 위해서다.

java.util.Map	클래스만 보더라도 get 메서드가 null을 리턴할 수 있다.

이럴 경우, 개발자는 의식적으로 null을 체크해야만 NPE를 막을 수 있다.



##### ## Optional 주요 메서드

- Optional<T> optional  = Optional.of(T value)
  - 값을 갖는 Optional 객체를 반환
- Optional<T> optional = Optional.empty()
  - 값이 없는 Optional 객체를 반환
- T value = optional.get()
  - 값을 반환한다.
  - 값을 갖지 않는 Optional인 경우에는 예외가 발생하므로 isPresent 메서드로 확인한 후 사용 가능
- T value = optional.get()
  - 값을 가지는 경우는 그 값을, 값을 갖지 않는 경우는 인수로 지정된 값을 반환
- optional.isPresent()
  - 값을 갖는 경우는 true, 값이 없으면 false 리턴
- optional.ifPresent(value -> {...})
  - 값을 갖는 경우만 람다식의 처리를 실시



##### ## 스택 클래스에 Optional 도입 해보기

```java
// 옵셔널을 도입한 스택 클래스
public class OptionalStack<E> {
    private List<E> taskList;
    
    public optionalStack() {
        this.taskList = new ArrayList<>();
    }
    
    public boolean push(E task) {
        return this.taskList.add(task);
    }
    
    public Optional<E> pop() {
        if (this.taskList.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(this.taskList.remove(this.taskList.size() -1));
    }
}
```



```java
// OptionalStack 사용 예
OptionalStack<String> optionalStack = new OptionalStack<>();
Optional<String> optional  = optionalStack.pop(); // 아직 push하지 않았으므로 값을 갖고 있지 않음

String optionalElement = optional.orElse("empty"); // optional 값이 존재하지 않으면 "empty" 반환
System.out.println(optionalElement); // "empty"

optionalStack.push("Scala");
optionalStack.push("Groovy");
optionalStack.push("Java");

optional = optionalStack.pop();
if (optional.isPresent()) { // optional의 값이 존재하는 경우만 진입
    System.out.println(optional.get()); // Java
}

optional = optionalStack.pop();
optional.ifPresent(System.out::println);	// Groovy
```

여기서 핵심은,

**Optional 객체를 취득한 후에 개발자는 Optional 객체가 값을 갖고 있는지 안 갖고 있는지 명확하게 의식해서 코드를 짜야 한다는 것이다**

의미상, null 체크를 하는 것과 그다지 다르지는 않지만 Optional을 사용하면 API 이용자 스스로가 의식해서 null 체크에 신경쓰게 하는 것이 아니라 강제적으로 값을 갖는 경우, 안 갖는 경우를 고려하게 만든다.

또한, orElse 같은 메서드를 사용하면 더욱 프로그램이 간단해진다.