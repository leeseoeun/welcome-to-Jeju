package com.welcomeToJeJu.wtj.handler;

import java.sql.Date;
import java.util.List;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserAddHandler extends AbstractUserHandler{
  public UserAddHandler(List<User> userList) {
    super(userList);
    //    User rootUser = new User();
    //    rootUser.setNo(0);
    //    rootUser.setEmail("root@test.com");
    //    rootUser.setPassword("0000");
    //    rootUser.setNickName("μ μ£Όμ μΉπ");
    //    rootUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //    userList.add(rootUser);

    //	    User testUser = new User();
    //	    testUser.setNo(2);
    //	    testUser.setName("111");
    //	    testUser.setId("111");
    //	    testUser.setEmail("111");
    //	    testUser.setPassword("111");
    //	    testUser.setTel("010-0000-0000");
    //	    testUser.setNickName("μμΈμ²μ¬");
    //	    testUser.setBookMarks(new ArrayList<>());
    //	    testUser.setThemeList(new ArrayList<>());
    //
    //	    userList.add(testUser);
    //
    //	    User testUser2 = new User();
    //	    testUser2.setNo(3);
    //	    testUser2.setName("222");
    //	    testUser2.setId("222");
    //	    testUser2.setEmail("222");
    //	    testUser2.setPassword("222");
    //	    testUser2.setTel("010-0000-1111");
    //	    testUser2.setNickName("μμΈμ΄λ");
    //	    testUser2.setBookMarks(new ArrayList<>());
    //	    testUser2.setThemeList(new ArrayList<>());
    //
    //	    userList.add(testUser2);



  }

  public void execute(CommandRequest request) {

    System.out.println("[νμ κ°μνκΈ°]");

    User user = new User();
    int userNo = 0;

    user.setEmail(Prompt.inputString("μ΄λ©μΌ > "));
    user.setNickName(Prompt.inputString("λλ€μ > "));
    user.setPassword(Prompt.inputString("μνΈ > "));
    user.setRegisteredDate(new Date(System.currentTimeMillis()));
    userNo = userList.get(userList.size()-1).getNo();
    user.setNo(++userNo);

    userList.add(user);

    System.out.println("νμ κ°μ μλ£!");
  }

}
