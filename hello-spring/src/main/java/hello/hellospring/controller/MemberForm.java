package hello.hellospring.controller;

public class MemberForm {
    private String name;    //createMemberForm.html에서 value name이 "name"이었기 때문에 동일하게 name으로 작성

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
