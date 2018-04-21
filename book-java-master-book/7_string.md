# 7. 문자열 공략하기

## 문자 코드 변환하기

문자를 컴퓨터에서 처리하기 위해서 문자에 정해진 숫자를 할당한다.

이렇게 할당된 숫자를 문자 코드(Character Set)라 한다.

윈도우는 MS949, 리눅스에서 UTF-8이나 EUC-KR 을 표준으로 사용중이다.



자바로 만들어진 시스템만을 고려하면, 자바가 내부에서 어떤 문자 코드를 사용하고 있는지 의식할 필요없다.

그러나, 자바가 아닌 언어로 만들어진 시스템과의 연계를 고려하려면 문자가 깨지지 않도록 적절한 Charset의 변환이 필요하다.



### 자바는 어떻게 문자 코드를 이용하는가?

자바는 내부적으로 UTF-16으로 인코딩된 UniCode를 사용하여 문자열을 보관한다.

String 클래스는 내부적으로 char 배열을 보관하므로 char도 Unicode(UTF-16) Charset을 쓴다.



### 임의의 문자 코드로부터 자바 문자로 변환하기

먼저, 바이트 배열을 얻고 이를 String 클래스 생성자를 이용해 문자열로 만든다.



### 문자 깨짐의 원인과 대책

한글을 취급한다면 문자 깨짐의 문제를 피해갈 수 없다. 크게 두 가지 문제가 있다.



#### # 개발 중에는 문제가 없었는데 실제 운영 환경에서는 문자 깨짐이 발생했다.

이런 경우는 대부분 디폴트 <u>인코딩의 차이</u> 때문이라고 볼 수 있다.

> 예를 들면, 개발 환경은 윈도우라 MS949 였고, 운영 환경인 리눅스에서는 UTF-8 



디폴트 인코딩을 사용하는 클래스나 메서드로는 아래와 같은 것들이 있다.

- String 생성자(인수 없음)
- String 클래스의 getBytes(인수 없음)
- FileReader 클래스
- FileWriter 클래스
- InputStreamReader 생성자(인수 없음)
- OutputStreamWriter 생성자(인수 없음)



다음과 같이 하면 개발 환경과 운영 환경에서 결과 달라지는 부분이 없을 수 있게 할 수 있다.

- FileReader 클래스와 FileWriter 클래스는 사용하지 않고, FileInputStream 클래스와 InputStreamReader 클래스로 대체
- 그 이외의 메서드나 생성자는 반드시 인코딩과 Charset을 지정



#### # 서로게이트 페어를 어떻게 취급할까

UTF-16은 서로게이트 페어라는 사양이 있다.

> Surrogate Pair란 여러 문자(16 비트 부호)로 한 문자를 표현하는 형식.
>
> 자바의 세계에서는 2개의 char로 하나의 문자를 표현하는 경우가 있다.



문제는 이게 좀 지저분 하다.

- 일부 문자는 char 2개, 일부는 char 1개와 같이 문자에 따라 필요한 char의 갯수가 다르다.



그러므로 예를 들어 문자 수를 계산해서 제한하는 처리의 경우 이 서로게이트 페어에 대응 할 필요가 생긴다.

Character 클래스의 isLowSurrogate, isHighSurrogate를 사용하면 그 문자가 서로게이트 페어인지의 여부를 확인할 수 있다.

```java
char[] chars = str.toArray();
for (char c: chars) {
    if (Character.isLowSurrogate(c) || Character.isHighSurrogate(c)) {
        System.out.println("서로게이트 페어가 포함되어 있는 문자열");
        return true;
    }
}

System.out(println("서로 게이트가 포함되어 있지 않는 문자열"));
return false;
```



서로게이트 페어를 허용해야 하는 경우는 문자 수를 적절하게 처리해야 한다.

고려해야 한다면 length 보다는 String 클래스의 codePointCount 메서드를 사용해야 한다.

> String string = "코알라:koala:";
>
> System.out.println(str.length()); // 5
>
> System.out.println(str.codePointCount(0, str.length())); // 4



### String 클래스의 intern 메서드로 같은 문자열 찾기

 String 클래스는 불변 객체다. 

동일 문자열을 여러 번 생성하면 그때마다 객체가 생성되서 메모리가 부족해지는 건 아니다.

String 클래스의 객체는 JVM이 관리하고, 같은 문자열을 작성하는 경우, 동일 객체를 참조하게 한다.

이것을 개발자가 명시적으로 하는 것이 intern 메서드다.



```java
String aaa = "aaa";
String aa = "aa";
String a = "a";

System.out.println(aaa.equals(aa + a)); // 값이 같으므로 true
System.out.println(aaa == (aa + a)); // aa + a는 새로 생성된 객체이므로 false
System.out.println(aaa == (aa + a).intern()); // intern을 통해 동일 문자열 객체를 취득하므로 true
```

