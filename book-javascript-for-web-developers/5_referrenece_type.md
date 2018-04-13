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



##### Object 타입

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

  ​