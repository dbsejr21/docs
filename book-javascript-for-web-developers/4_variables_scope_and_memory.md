# 4장 변수와 스코프, 매모리


## 변수의 원시값과 참조값
##### 원시값

- 단순한 데이터. Undefined, Null, 불리언, 숫자, 문자열
- 변수에 저장된 실제 값을 조작

##### 참조값

- 메모리에 저장된 객체
- 객체를 조작할 때는 사실 객체가 아니라 해당 객체에 대한 참조를 조작함.

##### 동적 프로퍼티

- 원시값은 동적으로 프로퍼티를 추가할 수 없다(에러는 안난다).

  ```javascript
  var name = "Nicholas";
  name.age = 27;
  alert(name.age); // undefined    
  ```


- 참조값은 동적으로 프로퍼티를 추가할 수 있다.

  ```javascript
  var person = new Object();
  person.name = "Nicholas";
  alert(person.name); // "Nicholas"
  ```

##### 값 복사

- 원시값은 값 자체를 복사한다.
- 참조값은 객체 자체를 복사하는게 아니라 힙에 저장된 객체를 가리키는 포인터를 복사한다.

##### 매개변수 전달

- ECMAScript의 **함수 매개변수는 모두 값으로 전달된다.**

- 즉, 매개변수에 객체를 넘겨도 값으로 전달된다.

  ```javascript
  function setName(obj) {
      obj.name = "Nicholas";
  }

  var person = new Object();
  setName(person);
  alert(person.name); // "Nicholas"

  // 함수 내부에서 obj 객체에 name 프로퍼티를 추가한 것이 외부에서도 반영되었음을 보고
  // 객체가 참조 형태로 전달되었다고 오해하면 안됨.
  ```

  ```javascript
  // 많은 개발자들이 함수 안에서 객체를 조작했을 때 외부에도 반영됨을 보고 참조 형태로 전달되었다고 오해한다.
  // 아래 코드를 보면 객체가 매개변수로 전달될 때 "값"으로 전달됨을 명확히 알 수 있다.
  function setName(obj) {
      obj.name = "Nicholas";
      obj = new Object();
      obj.name = "Greg";
  }

  var person = new Object();
  setName(person);
  alert(person.name); // "Nicholas"
  ```

#### 타입 판별

- **typeof** 연산자는 변수가 **원시 타입인지 파악**하기에 좋다.

  ```javascript
  var s = "Nicholas";
  var b = true;
  var i = 22;
  var u;
  var n = null;
  var o = new Object();

  alert(typeof s) // string
  alert(typeof b) // boolean
  alert(typeof i) // number
  alert(typeof u) // undefined
  alert(typeof n) // object
  alert(typeof o) // object
  ```

- **instanceof** 연산자는 변수가 **주어진 참조 타입의 인스턴스일 때 true**를 리턴한다.
  원시값에 instanceof를 사용하면 false이다.

  ```javascript
  alert(person instanceof Object); // person 변수가 Object의 인스턴스인가?
  alert(colors instanceof Array); // colors 변수가 Array의 인스턴스인가?
  alert(pattern instanceof RegExp); // pattern 변수가 RegExp의 인스턴스인가?
  ```

## 실행 컨텍스트의 이해

##### 개념 설명 

- **실행컨텍스트(Execution Context aka EC)** 는 다른 데이터에 대한 접근과 행동을 규정
- **변수 객체(Variable Obejct aka VO)** 는  컨텍스트의 정의된 모든 변수와 함수가 존재하는 곳
- 전역 컨텍스트는 가장 바깥쪽에 존재하는 실행컨텍스트이다.
  웹 브라우저에서는 window 객체라 부르며 전역변수와 함수는 모두 window 객체의 프로퍼티 및 메서드로 생성.


- **스코프 체인(Scope Chain)**
  - 실행컨텍스트에서 코드를 실행할 때 생성
  - 실행컨텍스트가 접근할 수 있는 모든 변수와 함수에 순서를 정의
  - **스코프 체인의 앞쪽**은 항상 코드가 실행되는 컨텍스트의 변수 객체
  - 함수의 컨텍스트라면 **활성화 객체(Activation Object)**가 변수 객체
    - 활성화 객체는 항상 arguments 변수 단 하나로 시작
  - 전역 컨텍스트는 항상 스코프체인의 마지막
  - 각 컨텍스트는 상위 컨텍스트의 변수나 함수를 검색 가능하지만 반대는 불가능



##### 스코프 체인 확장

- 특정 문장은 스코프 체인 앞 부분에 임시 변수 객체를 생성
- 이렇게 만들어진 임시 변수 객체는 코드 실행 끝나면 사라짐
- 종류
  - try-catch
  - with

##### 자바스크립트에는 블록 레벨 스코프가 없다!

- 예를 들면 이렇다!! :confused: 

  ```javascript
  if (true) {
      var color = "blue";
  }
  alert(color); // "blue"
  ```

  ```javascript
  for (var i = 0; i < 10; i++) {
      doSomething(i);
  }
  alert(i); // 10
  ```

- 변수 선언

  - var를 사용해 선언한 변수는 자동으로 가장 가까운 컨텍스트에 추가됨

  - var로 선언하지 않은 변수는 전역 컨텍스트에 추가됨

    ```javascript
    function add(num1, num2) {
        var sum = num1 + num2;
        return sum;
    }
    var result = add(10, 20); //30
    alert(sum); // sum은 유효한 범수가 아니므로 에러가 발생한다.
    ```

- 식별자 검색

  - 식별자란 변수나 함수, 프로퍼티, 함수 매개변수의 이름
  - 스코프 체인 앞에서부터 식별자 이름을 검색하고, 이러한 검색은 전역컨텍스트까지 이어질 수 있다.
    (스코프 체인 내부의 객체에도 프로토타입 체인이 있으므로 각 객체의 프로토타입 체인을 따라 계속 검색할 수도 있다).

## 가비지 콜렉션의 이해

- Mark and Sweep(표시하고 지우기)

  - 변수가 특정 컨텍스트 안에서 사용할 것으로 정의되면 그 변수는 컨텍스트 안에 있는 것으로 표시됨

    > 함수 안에서 변수 선언

  - 변수가 컨텍스트 밖으로 나가면 컨텍스트 밖에 있는 것으로 표시됨.

- Referrence Counting(참조 카운팅)

  - 각 값이 얼마나 많이 참조되었는지 추적
  - 순환 참조 문제(데드락) 발생 가능

- 메모리 관리

  - 메모리 사용 최적화 Best: 필요없어진 데이터에 null 할당

    ```javascript
    function createPerson(name) {
        var localPerson = new Object();
        localPerson.name = name;
        return localPerson;
    }

    var globalPerson = createPerson("Nicholas");

    // globalPerson을 사용하는 코드

    globalPerson = null;
    ```

    localPerson은 함수 실행을 마치는 순간 컨텍스트를 벗어나므로 명시적으로 참조를 제거할 필요 없음.

    globalPerson은 전역변수이므로 필요없어진 순간에 직접 참조 제거(라고 하는데 근데.. 이게 진짜 좋은 방법일까..😕)

- 요약

  **변수** 요약

  - 원시 값은 불리언, 숫자, 문자열, Undefined, Null 다섯가지
  - 참조값은 그 외의 것, 객체
  - 원시 값은 고정된 크기를 가지며 스택 메모리에 저장
  - 참조 값은 객체이며 힙 메모리에 저장
  - 참조 값은 객체에 대한 참조만 저장할 뿐임
  - 원시 값을 한 변수에서 다른 변수로 복사하면 값 자체가 복사
  - 참조 값을 한 변수에서 다른 변수로 복사하면 해당 객체에 대한 참조만을 복사하므로 두 변수는 같은 객체를 참조
  - 자바스크립트에서의 매개변수 전달은 원시 값이던 참조 값이던 Call by Value임
    - 넘겨지기 전 원래 참조변수 != 함수 매개변수 (서로 다른 객체 주소를 참조)
  - typeof 연산자는 값의 원시 타입을 판별하며 instanceof 연산자는 값의 참조 타입을 판별

  **스코프** 요약

  - 스코프는 실행 컨텍스트라고도 하며, 전역 컨텍스트와 함수 컨텍스트가 있음
  - 실행 컨텍스트에 진입할 때마다 스코프 체인이 만들어지며, 스코프 체인은 변수와 함수를 검색하는데 쓰임
  - 함수 컨텍스트는 해당 스코프에 있는 변수, 해당 스코프를 포함하는 컨텍스트에 있는 변수, 전역 컨텍스트에 있는 변수에 모두 접근 가능
  - 전역 컨텍스트는 전역 컨텍스트에 있는 변수와 함수에만 접근 가능