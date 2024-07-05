package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//롬복이 있으면, @Getter, Setter 애노테이션을 보고 자동으로 getter, setter 함수를 생성해준다.
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfg");

        System.out.println("helloLombok = " + helloLombok);
    }
}
