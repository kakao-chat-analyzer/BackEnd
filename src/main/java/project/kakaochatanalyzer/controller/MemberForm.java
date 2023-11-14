package project.kakaochatanalyzer.controller;

public class MemberForm {
    /* HTML의 인자 값 name="name" 이랑 같이 매핑이 된다.
    -> spring이 setname으로 설정해준다. 그럼 우린 getname으로 꺼내오면 된다.  modifiy2*/
    private String userId; // 아이디
    private String userPw; // 비밀번호
    private String userName; // 사용자 이름
    private String userEmail; // 사용자 이메일

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
