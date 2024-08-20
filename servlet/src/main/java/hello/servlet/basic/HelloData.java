package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//롬복으로 getter, setter 세팅
@Getter @Setter
public class HelloData {
    private String username;
    private int age;

}
