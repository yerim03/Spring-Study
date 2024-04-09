package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//회원 정보를 담는 객체
@Entity //jpa가 관리하는 entity라는 의미
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType가 IDENTITY면 DB가 pk를 알아서 설정해준다.
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
