package generic;


/*
* T는 제네릭과 관련된 부분
* 제네릭은 클래스 이름 뒤나, 메소드 return 타입으로 지정할 수 있다.
* 꼭 T를 안써도 되고 E,D 등등 쓸면 된다
* 어차피 Type의 줄임말이기 때문에
* */
public class GenericBox<T>{

    private T t;

    public void add(T obj) {
        this.t = obj;
    }

    public T get() {
        return this.t;
    }
}
