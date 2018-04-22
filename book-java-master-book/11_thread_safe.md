# 11. 스레드 세이프 즐기기

## 상태를 유지하지 않게(Stateless) 한다

### 속성은 처리할 메서드의 인수로 변경하여 삭제한다

클래스의 속성으로 갖게하면 해당 속성이 여러 스레드로부터 조작되는 것을 고려해야 함.



### 로직에서 처리한 결과를 인스턴스 변수에 저장하고, 속성 접근자(Accessor) 메서드를 사용해 취득하도록 한다

- 로직에서 처리한 결과의 정보량이 여러개인 경우
  - 결과를 모아서 객체를 만들고 호출한 곳으로 반환
- 로직 자체가 비동기로 처리되는 경우(결과를 지연해서 얻은 경우)
  - 콜백 처리를 구현
  - Future 패턴 구현

#### # 콜백 처리 예

```java
public interface AsyncCallback {
    void notify(String message);
}
```

```java
public class CallbackSample {
    public static void main(String... args) {
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        AsyncProcess proc = new AsyncProcess(new AsyncCallback( {
            public void notify(String message) {
                System.out.println("callback message: " + message);
                executor.shutdown();
            }
        }));
        
        executor.execute(proc);
        System.out.println("AsyncProcess is started.");
    }
}
```

```java
public class AsyncProcess implements Ruunabale {
    private AsyncCallback callback;
    
    public AsyncProcess(AsyncCallback asyncCallback) {
        this.callback = asyncCallback;
    }
    
    public void run() {
        try {
            Thread.sleep(1000L);
            this.callback.notify("Finished.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("AsyncProcess is finished.");
    }
}
```



#### # Future 패턴 예

```java
public class FutureSample {
    public static void main(String args... args) {
        ExecutorService executor = Executor.newSingleThreadExecutor();
        
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    return "Execution is failed."
                }
                return "Finished."; // 결과로 얻을 수 있는 객체는 불변 객체로 하라.
            }
        });
        
        System.out.println("ExecutorService is started.");
        
        try {
            String message = future.get();
            System.out.println("ExecutorService is finished : message = " + message);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```





## '메서드 단위'가 아니라 최소한의 '일련의 처리'에 대해 동기화한다

보호 범위를 오인하지 말자.

```java
// 자주 있는 실수 #1 : put 메서드의 호출만을 synchronized 블록으로 보호하기.
private Map<String, Integer> map = new HashMap<>();

// 생성자에서 map.put("COUNTER", 0) 실시

public void increment() {
    Integer counter = map.get("COUNTER");
    counter++;
    synchronized (this) {
        map.put("COUNTER", counter);
    }
}
```

```java
// 자주 있는 실수 #2 : ConcurrentHashMap 클래스 사용하기
private Map<String, Integer> map = new ConcurruentHashMap<>();

// 생성자에서 map.put("COUNTER", 0) 실시

public void increment() {
    Integer counter = map.get("COUNTER");
    counter++;
    map.put("COUNTER", counter);
}
```



각 메서드의 호출을 보호할 수는 있지만 메서드 호출을 연결한 일련의 '처리'를 보호할 수 없다.

따라서, '일련의 처리'를 보호해야 한다.

```java
private Map<String, Integer> map = new ConcurruentHashMap<>();

// 생성자에서 map.put("COUNTER", 0) 실시

public synchronized void increment() {
    Integer counter = map.get("COUNTER");
    counter++;
    map.put("COUNTER", counter);
}
```



실질적으로 synchronized 로 동기화 되는 범위는 다음과 같다.

#### # 서로 다른 클래스의 synchronized 메서드는 동기화 하지 않는다.

synchronized 메서드는 동일 클래스 내의 메서드끼리만 동기화디ㅗ고 다른 클래스와는 동기화 하지 않음.



#### # 동일한 잠금 객체에 대한 처리만 동기화 된다.

```java
public class SomeProcessor {
    Obejct lock = new Object();
    public synchronized void doSync1() {/*처리 생략*/}
    public synchronized void doSync2() {/*처리 생략*/}
    
    public void doSync3() {
        synchronized(this) {
            //처리 생략
        }
    }
    
    public void doSync4() {
        synchronized (lock) {
            // 처리 생략
        }
    }
}
```

여기서 doSync4 메서드만은 lock이라는 다른 잠금 객체를 지정하고 있으므로 동기화 되지 않음.



#### # 동일 클래스의 동일 메서드라도 인스턴스가 다르면 잠금은 걸리지 않는다.

```java
public class SomeProcessor {
    public synchronized void doSync() {
        // 처리 생략
    }
}

pulbic class SomeConsumer {
    public void doSync1() {
        SomeProcesor proc = new SomeProcessor();
        proc.doSync();
    }
    
    public void doSync2() {
        SomeProcessor proc = new SomeProcessor();
        proc.doSync();
    }
}
```



#### # 동일한 처리라도 잠금 객체의 인스턴스가 다르면 잠금은 걸리지 않는다.

```java
public class SomeProcessor {
    public void doSync() {
        Object lock = new Object();
        synchronized (lock) {
            // 처리 생략
        }
    }
}
```



- 메서드 실행의 동기화
  - '스레드 세이프하지 않은 처리'를 동시에 호출하는 것을 방지한다
  - '처리의 호출' 부분을 동기화한다
- 일련의 처리의 동기화
  - '자신이 작성한 스레드 세이프하지 않은 처리'가 동시에 호출되는 것을 방지한다
  - '자신이 작성한 일련의 처리'를 동기화한다