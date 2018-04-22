# 5. 스트림과 처리 제대로 사용하기

## Stream API를 사용하기 위한 기본

### Stream API로 컬렉션의 조작은 어떻게 변하는가?

Java 8에서는 대량 데이터를 연속 처리 하는 '스트림 처리'를 효율적으로 기술하기 위한 방법이 새로 도입되었다.

**Stream API에서는 How가 아니라 What을 기술한다.**

- 개발의 처리(How)를 열거하지 않고,
- 개발의 목적(What)을 열거하는 식으로 코드.

### 람다식 작성법 마스터하기

람다식은 메서드의 인수 등에 처리 그 자체를 넘기는 것이 가능한 강력한 기법.

Java 8 에서는 구현해야할 메서드가 하나밖에 없는 인터페이스를 '함수형 인터페이스'라는 이름으로 취급함.

람다식은 이 함수형 인터페이스 대신 사용할 수 있는 것임.

```java
Student[] students = {
    new Student("Ken", 100),
    new Student("Shin", 60),
    new Student("Kim", 80)
}

Arrays.sort(students, (Student o1, Student o2) -> 
            Integer.compare(o2.getScore(), o1.getScore()));

Arrays.stream(students).forEach(s ->
                                System.out.println(s.getName() + ":" + s.getScore())
                               );
```

#### 인수 부분의 생략

```java
// 인수 타입의 생략
(student1, student2) -> {
    return Intger.compare(student1.getScore(), student2.getScore());
}

// 인수가 하나밖에 없으면 인수 쪽 소괄호도 생략 가능
Consumer<String> consumer1 =
    (String s) -> {System.out.println(s);}
Consumer<String> consumer1 =
    String s -> {System.out.println(s);}

// 인수가 없으면 소괄호만 쓴다.
Runnable runnable1 =
    () -> {System.out.println("람다식의 테스트다.");}
```

#### 처리 부분의 생략

```java
// 처리 명령어가 한 행인 경우 return과 처리부를 나타내는 중괄호 생략 가능
(student1, student2) -> {
    return Integer.compare(student1.getScore(), student2.getScore());
}

(student1, student2) -> Integer.compare(student1.getScore(), student2.getScore());
```





### 메서드 참조

Java 8에서는 이미 준비되어 있는 메서드 그 자체도 대입할 수 있음.

```java
List<String> list = Arrays.asList("Xxx", "Yyyyy", "Zzzz");
list.forEach(System.out:println);
```

메서드 참조는 대입할 곳의 함수형 인터페이스에 있는 인수의 수와 타입이 일치하면 메서드를 대입할 수 있다.

```java
List<String> list = Arrays.asList("Xxx", "Yyyyy", "Zzzz");
list.forEach(str -> System.out::println(str))
```



##### 메서드 참조의 문법

- 인스턴스의 메서드를 참조
  - {인스턴스명}::{메서드명}
- 자기 자신의 인스턴스의 메서드를 참조
  - this::{메서드명}
- static 메서드를 참조
  - {클래스명}::{메서드명}



## Stream 작성하기

### 배열로부터 Stream 작성하기

```java
String[] array = {"Haeun", "Shin", "Shion"};
Stream<String> stream = Arrays.stream(array);
stream.forEach(System.out::println);

// of 메서드를 이용해서 Stream 인스턴스를 작성할 수 있다.
Stream<String> stream = Stream.of("Haeun", "Shin", "Shion");
stream.forEach(System.out::println);
```

### Map으로부터 Stream 생성하기

Java 8dptjsms Map을 적절하게 처리하기 위한 Stream 클래스는 준비되어 있지 않다.

Map에 대한 Stream 조작을 원할 경우, Map의 entrySet 메서드를 통해 Set 을 구하고, Set 메소드의 stream 메서드를 호출하면 된다.

```java
Map<String, String> map = new HashMap<>();
map.put("1", "Haeun");
map.put("2", "Shin");
map.put("3", "Shion");

Stream<EntrySet<String, String>> stream = map.entrySet().stream();
stream.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
```

### 숫자 범위로부터 Stream 작성하기

```java
IntStream stream = IntStream.range(1, 5); // range()는 마지막 값 포함 안함.
Stream.forEach(System.out::print); // 1234
```

```java
IntStream stream = IntStream.rangeClosed(1, 5); // rangeClosed()는 마지막 값 포함 함.
Stream.forEach(System.out::print); // 12345
```

## Stream에 대한 '중간 작업'

중간 작업이므로 인수는 처리이고(함수. 즉, 람다 표현식 혹은 메서드 참조), 반환값은 Stream이 된다.



### 요소를 치환하는 중간 작업 

- map()
  - 처리 내용: 요소를 별도의 값으로 치환
  - 인수: Function(인수가 Stream 대상 객체, 반환값이 임의의 타입)
  - 반환값: Stream<반환 후의 타입>
- mapToInt(), mapToDouble(), mapToLong()
  - 처리 내용: 요소를 int or double, long 값으로 치환한다.
  - 인수: Fucntion(인수가 Stream 대상 객체, 반환값이 int or double or long)
  - 반환값: IntStream, DoubleStream, LongStream
- flatMap()
  - 처리 내용: 요소의 Stream을 결합한다.
  - 인수: Function(인수가 Stream 대상 객체, 반환값이 Stream)
  - 반환값: Stream<반환 후의 타입>

```java
List<Student> students = new ArrayList<>();
students.add(new Student("Ken", 100));
students.add(new Student("Shin", 60));
students.add(new Student("Tommy", 80));

Stream<Intger> stream = students.stream()
    .map(s -> s.getScore());
stream.forEach(System.out::println);

students.stream()
    .map(s -> s.getScore())
    .forEach(System.out::println);
```



#### flatMap() 심화

stream과 stream 을 결합해서 map 한다.

```java
// map() 만 사용하는 경우
List<Student> allStudents = new ArrayList<>();
groups.stream().forEach(g -> allStudents.addAll(g.getStudents()));
allStudents.stream()
    .sorted((s1, s2) -> s2.getScore() - s1.getScore())
    .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));
```

```java
// flatMap() 사용하는 경우
groups.stream()
    .flatMap(g -> g.getStudents())
    .sorted((s1, s2) -> s2.getScore() - s1.getScore())
    .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));
```



### 요소를 걸러내는 중간 작업

```java
List<Studen> students = new ArrayList<>();
students.add(new Student("Haeun", 100));
students.add(new Student("Shin", 60));
students.add(new Student("Shion", 80));

students.stream()
    .filter(s -> s.getScore() > 70) // filter 는 조건에 일치하는 요소들만 걸러낸다.
    .forEach(s -> System.out.println(s.getName));

students.stream().stream()
    .limit(2) // limit은 주어진 수만큼 한정한다.
    .forEach(s -> System.out.println(s.getName()));
```

```java
List<String> strings = new ArrayList<>();
strings.add("Haeun");
strings.add("Shin");
strings.add("Shion");
strings.add("Yongho");
strings.add("Haeun");
strings.add("Shin");

strings.stream()
    .distinct() // distinct는 유니크한 요소를 추출한다.
    .forEach(System.out::println)
```



### 요소를 정렬하는 중간 작업

- sorted()
  - 0보다 작은 값(음수)인 경우 -> 인수1의 객체가 앞으로 간다.
  - 0보다 큰 값(양수)인 경우 -> 인수2의 객체가 앞으로 간다.
  - 0인 경우 -> 나열 순서가 변하지 않는다.

```java
List<Students> students = new ArrayList<>();
students.add(new Student("Ken", 100));
students.add(new Student("Shin", 60));
students.add(new Student("Jung", 80));

students.stream()
    .sorted((s1, s2) -> s2.getScore() - s1.getScore())
    .forEach(s -> System.out.println(s.getName() + " " + s.getScore()));
```

## Stream에 대한 '종료 작업'

### 반복 처리를 실시

- forEach()

### 결과를 정리해서 추출

- collect()

  ```java
  List<String> list = Arrays.asList("HaeunJung", "Shin", "ShionJung");

  List<String> newList = list.stream()
      .filter(p -> p.length() > 5)
      .collect(Collectors.toList());
  ```

  - 처리 내용: 요소를 스캐닝하여 결과를 작성

  - 인수: Collector

  - 반환값: 인수에 넘겨진 처리(함수)에 따라 달라짐

    - Collectors.toList()

    - Collectors.toSet()

    - Collectors.joining()

      - 하나의 문자열로 결합

    - Collectors.joining(String s)

      - 전체 요소를 s로 넘겨받은 구분 문자를 사용해 결합

    - **Collectors.groupingBy(Function T)**

      - **요소를 그룹으로 나눔.**
      - **인수는 값을 집계하는 함수**
      - **반환은 요소를 집계한 Map**

      ```java
      List<Students> students = new ArrayList<>();
      students.add(new Student("Ken", 100));
      students.add(new Student("Shin", 60));
      students.add(new Student("Yoon", 80));
      students.add(new Student("Jung", 100));

      Map<Integer, List<Student>> map = students.stream()
          .collect(Collectors.groupingBy(Student::getScore));

      List<Student> pefects = map.get(100);
      pefects.forEach(s -> System.out.println(s.getName()));
      ```


- toArray()
  - 처리 내용: 모든 요소를 배열로 변환
  - 인수: 없음
  - 반환값: OptionalObject[]
- reduce()
  - 처리 내용: 값을 집약한다.
  - 인수: BinaryOperator(인수가 Stream 대상 객체)
  - 반환값: Optional

### 결과를 하나만 추출

- findFirst()
  - 가장 먼저 찾은 것 리턴
- findAny()
  - 어떤 한 요소 리턴
- min()
- max()

### 집계 처리를 실시

- count()
- min()
- max()
- sum()
- average()

## Stream API를 사용하기 위한 포인트

### Stream에서 가장 많이 쓰는 것은 map, filter, collect 이다.

```java
List<String> list = Arrays.asList("AAA", "BB", "CCCC");

List<String> newList = list.stream()
    .filter(p -> p.length() > 3)
    .map(p -> "[" + p + "]")
    .collect(Collectors.toList());

newList.forEach(System.out::println);

//CCCC
```



### n번 반복하는 IntStream

```java
// StringBuilder 사용
int count = 5;

StringBuilder builder = new StringBuilder();
for (int i = 0; i < count; i++) {
    if (i != 0) {
        builder.append(", ");
    }
    builder.append("?");
}
System.out.println(builder.toString());
```

```java
// 배열을 만들어 두고 사용
int count = 5;

String[] array = new String[count];
Arrays.fill(array, "?");
String query = StringUtils.join(array, ", ");
System.out.println(query);
```

```java
// Stream 사용
int count = 5;
String query = IntStream.range(0, count)
    .mapToObj(i -> "?")
    .collect(Collectos.joining(", "));
System.out.println(query);    
```



### List나 Map에 대한 효율적인 처리

#### List에서 모든 요소에 함수를 적용하는 메서드

- removeIf
  - 인수로 주어진 함수의 결과 값이 true를 반환하는 모든 요소 제거
- replaceAll
  - 인수로 주어진 함수의 결과로 List의 모든 요소를 치환

```java
List<String> list = new ArrayList<>();
list.add("HaeunJung");
list.add("IYOU");
list.add("ShionJung");

list.removeIf(s -> s.length() <= 5);
list.replaceAll(s -> s.toUpperCase());
list.forEach(System.out::println);

//HAEUNJUNG
//SHIONJUNG
```



#### Map에서 모든 요소에 함수를 적용하는 메서드

- compute
  - 처리 내용: 인수로 주어진 함수의 결과를 MapDP WOTJFWJD
  - 인수: 인수1이 키, 인수2가 BiFunction
- computeIfPresent
  - 처리 내용: 키가 있을 때만 인수로 주어진 함수의 결과를 Map에 재설정
- computeIfAbsent
  - 처리 내용: 키가 없을 때만 인수로 주어진 함수의 결과를 Map에 재설정

## Stream API를 사용하여 List 초기화하기

of() 와 boxed() 그리고 collect를 이용해서 적절하게 List를 만들어낸다.

```java
List<Integer> integerList = IntStream.of(1, 2, 3, 4, 5).boxed()
    .collect(Collectors.toList());
```

```java
List<String> stringList = Stream.of("A", "B", "C").collect(Collectors.toList());
```

```java
// 1~99
List<Integer> integerList = IntStream.range(1, 100).boxed()
    .collectI(Colletors.toList());
```

```java
// 1~100
List<Integer> integerList = IntStream.rangeClosed(1, 100).boxed()
    .collect(Collectors.toList());
```

Stream을 통해서 배열도 만들어 낼 수 있다.

```java
Integer[] integerArray = IntStream.of(1, 2, 3, 4, 5).boxed()
    .toArray(n -> new Integer[n]);
```

