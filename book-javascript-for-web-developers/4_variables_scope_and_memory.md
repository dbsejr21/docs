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

#####값 복사

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



## 실행 컨텍스트의 이해

## 가비지 콜렉션의 이해