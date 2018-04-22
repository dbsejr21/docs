# 12. 디자인 패턴 즐기기

프로그램 개발하다보면 부품 단위로 생각해볼 때 비슷한 부분들이 있다.

- 어떤 상태가 바뀌었을 때 그에 반응하는 클래스 군을 만들기
- 다른 팀이 만든 부품과의 중개를 하는 클래스 만들기
- 재귀적인 구조를 클래스로 표현하기

이런 비슷한 '목적'에 대해 클래스의 구조의 모범 사례를 패턴화 한 움직임이 있었고,

가장 유명한 GoF 디자인 패턴을 아래와 같이 소개한다.



## 생성에 관한 패턴

### AbstractFactory 패턴

- 관련된 일련의 인스턴스를 상황에 따라 적절하게 생성하는 방법을 제공


- 일련의 인스턴스군을 모아서 생성하기

```java
public interface Factory {
    Connection getConnection();
    Configuration getConfiguration();
}
```

```java
public abstract class Connection {
    // 임의의 처리
}
```

```java
public abstract class Configuration {
    // 임의의 처리
}
```

```java
public class PostgreSQLFactory implements Factory {
    public Connection getConnection() {
        return new PostgreSQLConnection();
    }
    
    public Configuration getConfiguration() {
        return new PostgreSQLConfiguration();
    }
}
```

```java
public class PostgreSQLConnection extends Connection {
    // PostgreSQL의 커넥션 처리
}
```

```java
public class PostgreSQLConfiguration extends Configuration {
    // PostgreSQL의 설정 정보 로딩 처리
}
```

```java
public class MySQLFactory implements Factory {
    public Connection getConnection() {
        return new MySQLConnection();
    }
    
    public Configuration getConfiguration() {
        return new MySQLConfiguration();
    }
}
```

```java
public class MySQLConnection extends Connection {
    // MySQL의 커넥션 처리
}
```

```java
public class MySQLConfiguration extends Configuration {
    // MySQL의 설정 정보 로딩 처리
}
```

```java
public class SampleMain {
    public static void main(String... args) {
        String env = "PostgreSQL";
        
        Factory factory = createFactory(env);
        Connection connection = factory.getConnection();
        Configuration configuration = factory.getConfiguration();
    }
    
    private static Factory createFactory(String env) {
        switch (env) {
            case "PostgreSQL":
                return new PostgreSQLFactory();
            case "MySQL":
                return new MySQLFactory();
            default:
                throw new IllegalArgumentException(env);
        }
    }
}
```

**AbstractFactory 패턴은 관련된 일련의 인스턴스를 모아서 생성하는 경우 위력을 발휘한다.**

현업의 프로그램에서는 프레임워크를 만들 때 사용하는 경우가 많은 패턴이다.

환경이나 조건에 따라 처리 패턴을 전환하여 실행하는 구조를 실현할 때 프레임워크의 이용자가 구체적인 처리 내용을 의식하지 않고 호출할 수 있는 점이 장점.



### 빌더

복합화된 인스턴스의 생성 과정을 은폐



### 싱글턴

특정 클래스에 대해 인스턴스가 하나임을 보장.



## 구조에 관한 패턴

### Adapter 패턴

- 인터페이스에 호환성이 없는 클래스들을 조합시키기

기존 시스템과 새로운 시스템의 인터페이스 차이를 흡수하는 어댑터 클래스를 작성

이로써 적은 변경으로 기존 시스템을 새로운 시스템에 적용할 수 있도록 함.

#### # 상속을 이용

```java
public class OldSystem {
    public void oldProcess() {
        // 기존 처리
    }
}
```

```java
public interface Target {
    void process();
}
```

```java
public class Adapter extends OldSystem implements Target {
    public void process() {
        oldProcess();
    }
}
```

```java
public class SampleMain {
    public static void main(String... args) {
        Target target = new Adapter();
        target.process();
    }
}
```

OldSystem 의 oldProcess 메서드를 새로운 시스템에서 호출하려고 할 때 새로운 시스템에서는 Target 인터페이스를 구현한 클래스를 호출하는 방식으로 제작되어 있는 경우, 새로운 시스템에서 직접 oldProcess를 호출할 수 없다.

그래서 Adapter 클래스를 제공하였다.

Adapter 는 OldSystem 을 상속하고 Target 인터페이스를 구현했다. 이렇게 하면 새로운 시스템에서는 Adapter의 process 메서드 호출을 통해 기존 기능을 그대로 이용 가능하게 된다.



상속을 이용한 방법은 아래 두가지 케이스일 때 안된다.

- OldSystem이 final 클래스일 때
  - Adapter 가 OldSystem	을 상속받을 수 없으므로 어댑터 작성 불가
- Target 이 추상 클래스일 경우
  - Adapter가 OldSystem을 상속받은 채 Target도 추가적으로 상속할 수 없으므로 어댑터 작성 불가

이런 상황에서는 위임을 사용하면 해결된다.



#### # 위임을 이용

```java
public class OldSystem {
    public void oldProcess() {
        // 기존 처리
    }
}
```



```java
public abstract class Target {
    abstract void process();
}
```

```java
public class Adapter extends Target {
    private OldSystem oldSystem;
    
    public Adapter() {
        this.oldSystem = new OldSystem();
    }
    
    public void process() {
        this.oldSystem.oldProcess();
    }
}
```

```java
public class SampleMain {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.process();
    }
}
```

Adapter가 OldSystem을 가지고 있도록 하여 Target의 process()의 처리를 위임시킴



#### # 어댑터 패턴 정리

Adapter 패턴은 사용하고자 하는 인터페이스를 강제로 바꾸고자할 때 사용할 수 있다.

이미 존재하는 프로그램의 코드 수정을 피하고 싶을 경우이다.

어댑터 패턴은 별로 권장하지는 않지만 프레임워크를 이용하고 있을 때 '프레임워크 내부의 처리를 아무래도 다른 방법으로 호출하고 싶다'라는 경우에 쓰면 좋다.



### Compsite 패턴

-  재귀적 구조 쉽게 처리하기

```java
public interface Entry {
    void add(Entry entry);
    void remove();
    void rename(String name);
}
```

```java
public class File implements Entry {
    private String name;
    
    public File(String name) {
        this.name = name;
    }
    
    public void add(Entry entry) {
        throw new UnsupportedOperationException();
    }
    
    public void remove() {
        System.out.println(this.name + "을 삭제했다.");
    }
    
    public void rename(String name) {
        this.name = name;
    }
}
```

```java
public class Directory implements Entry {
    private String name;
    
    private List<Entry> list;
    
    public Directory(String name) {
        this.name = name;
        list = new ArrayList<>();
    }
    
    public void add(Entry entry) {
        throw list.add(entry);
    }
    
    public void remove() {
        Iterator<Entry> itr = list.iterator();
        while (itr.hasNext()) {
            Entry entry = itr.next();
            entry.remove();
        }
        System.out.println(this.name + "을 삭제했다.");
    }
    
    public void rename(String name) {
        this.name = name;
    }
}
```

```java
public class SampleMain {
    public static void main(String... args) {
        File file1 = new File("file1");
        File file2 = new File("file2");
        File file3 = new File("file3");
        File file4 = new File("file4");
        
        Directory dir1 = new Directory("dir1");
        dir1.add(file1);
        
        Directory dir2 = new Directory("dir2");
        dir2.add(file2);
        dir2.add(file3);
        
        dir2.add(dir1);
        
        dir1.add(file4);
        
        dir1.remove();
        
    }
}
```



#### # Composite 패턴 정리

Composite 패턴에서는 재귀적인 구조를 표현할 수 있기 때문에 디렉터리와 파일을 동일시 하여 취급

동일시 하기 위해 공통의 인터페이스 Entry 를 준비했고, File 과 Directroy 는 Entry를 구현했다.



## 행동에 관한 패턴

### Command 패턴

- '명령'을 인스턴스로 취급하여 처리 조합을 쉽게 한다.

처리 내용이 비슷한 명령을 패턴에 따라 구분하거나 조합하거나 해서 실행하는 처리



예를 들어, 판매 사이트에서의 할인 계산

- 상품 금액에 할인율을 곱하는 것이라면 간단
- 계절과 상품의 내용에 따라 할인의 패턴을 바꾸어 처리하는게 필요
- 할인한 금액에 추가로 할인



> 음.. 책에서의 예제가 별로인지 이 방법은 좀 별론데.;;;
>
> 처리 대상 자체를 가지고 있는 것 자체가 별로...



### Strategy 패턴

알고리즘을 바꿔서 이용하는 경우 가독성이 높은 코드 작성 가능

전략 인터페이스를 구현한 알고리즘을 매개변수와 조건에 따라 바꾸어 이용함으로써 처리 내용 자체를 의식하지 않고 구현



### Observer 패턴

- 어떤 인스턴스의 상태가 변화할 때 그 인스턴스 자신이 상태의 변화를 통지하는 구조를 제공



정기적으로 혹은 어떤 계기로 상태가 변화하는 인스턴스가 있다.

> 예를 들어, 
>
> - 다른 시스템으로부터 데이터를 수신
> - 사용자가 버튼을 클릭



이와 같이 인스턴스의 상태가 바뀐 것을 감지하여 처리하도록 하는 프로그램을 만들기 위한 패턴



옵저버 패턴은 이름대로 어떤 특정 인스턴스의 상태가 변화한 것을 관찰하고, 그 인스턴스 자신이 상태의 변화를 통지하는 구조를 제공하는 패턴



```java
public interface Observer {
    void update(Subject subject);
}
```

```java
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(this);
        }
    }
    
    public abstract void execute();
}
```

```java
public class Client implements Observer {
    public void update(Subject subject) {
        System.out.println("통지를 수신했다.");
    }
}
```

```java
public class DataChanger extends Subject {
    private int status;
    
    public void execute() {
        status++;
        System.out.println("상태가 " + status + "로 바뀌었다.");
    }
    notifyObservers();
}
```

```java
public class SampleMain {
    public static void main(String... args) {
        Observer observer = new Client();
        Subject dataChanger = new DataChanger();
        
        dataChanger.addObserver(observer);
        for (int count = 0; count < 10; count++) {
            dataChanger.execute();
            
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
}
```



#### # 옵저버 패턴 정리

- Observer 클래스
  - 상태 변경을 '감시'한다
- Subject 클래스
  - 상태 변경을 '통지'한다

Subject는 통지할 Observer를 보관하고, notifyObserver 메서드가 호출되면 Observer에 통지한다(update 메서드를 호출).

정보를 통지하는 구조가 Observer 인터페이스와 Subject 추상 클래스에서 제공되며, 실제 처리는 각각을 구현하고 상속한 클래스에서 실시하는 것이 핵심이다.

이를 통해 통지를 하는 곳과 받는 곳을 쉽게 늘이거나 줄일 수 있다.



현업에서는 "사용자의 조작에 의해 값이 변경된 것을 계기로 어떤 특정한 처리를 호출하여 표시 및 백그라운드 처리를 별도의 구현자가 담당한다"라고 나누어 처리할 수 있다.