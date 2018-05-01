# 객체지향의 사실과 오해

## 01. 협력하는 객체들의 공동체

- 객체지향의 목표는 실세계를 모방하는 것이 아니다. 오히려 새로운 세계를 창조하는 것이다.
  고객과 사용자를 만족시킬 수 있는 신세계를 창조하는 것이다.



- 실세계의 모방이라는 객체지향의 개념은 훌륭한 프로그램을 설계하고 구현하는 실무적인 관점에서는 부적합.
  객체지향이라는 용어 자체에 담긴 기본 사상을 이해하고 학습하는 데는 매우 효과적.



- 객체지향 설계라는 예술은 적절한 객체에게 적절한 책임을 할당하는 것에서 시작된다.
  얼마나 적절한 책임을 선택하느나가 애플리케이션의 아름다움을 결정한다.



- 객체는 다른 객체의 명령에 복종하는 것이 아니라 요청에 응답할 뿐이다.
  객체는 충분히 자율적이어야 한다.



- 상태와 행동을 함께 지닌 자율적인 객체
  객체가 협력에 참여하는 과정 속에서 스스로 판단하고 스스로 결정하는 자율적인 존재로 남기 위해서는 필요한 행동과 상태를 함께 지니고 있어야 한다.
  객체의 관점에서 "자율성"이란 자신의 상태를 직접 관리하고, 상태를 기반으로 스스로 판단하고 행동할 수 있음을 의미한다.



- 객체지향의 세계에서 협력은 메시지를 전송하는 객체와 메시지를 수신하는 객체 사이의 관계로 구성된다.
  커피 제조 요청이 메시지고 커피를 제조하는 구체적인 방법이 메서드다.



- 클래스가 객체지향 프로그래밍 언어의 관점에서 매우 중요한 구성요소인 것은 분명하지만 객체지향의 핵심을 이루는 중심 개념이라고 말하기에는 무리가 있다.



- 훌륭한 객체지향 설계자가 되기 위해 거쳐야 할 첫 번째 도전은 코드를 담는 클래스의 관점에서 메시지를 주고받는 객체의 관점으로 사고의 중심을 전환하는 것이다.



- 객체지향의 중심에는 클래스가 아니라 객체가 위치하며, 중요한 것은 클래스들의 정적인 관계가 아니라 메시지를 주고받는 객체들의 동적인 관계다.



## 02. 이상한 나라의 객체

- 많은 사람들이 객체지향을 직관적이고 이해하기 쉬운 패러다임이라고 말하는 이유는 **객체지향이** 세상을 자율적이고 독립적인 객체들로 분해할 수 있는 **인간의 기본적인 인지 능력에 기반을 두고 있기 때문이다.**



- 객체란, 인간이 분명하게 인지하고 구별할 수 있는 물리적인 또는 개념적인 경계를 지닌 어떤 것.



- 객체지향 패러다임은 인간이 인지할 수 있는 다양한 객체들이 모여 현실 세계를 이루듯, 소프트웨어의 세계 역시 그러할 것이라는 믿음에서 출발하지만 현실 세계와 소프트웨어 세계의 유사성은 딱 여기까지다. **현실 세계를 "모방"해서는 안된다.**



- **객체지향의 목적**은 현실 세계를 "모방" 하는 것이 아니라 **새로운 세계를 창조하는 것이다. "은유"를 통해!**
  현실 세계의 전등은 사람의 손길 없이는 전원을 크겨나 끌 수 없지만 소프트웨어 세계에서는 스스로 할 수 있다!
  현실 세계에서는 사람이 직접 주문 금액을 계산하지만 소프트웨어 세계에서는 주문 객체가 스스로 자신의 금액을 계산한다!



- **상태는 왜 필요한가?**
  어떤 행동의 결과는 과거에 어떤 행동"들"이 일어났었느냐에 의존한다. 상태를 이용하면 과거의 모든 행동 이력을 설명하지 않고도 행동의 결과를 쉽게 예측할 수 있다. 즉, **과거에 얽매이지 않고 현재를 기반으로 객체의 행동 방식을 이해할 수 있다.**



- 객체는 자율적인 존재다. 객체지향의 세계에서 객체는 다른 객체의 상태에 직접 접근할 수도, 상태를 변경할 수도 없다. **자율적인 객체는 스스로 자신의 상태를 책임진다.**



- 프로퍼티, 프로퍼티 값
  객체의 상태를 구성하는 모든 특징을 통틀어 "프로퍼티" 라고 하며, 이는 정적인 특성을 갖는다.
  "프로퍼티 값"은 시간이 흐름에 따라 변하는 동적인 값이다.



- 객체가 취하는 행동은 객체 자신의 상태를 변경시킨다. 객체의 행동에 의해 객체의 상태가 변경된다는 것은 행동이 부수 효과(Side Effect)를 초래한다는 것을 의미한다.
  객체의 행동은 상태를 변경시키지만 행동의 결과는 상태에 의존적이다.
  예) 음료를 마신 후의 앨리스의 키는 음료를 마시기 전의 앨리스의 키보다 작아져야 함.
  - 객체의 행동은 상태에 영향을 받는다.
  - 객체의 행동을 상태를 변경시킨다.



- 상태라는 개념을 이용해 행동을 두 가지 관점으로 나누면 아래와 같음.
  - 상호작용이 현재의 상태에 어떤 방식으로 의존하는가
    예) 앨리스의 키가 40 센티미터 이하라면 문을 통과할 수 있다.
  - 상호작용이 어떻게 현재의 상태를 변경시키는가
    예) 문을 통과한 후에 앨리스의 위치는 아름다운 정원으로 바뀌어야 한다.



- 객체는 자신에게 주어진 책임을 다하기 위해 다른 객체를 이용하고 다른 객체에게 서비스를 제공한다.
  객체가 다른 객체와 협력하는 유일한 방법은 다른 객체에게 요청을 보내는 것이다.



- **객체의 행동으로 인해 발생하는 결과**
  - **객체 자신의 상태 변경**
  - **행동 내에서 협력하는 다른 객체에 대한 메시지 전송**



- 상태 캡슐화
  현실 속에서 앨리스는 스스로 음료를 마시는 능동적인 존재, 음료는 스스로는 아무것도 할 수 없는 수동적인 존재.
  그러나, 객체지향의 세계에서 모든 객체는 자신의 상태를 스스로 관리하는 자율적인 존재다.
  그러므로, 음료 객체의 양을 줄이는 것은 음료 자신이어야 한다.
  - 예) 앨리스는 직접적으로 음료의 상태를 변경할 수 없고, 음료에게 자신이 음료를 마셨다는 메시지를 전달할 뿐이다.
    적절한 정도로 음료의 양을 줄이는 것은 앨리스의 메세지를 받은 음료 스스로의 몫이자 책임이다.



- 상태를 외부에 노출시키지 않고 행동을 경계로 캡슐화 하는 것은 결과적으로 객체의 자율성을 높인다.
  객체의 자율성이 높아질 수소록 협력이 유연하고 간결해진다.



- 식별자?
  값의 경우 두 인스턴스의 상태가 같다면 두 인스턴스가 같다고 판단.
  그러나, 객체의 경우 타입이 같은 두 객체의 상태가 완전히 똑같더라도 독립적인 별개의 객체로 다뤄져야 한다.



- 객체 특성 정리
  - 객체는 상태를 가지며 상태는 변경 가능하다.
  - 객체의 상태를 변경시키는 것은 객체의 행동이다.
    - 행동의 결과는 상태에 의존적이며 상태를 이용해 서술할 수 있다.
    - 행동의 순서가 실행 결과에 영향을 미친다.
  - 객체는 어떤 상태에 있더라도 유일하게 식별 가능하다.



- 초보자들은 먼저 객체에 필요한 상태가 뭔지 결정하고 그 상태에 필요한 행동을 결정한다. 이는 설계에 나쁜 영향을 끼칠 수 있다.
  - 상태를 먼저 정하면 캡슐화가 저해될 수 있음
    상태가 객체 내부로 깔끔하게 캡슐화 되지 못하고 공용 인터페이스에 노출되버릴 확률이 높아짐
  - 객체를 협력자가 아닌 고립된 섬으로 만들 수 있음
    객체가 필요한 이유는 시스템의 책임 달성을 위해 다른 객체와 협력하기 위해서임.
    상태 먼저 설계하면 협력적인 객체와는 벗어난 설계가 나오기 쉬움



- **객체지향 설계는 애플리케이션에 필요한 협력을 생각하고 협력에 참여하는 데 필요한 행동을 생각한 후 행동을 수행할 객체를 선택하는 방식으로 수행된다.**
  **행동을 결정한 후에 행동에 필요한 정보가 무엇인지를 고려하며 필요한 상태를 결정한다.**



- **행동이 상태를 결정한다.**



- **객체지향 세계는 현실 세계의 단순한 모방이 아니다.** 소프트웨어 상품은 실제 세계의 상품이 하지 못하는 가격 계산과 같은 행동을 스스로 수행할 수 있다.
  모방이 아니라면 뭐라고 하는게 좋을까? **바로 은유다.**
  **은유란, 실제로는 적용되지 않는 한 가지 개념을 이용해 다른 개념을 서술하는 대화의 형태**
  현실 속의 객체의 의미 일부가 소프트웨어 객체로 전달되기 때문에 프로그램 내의 객체는 현실 속의 객체에 대한 은유다.



- 소프트웨어 객체에 대한 현실 객체의 은유를 효과적으로 사용할 경우 표현적 차이를 줄일 수 있으며, 이를 통해 이해하기 쉽고 유지보수 쉬운 소프트웨어를 만들 수 있다.



- **이상한 나라를 창조하라.**



## 03. 타입과 추상화

- 헤리 벡이 창조한 지하철 노선도의 핵심은 지도가 당연히 가져야 한다고 생각되는 "정확성"을 버리고 노선도의 그 "목적"에 집중한 결과다.
  승객들이 지하철을 이용하는 이유와 헤리 벡이 지형 정보를 제거한 이유는 동일하다.
  역의 위치가 중요한 것이 아니라 역과 역 사이의 연결 관계가 중요했던 것이다.



- 진정한 의미에서 추상화란 현실에서 출발하되 불필요한 부분을 도려내가면서 사물의 본질을 드러나게 하는 과정



- 훌륭한 추상화는 목적에 부합하는 것이어야 한다.



- 추상화
  어떤 양상, 세부사항, 구조를 좀 더 명확하게 이해하기 위해 특정 절차나 물체를 의도적으로 생략하거나 감춤으로써 복잡도를 극복하는 방법
  - 구체적인 사물들로부터 공통점은 취하고 차이점은 버린다(일반화).
  - 중요한 부분을 강조하기 위해 불필요한 세부사항을 제거



- 객체지향 패러다임의 중심에는 구체적이고 실제적인 객체가 존재하지만 수많은 객체들을 개별 단위로 취급하기에는 인간이 지닌 인지능력은 턱없이 부족하다.
  이에 인간은 공통적인 특성을 기준으로 객체를 그룹으로 묶어 동시에 다뤄야 하는 가짓수를 줄여서 상황을 단순화 하려 한다.

  ​
  **공통점을 기반으로 객체들을 묶기 위한 그릇을 개념이라고 한다.**

  일반적으로 우리가 인식하고 있는 다양한 사물이나 객체에 적용할 수 있는 아이디어나 관념



- 개념을 이용하면 객체를 분류할 수 있다.



- 개념의 세 가지 관점
  **개념이 심볼, 내연, 외연으로 구성돼 있다는 사실보다는 개념을 이용해 객체를 분류할 수 있다는 사실이 더 중요하다.**
  - 심볼 (개념의 이름)
    개념을 가리키는 간략한 이름이나 명칭
  - 내연 (개념의 의미)
    개념의 완전한 정의. 내연의 의미를 이용해 객체가 개념에 속하는지 여부를 확인할 수 있다
  - 외연 (개념의 집합)
    개념에 속하는 모든 객체의 집합



- 분류란 객체에 특정한 개념을 적용하는 작업이다.
  어떤 객체를 어떤 개념으로 분류할지가 객체지향의 품질을 결정한다.



- 따라서, 개념은 추상화의 첫 번째 차원인 일반화를 적용한 결과다.



- 타입은 개념이다.
  개념이라는 단어 자체는 이미 우리 생활에서 폭넓게 사용되는 일상 용어다.
  컴퓨터 공학자들은 개념을 좀 더 멋지게 표현할 수 있는 자신들 만의 용어를 갖고 싶었는지 수학에서 차용한 "타입" 이라는 단어를 사용하고 있다.



- 데이터 타입 시스템의 목적
  - 메모리 안의 모든 데이터가 비트열로 보임으로써 야기되는 혼란을 방지
  - 메모리 안에 저장된 0과 1에 대해 수행 가능한 작업과 불가능한 작업을 구분함으로써 데이터가 잘못 사용되는 것을 방지



- 데이터 타입에 관한 중요한 사실 두 가지
  - 데이터 타입은 데이터가 어떻게 사용되느냐에 관한 것이다.
    숫자형 데이터가 숫자형인 이유는 데이터를 더하거나 빼거나 곱하거나 나눌 수 있기 위한 행동 때문이다.
  - 타입에 속한 데이터를 메모리에 어떻게 표현하는지는 외부로부터 철저하게 감춰져 있다.
    개발자는 해당 데이터 타입의 메모리 상 표현 방식을 몰라도 데이터를 사용하는 데 지장이 없다.



- 객체를 타입에 따라 분류하고, 그 타입에 이름을 붙이는 것은 결국 프로그램에서 사용할 새로운 데이터 타입을 선언하는 것과 같다.
  그러나, 객체는 데이터가 아니다. 객체에서 중요한 것은 행동이다.



- 객체 타입에 관한 중요한 사실 두가지
  - 어떤 객체가 어떤 타입에 속하는 지를 결정하는 것은 객체가 수행하는 행동이다.
    어떤 객체들이 동일한 행동을 수행할 수 있다면 그 객체들은 동일한 타입으로 분류될 수 있다.
  - 객체의 내부적인 표현은 외부로부터 철저하게 감춰진다.
    객체의 행동을 가장 효과적으로 수행할 수만 있다면 객체 내부의 상태를 어떤 방식으로 표현하더라도 무방하다.



- 어떤 객체를 다른 객체와 동일한 타입으로 분류하는 기준은 무엇인가?
  동일한 행동을 하기만 하면 된다.
  그 객체가 다른 객체와 동일한 데이터를 가지고 있는지는 관심사가 아니다. 다른 행동을 한다면 그 객체들은 서로 다른 타입으로 분류해야 한다.



- **같은 타입에 속한 객체는 동일한 행동을 함**
  **동일한 행동은 동일한 책임을 의미.**
  **동일한 책임이란 동일한 메시지 수신을 의미.**



- **훌륭한 객체지향 설계는** 외부에 행동만을 제공하고 데이터는 행동 뒤로 감춰야 한다.
  행동에 따라 객체를 분류하기 위해서는 객체가 내부적으로 관리해야 하는 데이터가 아니라 객체가 외부에 제공해야 하는 행동을 먼저 생각해야 한다.

  **외부에 제공해야 하는 책임을 먼저 결정하고, 그 책임을 수행하는 데 적합한 데이터를 나중에 결정한 후, 데이터를 공용 인터페이스 뒤로 캡슐화 해야한다.**



- 흔히 책임-주도 설계 라고 부르는 객체지향 설계 방법은 데이터를 먼저 생각하는 데이터-주도 설계 방법의 단점을 개선하기 위해 고안됐다. [Wirfs-Brock 1989]



- **객체를 결정하는 것은 행동이다. 데이터는 단지 행동을 따를 뿐이다. 이것이 객체를 객체답게 만드는 가장 핵심적인 원칙이다.**



- 일반화/특수화
  - 트럼프: 일반화
  - 트럼프 인간: 특수화
  - 역시, 객체지향에서 일반화/특수화 관계를 결정하는 것은 객체의 상태를 표현하는 데이터가 아니라 행동이다.



- 좀 더 일반적인 타입을 "슈퍼 타입" 이라 하고, 좀 더 특수한 타입을 "서브 타입" 이라고 한다.
  **어떤 타입이 다른 타입의 서브 타입이 되기 위해서는 행위적 호환성을 만족시켜야 한다.**



- 타입은 추상화다. 타입을 이용하면 객체의 동적인 특성을 추상화 할 수 있다.
  즉, 타입은 시간에 다른 객체의 상태 변경이라는 복잡성을 단순화할 수 있는 효과적인 방법인 것이다.



- 객체지향 프로그래밍 언어를 이용해 클래스를 작성하는 시점에는 시스템을 정적인 관점에서 접근하는 것이다.
  실제로 어플리케이션을 실행해 객체의 상태 변경을 추적하고 디버깅하는 동안에는 객체의 동적인 모델을 탐험하고 있는 것이다.



- 타입은 객체를 분류하기 위한 개념.
  **클래스는 단지 타입을 구현하기 위한 여러 구현 메커니즘 중 하나.**
  실제로 자바스크립트와 같은 프로토타입 기반의 언어에는 클래스가 없다!



- 클래스와 타입을 구분하는 것은 설계를 유연하게 유지하기 위한 바탕이 된다. **클래스는 타입의 구현 외에도 코드를 재사용하는 용도로도 사용**되기 때문에 클래스와 타입을 동일시 하는 것은 수많은 오해와 혼란은 불러일으키곤 한다.



- **객체를 분류하는 기준은 클래스가 아니라 타입.**
  **타입을 나누는 기준은 객체가 수행하는 행동.**



## 04. 역할, 책임, 협력

- 1982년 독일 훔볼트 대학 베르너 귀스 교수의 경제학 게임. "최후통첩 게임"
  결론적으로 인간이 어떤 본질적인 특성을 지니고 있느냐가 아니라 어떤 상황에 처해 있느냐가 인간의 행동을 결정한다.
  **인간의 행동을 결정하는 문맥은 타인과의 협력이다.**



- 객체의 세계에서도 협력이라는 문맥이 객체의 행동 방식을 결정한다.



- **객체지향 설계의 전체적인 품질을 결정하는 것은 개별 객체의 품질이 아니다. 협력의 품질이다.**
  비록 그 객체를 따로 떼어놓고 봤을 때는 겉모습이 다소 기묘하고 비합리적이더라도 말이다.



- 책임은 객체지향 설계의 가장 중요한 재료다. 
  어떤 대상에 대한 요청은 그 대상이 그 요청을 처리할 책임이 있음을 암시한다.



- 객체지향 설계의 예술은 적절한 객체에게 적절한 책임을 할당하는 데 있다.



- **책임은 객체의 외부에 제공해 줄 수 있는 정보(아는 것의 측면)와 외부에 제공해 줄 수 있는 서비스(하는 것의 측면)의 목록이다.**



- **책임**이 협력이라는 문맥 속에서 요청을 수신하는 **한 쪽의 객체 관점에서 무엇을 할 수 있는지를 나열**하는 것이라면,
  **메시지**는 협력에 참여하는 **두 객체 사이의 관계를 강조**한 것이다.



- 책임을 결정한 후 실제로 협력을 정제하면서 이를 메시지로 변환할 때는 하나의 여러 메시지로 분할되는 것이 일반적이다.

  > me) 여러 메시지로 분할된다는 것은 한 객체가 책임을 수행하기 위해 다른 객체의 책임을 필요로 해 메시지를 전송한다라는 의미로 들린다.



- 객체지향 설계는 협력에 참여하기 위해 어떤 객체가 어떤 책임을 수행해야 하고, 어떤 객체로부터 메시지를 수신할 것인지를 결정하는 것으로부터 시작된다.
  어떤 클래스가 필요하고 어떤 메서드를 포함해야 하는지를 결정하는 것은 책임과 메시지에 대한 대략적인 윤곽을 잡은 후에 시작해도 늦지 않다!



- **책임의 집합**이 의미하는 것. **역할**
  어떤 객체가 수행하는 책임의 집합은 객체가 협력 안에서 수행하는 역할을 암시한다.
  **역할은 재사용 가능하고, 유연한 객체지향 설계를 낳는 매우 중요한 구성요소이다.**



- **역할은 협력 내에서 다른 객체로 대체 가능함을 의미한다.**



- 역할을 대체하기 위해서는 각 역할이 수신할 수 있는 메시지를 동일한 방식으로 이해해야 한다.
  **즉, 역할을 대체할 수 있는 객체는 동일한 메시지를 이해할 수 있는 객체로 한정된다.**



- **역할의 가장 큰 가치는 협력을 추상화 할 수 있다는 것이다!**



- **역할의 대체 가능성은 행위 호환성을 의미하고, 행위 호환성은 동일한 책임 수행을 의미한다.**



- 객체지향과 관련한 흔한 오류
  - 많은 사람들이 시스템에 필요한 데이터를 저장하기 위해 객체가 존재한다는 선입견
    객체가 물론 상태의 일부로 데이터를 포함하는 것은 사실이지만 어디까지나 데이터는 단지 객체의 행위를 수행하는 데 필요한 재료일 뿐이다.
  - 객체지향은 클래스와 클래스 사이의 관계를 표현하는 시스템의 정적인 측면에 중점을 둔다는 선입견
    중요한 것은 정적인 클래스가 아니라, 협력에 참여하는 동적인 객체이다.
    클래스는 단지 시스템에 필요한 객체를 표현하고 생성하기 위해 프로그래밍 언어가 제공하는 구현 메커니즘일 뿐이다.



- 올바른 객체를 설계하기 위해서는 먼저 견고하고 깔끔한 협력을 설계해야 한다.
  협력을 설계한다는 것은 객체들이 주고 받은 요청과 응답의 흐름을 결정하다는 것을 의미한다.



- **객체지향 시스템에서 가장 중요한 것은 충분히 자율적인 동시에 충분히 협력적인 객체를 창조하는 것이다.**
  이 목표를 달성하기 위한 가장 쉬운 방법은 객체를 충분히 협력적으로 만든 후에 협력이라는 문맥 안에서 객체를 충분히 자율적으로 만드는 것이다.



- **책임-주도 설계**에 관하여
  협력에 필요한 책임들을 식별하고 적합한 객체에게 책임을 할당하는 방식으로 애플리케이션을 설계한다.
  **시스템의 책임을 객체의 책임으로 변환하고, 각 객체가 책임을 수행하는 중에 필요한 정보나 서비스를 제공해줄 협력자를 찾아 해당 협력자에게 책임을 할당하는 순차적인 방식으로 객체들의 협력 공동체를 구축한다.**



- **테스트-주도 개발**에 관하여
  테스트-주도 개발의 핵심은 테스트 작성이 아니다.
  테스트는 단지 테스트-주도 개발을 통해 얻을 수 있는 별도의 보너스이다.
  실제 목적은 구체적인 코드를 작성해나가면서 역할, 책임, 협력을 식별하고 적절한지 피드백 받는 것이다.

  **책임을 수행할 객체 또는 클라이언트가 기대하는 객체의 역할이 메시지를 수신할 때 어떤 결과를 반환하고 그 과정에서 어떤 객체의 역할이 메시지를 수신할 때 어떤 결과를 반환하고 그 과정에서 어떤 객체와 협력할 것인지에 대한 기대를 코드의 형태로 작성하는 것.**



- **디자인 패턴**에 관하여
  패턴은 전문가들이 특정 문제를 해결하기 위해 이미 식별해 놓은 역할, 책임, 협력의 모음이다.
  패턴을 안다면 바퀴를 반복적으로 발명할 필요가 없다.
  **디자인 패턴이란 반복해서 일어나는 특정한 상황에서 어떤 설계가 왜 더 효과적인지에 대한 이유를 설명하는 것이다.**



## 05. 책임과 메시지

- 객체가 어떤 행동을 하는 유일한 이유는 다른 객체로부터 요청을 수신했기 때문이다.
  요청을 처리하기 위해 객체가 수행하는 행동을 "책임"이라 한다.



- 포괄적이고 추상적이면서도 해야 할 일을 명확히 지시하는 책임을 만들라.
  객체가 자율적이기 위해서는 책임의 수준 역시 자율적이어야 한다.

  그렇다고 너무 추상적이면 안된다.
  책임은 협력에 참여하는 의도를 명확하게 설명할 수 있는 수준 안에서 추상적이어야 한다.



- **'어떻게'가 아니라 '무엇'을**
  자율적인 책임의 특징은 객체가 '어떻게'해야 하는가가 아니라 '무엇'을 해야 하는가를 설명한다는 것이다.



- 객체가 수신할 수 있는 메시지의 모양이 객체가 수행할 책임의 모양을 결정한다.



- 객체가 유일하게 이해할 수 있는 의사소통 수단은 "메시지"
  객체는 메시지를 처리하기 위한 방법(메서드)을 자율적으로 선택할 수 있다.



- 다형성은 역할, 책임, 협력과 깊은 관련이 있다.
  서로 다른 객체들이 다형성을 만족시킨다는 것은 그 객체들이 동일한 책임을 공유한다는 것을 의미.

  그리고, 다형성에서 중요한 점은 메시지 송신자의 관점이다.
  오퍼레이션을 다른 방식으로 처리하더라도 메시지 송신자 관점에서는 이 객체들은 동일한 책임을 수행하는 것이다.



- 기본적으로 다형성은 대체 가능성을 의미한다.
  대체 가능한 이유는 동일한 메시지를 처리할 수 있기 때문이다. 비록 메시지를 처리하는 방법인 메서드는 달라지더라도.



- 객체지향이 강력한 이유는 다형성을 이용해 협력을 유연하게 만들 수 있기 때문이다.



- 유연하고 확장 가능하고 재사용성이 높은 협력의 의미
  1. 송신자는 수신자가 메시지를 이해한다면 누구라도 상관하지 않는다. 대체가 쉽다.
  2. 송신자에게 아무런 영향도 않고서도 수신자를 교체할 수 있으므로 확장이 쉽다.
  3. 협력이 수행되는 방식을 재사용



- 메시지는 송신자와 수신자 사이의 결합도를 낮춤으로써, 설계를 유연하고, 확장 가능하고, 재사용 가능하게 만든다.



- 클래스가 코드를 구현하기 위해 사용할 수 있는 중요한 추상화 도구인 것은 사실이지만
  객체지향의 강력함은 클래스가 아니라 객체들이 주고받는 메시지로부터 나온다.
  **객체지향 애플리케이션은 클래스를 이용해 만들어지지만 메시지를 통해 정의된다.**



- 클래스는 단지 추상화 도구일 뿐이다.
  객체들의 책임을 먼저 협력으로 엮고, 속성과 행위를 식별하는 것이 먼저다.
  클래스는 속성과 행위를 담는 틀일 뿐이다.



- **진정한 객체지향 패러다임으로의 도약은 개별적인 객체가 아니라 메시지를 주고 받는 객체들 사이의 커뮤니케이션에 초점을 맞출 때 일어난다.**




