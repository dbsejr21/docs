# 4장 변수와 스코프, 매모리


## 변수의 원시값과 참조값
#### 원시값
- 단순한 데이터. Undefined, Null, 불리언, 숫자, 문자열
- 변수에 저장된 실제 값을 조작
- 동적으로 프로퍼티를 추가할 수 없다(에러는 안난다).
    ```
    var name = "Nicholas";
    name.age = 27;
    alert(name.age); // undefined    
    ```

#### 참조값
- 메모리에 저장된 객체
- 객체를 조작할 때는 사실 객체가 아니라 해당 객체에 대한 참조를 조작함.
- 동적으로 프로퍼티를 추가할 수 있다.
    ```
    var person = new Object();
    person.name = "Nicholas";
    alert(person.name); // "Nicholas"    
    ```


## 실행 컨텍스트의 이해

## 가비지 콜렉션의 이해