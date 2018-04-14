# 5장 참조 타입

##### 이런걸 배운다.

- 객체로 작업하기
- 배열 생성하고 조작하기
- 자바스크립트의 데이터 타입 이해
- 원시 데이터 및 원시 래퍼로 작업하기

##### 참조 타입 용어 개념

- ECMAScript에서 참조 타입 != 클래스
- 데이터와 기능을 그룹으로 묶는 구조
- 참조 값(객체)이란 '참조 타입'의 인스턴스

## Object 타입

- Object의 인스턴스 생성방법 #1 - new 연산자

  ```javascript
  var person = new Object(); // var person = {}; 와 동일
  person.name = "Nicholas";
  person.age = 29;
  ```

- Object의 인스턴스 생성방법 #2 - 객체 리터럴 표기

  ```javascript
  var person = {
      name : "Nicholas",
      age : 29
  };
  ```

- 객체 리터럴이 더 선호되는 표기법이다

  ```javascript
  function displayInfo(args) {
      var output = "";
      
      if (typeof args.name == "string") {
          output += "Name: " + args.name + "\n";
      }
      
      if (typeof args.age == "number") {
          output += "Age: " + args.age + "\n";
      }
      
      alert(output);
  }

  displayInfo({
     name: "Nicholas",
      age: 29
  });

  displayInfo({
     name: "Greg" 
  });
  ```

- 점 표기범은 대괄호 표기법으로 쓸 수도 있다

  ```javascript
  alert(person["name"]);
  alert(person.name)
  ```

## Array 타입

- ECMAScript 에서의 배열은 각 배열 슬롯에 어떤 타입이던 넣을 수 있다

- 배열의 크기는 동적으로 조절된다

- 배열 생성 방법 #1 - Array 생성자 사용

  ```javascript
  var colors = new Array();
  var colors = new Array(20); // 크기가 20인 배열, length 프로퍼티가 20인 배열 생성
  var colors = new Array("red", "blue", "grren");
  var colors = Array(3); // Array 생성자 사용할 시 new 생략 가능
  ```

- 배열 생성 방법 #2 - 배열 리터럴

  ```javascript
  var colors = ["red", "blue", "grren"];
  var names = [];
  var values = [1,2,]; // 데이터가 두 개 또는 세 개 들어있는 배열을 생성. 이렇게 하지말것.
  var options = [,,,,,]; // 데이터가 5개 또는 6개 들어 있는 배열을 생성. 이렇게 하지말것.
  ```

  쉼표 사이에 값을 넣지 않으면 undefined가 채워지는데, 이는 Array 생성자에 숫자를 넘겨 호출한 것과 마찬가지 결과

- length 프로퍼티

  ```javascript
  var colors = ["red", "blue", "grren"]; // length 3
  var names = []; // length 0
  ```

  ```javascript
  // length 는 읽기 전용이 아니다.
  var colors = ["red", "blue", "grren"];
  colors.length = 2; // 세번째 요소 잘림
  alert(colors[2]); // undefined
  ```

  ```javascript
  // length 를 이용한 데이터 추가
  var colors = ["red", "blue", "grren"];
  colors[colors.length] = "black";
  colors[colors.length] = "brown";
  ```

  ```javascript
  // 배열의 현재 위치를 벗어나는 위치에 데이터가 추가될 때마다 배열의 length 프로퍼티는 해당 슬롯 위치의 +1
  var colors = ["red", "blue", "green"];
  colors[99] = "black";
  alert(colors.length); // 100
  ```

### 배열 감지

- ECMAScript 3

  - instanceof (X)

    ```javascript
    if (value instanceof Array) {
        // 배열일 때 실행하는 코드
    }
    ```

    전역 스코프가 하나뿐인 단순한 웹페이지는 instanceof 연산자를 쓰면 되지만
    **웹 페이지에 프레임이 여러개 있으면** 실행 컨텍스트도 여러개 있는 것이라 **배열 생성자도 여러개 있는 것임.**

- ECMAScript5

  - Array.isArray()

    instanceof 의 문제를 우회.

    주어진 값이 배열인지 아닌지를 생성된 실행 컨텍스트와 무관하게 명확히 알 수 있음.

    ```javascript
    if (Array.isArray(value)) {
        // 배열일 때 실행하는 코드
    }
    ```

### 변환 메서드

모든 객체는 toLocaleString(), toString(), valueOf() 메소드를 가진다.

배열에서 toString()과 valueOf()는 결과값이 같다.

```javascript
var colors = ["red", "blue", "green"];
alert(colors.toString()); // red, blue, green
alert(colors.valueOf()); // red, blue, green
alert(colors); // red, blue, green, toString()을 호출한 것과 같은 효과
```

배열에서 toLocaleString()은 toString() 결과와 값이 같을수도 다를수도 있다.

```javascript
var person1 = {
    toLocaleString : function() {
        return "Nicholas";
    },
    toString: function() {
        return "Nicholas";
    }
};

var person2 = {
    toLocaleString : function() {
        return "Grigorios";
    },
    toString : function() {
        return "Greg";
    }
};

var people = [person1, person2];
alert(people); // Nicholas,Greg
alert(people.toString()); // Nicholas,Greg
alert(people.toLocalString()); // Nicholas,Gregorious
```

join() 을 써서 다른 구분자를 써서 출력 가능하다.

```javascript
var colors = ["red", "green", "blue"];
alert(colors.join(",")); // red,green,blue
alert(colors.join("||")); // red||green||blue
```

### 스택 메서드

ECMAScript 배열은 다른 데이터 구조인 듯 동작하게 하는 메서드가 있다.

push(), pop()을 사용해서 배열을 스택처럼 사용할 수 있다.

### 큐 메서드

ECMAScript 배열은 다른 데이터 구조인 듯 동작하게 하는 메서드가 있다.

- shift(), pop()을 사용해서 배열을 큐처럼 사용할 수 있다.

```javascript
var colors = new Array();
var count = colors.push("red", "green");
alert(count); // 2

count = colors.push("black");
alert(count) // 3

var item = colors.shift();
alert(item) // red
alert(colors.length); // 2
```

- unshift() : 매개변수로 받은 데이터를 배열의 처음에 추가

```javascript
var colors = new Array();
var count = colors.unshift("red", "green");
alert(count); // 2

count = colors.unshift("black");
alert(count); // 3

var item = colors.pop();
alert(item); // green
alert(colors.length) // 2
```

### 정렬 메서드

배열 순서를 조작하는 메서드

- reverse()

  - 순서 뒤집기

- sort()

  - 가장 작은 값이 첫 번째에 오게 정렬

  - 내부적으로 String() 함수를 이용해 데이터를 문자열로 변환한 후, 비교하므로 숫자만으로 이루어진 배열에서는 이상한 동작을 함

    ```javascript
    var values = [0, 1, 5, 10, 15];
    values.sort();
    alert(values); // 0,1,10,15,5
    ```

  - sort() 메서드를 써서 만족스러운 결과를 얻기 힘든 경우가 있으므로 **비교함수** 를 넘겨서 순서를 조절할 수 있음

    - 비교함수
      첫 번째 매개변수 < 두 번째 매개변수 : 음수 반환
      첫 번째 매개변수 == 두 번째 매개변수 : 0 반환
      첫 번째 매개변수 > 두 번째 매개변수 : 양수 반환

    ```javascript
    function compare(value1, value2) {
        if (value < value2) {
            return -1;
        } else if (value1 > value2) {
            return 1;
        } else {
            return 0;
        }
    }

    var values = [0, 1, 5, 10, 15];
    values.sort(compare);
    alert(values); // 0,5,10,15
    ```

### 조작 메서드

- concat()

  - 현재 배열을 복사한 다음 메서드의 매개변수를 새 배열 마지막에 추가하여 반환

    ```javascript
    var colors = ["red", "green", "blue"];
    var colors2 = colors.concat("yellow", ["black", "brown"]);
    alert(colos); // red, green, blue
    alert(colos2); // red, green, blue, yellow, black, brown
    ```

- slice(시작 인덱스, 끝 인덱스)

  - 배열에 포함된 데이터 일부를 가진 메서드

- splice()

  - 배열 중간에 데이터를 삽입

  - 세 가지 사용 방법: 삭제, 삽입, 대체

    ```javascript
    var colors = ["red", "green", "blue"];
    var removed = colors.splice(0,1); // 첫 번째 데이터 제거
    alert(colors); // green,blue
    alert(removed); // red 하나만 남은 배열

    removed = colors.splice(1, 0, "yellow", "orange"); // 인덱스 1에 데이터 2개 추가
    alert(colors); // green,yellow,orange,blue
    alert(removed); // 빈 배열

    removed = colors.splice(1, 1, "red", "purple") // 데이터 2개를 추가하고, 1개 제거
    alert(colors); // green,red,purple,orange,blue
    alert(removed); // yellow 하나만 남은 배열
    ```

### 위치 메서드

ECMAScript 5에서는 배열에 indexOf()와 lastIndexOf() 두 위치 메서드가 추가됨

첫 번째 매개변수는 검색할 데이터, 두 번째 매개변수는 검색을 시작할 인덱스

반환은 === 연산자로 비교해서 일치하는 데이터

- indexOf()
  - 배열의 처음(인덱스 0)에서 검색을 시작
- lastIndexOf()
  - 배열의 마지막에서 검색을 시작

### 반복 메서드

ECMAScript 5에서는 배열에 다섯 가지 반복 메서드를 추가함

첫 번째 매개변수는 배열의 각 데이터에서 실행할 함수(**콜백** 함수), 두 번째 매개변수는 함수를 실행할 스코프 객체

> **콜백**
>
> - 호출될 함수를 알려 주어 다른 프로그램 또는 다른 모듈에서 함수를 호출하게 하는 방법
> - 프로그래밍에서 콜백은 다른 코드의 인수로 넘겨주는 실행 가능한 코드
> - 콜백을 넘겨받는 코드는 이 콜백을 필요에 따라 즉시 실행할 수도 있고, 아니면 나중에 실행할 수도 있다.

스코프는 this의 값에 영향을 미친다.

콜백 함수를 실행 했을 때 메서드의 반환 값에 영향을 미치는지는 메서드마다 다름.

- every()

  - 배열의 모든 데이터에서 콜백 함수를 호출하고 반환 값이 전부 true 이면 true 를 반환

    ```javascript
    var numbers = [1,2,3,4,5,4,3,2,1];
    var everyResult = numbers.every(function(item, index, array){
        return (item > 2);
    });
    alert(everyResult); // false
    ```

- filter()

  - 배열의 모든 데이터에서 콜백 함수를 호출하고 반환 값이 true 인 데이터를 새 배열에 저장하여 반환

    ```javascript
    var numbers = [1,2,3,4,5,4,3,2,1];
    var filterResult = numbers.filter(function(item, index, array){
        return (item > 2);
    });

    alert(numbers); // 3,4,5,4,3
    ```

- forEach()

  - 배열의 모든 데이터에서 콜백 함수를 호출. 이 메서드는 반환 값이 없음

- map()

  - 배열의 모든 데이터에서 콜백 함수를 호출하고 그 결과를 새 배열에 저장하여 반환
    원래 배열과 1:1 대응되는 배열을 만들 때 유용

    ```javascript
    var numbers = [1,2,3,4,5,4,3,2,1];
    var mapResult = numbers.map(function(item, index, array){
        return item * 2;
    });

    alert(numbers); // 2,4,6,8,10,8,6,4,2
    ```

- some()

  - 배열의 모든 데이터에서 콜백 함수를 호출하고 반환 값 중 하나라도 true 이면 true 를 반환

    ```javascript
    var numbers = [1,2,3,4,5,4,3,2,1];
    var someResult = numbers.some(function(item, index, array){
        return (item > 2);
    });

    alert(someResult); // true
    ```


### 감소 메서드



## Date 타입

### 상속된 메서드

### 날짜 표시 메서드

### 날짜/시간 메서드



## RegExp 타입

### 정규 표현식 인스턴스 프로퍼티

### 정규 표현식 인스턴스 메서드

### RegExp 생성자 프로퍼티

### 패턴의 한계



## Function 타입

### 오버로딩 없음

### 함수 선언 vs 함수 표현식

### 값처럼 쓰는 함수

### 함수의 내부 구조

### 함수 프로퍼티와 메서드



## 원시 래퍼 타입

### 불리언 타입

### Number 타입

### String 타입



## 내장된 싱글톤 객체

### Global 객체



## 요약

