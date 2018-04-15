# 3. 타입 공략하기

## 원시형과 참조형

### 래퍼클래스

원시형은 객체가 아니라 단순한 값이며 그 자신은 메서드를 갖지 않음.

그러나, 프로그램 안에서는 기본형의 값에 대한 조작(문자열과의 상호작용 등)이 필요하게 될 경우가 있음.

따라서, 자바는 원시형을 내포하여 해당 원시형의 값을 조작하는 기능을 마련한 **래퍼 클래스**를 제공함

#### 래퍼 클래스의 대표적인 상수

- SIZE
- BYTES
- MAX_VALUE
- MIN_VALUE

#### 래퍼클래스의 대표적인 메서드

- valueOf(원시형의 값)
  - 원시형으로부터 래퍼 클래스의 객체로 변환
- valueOf(String s)
  - 문자열로부터 래퍼 클래스의 객체로 변환
- valueOf(String s, int radix)
  - 진수를 지정해 문자열로부터 래퍼 클래스의 객체로 변환
- parse(String s)
  - 문자열로부터 원시형의 값으로 변환
- parse(String s, int radix)
  - 진수를 지정해 문자열로부터 원시형의 값으로 변환
- toString(원시형의 값)
  - 기본형에서 문자열로 변환
- toString(원시형의 값, int radix)
  - 진수를 지정해 기본형에서 문자열로 변환

### 래퍼 클래스의 생성

new 를 사용한 생성도 가능하지만 valueOf() 를 사용한 방법을 권함.

**128 ~ 127의 범위라면 사전에 생성된 객체를 이용하므로 new 방식에 비해 객체 생성 비용을 아낄 수 있음**

### 오토박싱과 언박싱

- 원시형에서 래퍼 클래스로의 자동 변환을 오토 박싱


- 래퍼 클래스에서 원시형으로의 변환을 언박싱

아래와 같은 상황이 있을 수 있으니 유의해야 함.

```java
Integer num1 = new Integer(3);
Integer num2 = new Integer(3);

System.out.println(num1 == num2); // false
System.out.println(num1.equals(num2)); // true
```

```java
Integer int1 = 127;
Integer int2= = 127;

System.out.println(int1 == int2); // true
```

```java
Integer int1 = 128;
Integer int2 = 128;

System.out.println(int1 == int2); // false
```

따라서, 저자는 아래와 같이 할 것을 방침으로 한다고 한다.

- 원칙적으로 오토박싱, 언박싱 이용하지 않고 명시적인 변환을 실시
- 파일이나 데이터베이스, HTTP 요청 등을 유지하는 값은 래퍼 클래스로 사용
- 수치 연산에 사용하는 변수는 기본형으로 사용
- 코딩량의 감소에 효과적인 경우에 한하여 오토박싱, 언박싱 사용

## 타입 판정과 객체의 등가성

### 타입판정

instanceof: 좌변의 객체가 우변에 지정한 클래스(또는 계승한 클래스)인지의 여부 리턴

### 객체의 등가성

''객체가 동등한가' 라는 것은 아래와 같이 두 단계로 나뉨

- 객체가 동일 객체다
  - ==
- 객체의 값이 같다
  - .equals()

#### hashCode()

equals 메서드를 구현했다면 한 가지 더 구현해야 하는 것이 바로 hashCode()

hashCode()는 객체 자신의 내용을 나타내는 숫자값(해시값)을 반환한다.

> 해시값이란 객체의 값을 일정의 규칙에 따라 숫자값으로 한 것

**같은 객체 --> 해시값을 가짐.**

**그러나, 동일 해시값 --> 같은 객체 이건 아닐 수도 있음**

> equals()는 값을 하나씩 검증하므로 느림.
>
> 그에 비해 해시값인 경우 단순 숫자 비교라서 빠름.
>
> 때문에, HashMap 또는 HashSet 에서는
>
> - 처음에 해시값으로 객체 비교
> - 해시값이 동일한 경우에 한해서 equals() 비교로 엄격히 판정

## 타입에 관련된 문제 예방하기

### 열거형

```java
// 열거형을 써야 좋은 문제 #1
public static final String COLOR_BLUE = "blue";
public static final String COLOR_YELLOW = "yellow";
public static final String COLOR_RED = "red";

public void processColor(String color) {
    // color에 COLOR_BLUE, COLOR_YELLOW, COLOR_RED 외에 다른 값이 오면 오동작을 일으킬 수도 있음
}
```

```java
// 열거형을 써야 좋은 문제 #2

// 상수 클래스 쪽의 정의
public static final String SELECTED_COLOR = "blue";

// 이용 클래스 쪽의 정의
public String color = SELECTED_COLOR; // 이 코드는 컴파일 시 public String color = "blue"; 이 됨.

// 이 상태에서 상수 클래스 쪽만 수정하고 컴파일 하면 이용 클래스와 불일치 발생.
// 그러나, 한 쪽만 컴파일하는 경우는 거의 없긴 할 듯.
```

### 제네릭스(Generics)

#### 제네릭스를 사용한 클래스의 작성

```java
public class StringStack {
    private List<String> taskList;
    
    public StringStack() {
        taskList = new ArrayList<>();
    }
    
    public boolean push(String task) {
        return taskList.add(task);
    }
    
    public String pop() {
        if (taskList.isEmpty()) {
            return null; // 요소가 하나도 없을 시 null 리턴
        }
        
        return taskList.remove(taskList.size() - 1);
    }
}

// StringStack 사용 예

StringStack strStack = new StringStack();
String strElement = strStack.pop();

strStack.push("A");
strStack.push("B");
strStack.push("C");

strElement = strStack.pop();

if (strElement != null) {
    System.out.println(strElement); // C
}
```

앞서 구현한 StringStack 은 요소가 String 으로 고정되어있다.

제네릭을 이용하면 각 스택 요소를 임의의 타입으로 만들 수 있다.

```java
public class GenericStack<E> {
    private List<E> taskList;
    public GenericStack() {
        taskList = new ArrayList<>();
    }
    public boolean push(E task) {
        return taskList.add(task);
    }
    public E pop() {
        if (taskList.isEmpty()) {
            return null;
        }
        
        return taskList.remove(taskList.size() - 1);
    }
}

// GenericStack 사용 예
GenericStack<String> genericStack = new GenericStack<>();

genericStack.push(true); // true는 문자열 타입 아니므로 컴파일 에러
genericStack.push("A");
genericStack.push("B");
genericStack.push("C");

String genericElement = genericStack.pop();
if (genericElement != null) {
    System.out.println(genericElement); // C
}

GenericStack<Integer> genericIntegerStack = new GenericStack<>();

genericIntegerStack.push(true); // true는 정수 타입 아니므로 컴파일 에러
genericIntegerStack.push(1);
genericIntegerStack.push(2);
genericIntegerStack.push(3);

String genericIntegerElement = genericIntegerStack.pop();
if (genericIntegerElement != null) {
    System.out.println(genericIntegerElement); // 3
}
```



제네릭은 메서드에 정의할 수도 있다.

```java
public class GenericStackUtil {
    public static <T> genericStackUtil<T> as(List<T> list) {
        GenericSTack<T> stack = new GenericStack<>();
        list.forEach(stack::push);
        return stack;
    }
}

// GenericStackUtil 사용 예
List<String> stringList = new ArrayList<>();
stringList.add("A");
stringList.add("B");

GenericStack<String> stack = GenericStackUtil.as(stringList);
```



