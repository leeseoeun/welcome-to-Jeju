package com.welcomeToJeJu.wtj.handler;

import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeJu.util.Prompt;
import com.welcomeToJeJu.wtj.domain.Theme;
import com.welcomeToJeJu.wtj.domain.User;

public class MyThemeUpdateHandler extends AbstractMyThemeHandler {

  public MyThemeUpdateHandler(List<User> userList) {
    super(userList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[테마 수정하기]");

    Theme theme = (Theme) request.getAttribute("searchedTheme");
    int categoryNum;

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    String newTitle = Prompt.inputString("테마 제목 > ");

    // 카테고리
    List<String> categories = new ArrayList<>();
    categories.add("식당");
    categories.add("카페");
    categories.add("관광명소");
    categories.add("기타");
    while (true) {
      int index = 1;
      for(String category : categories) {
        System.out.printf("%d. %s ",index++,category);
      }
      System.out.println();
      categoryNum = Prompt.inputInt("카테고리 번호 > ");
      if(categoryNum > categories.size() || categoryNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      System.out.println();
      break;
    }

    // 해시태그
    List<String> hashtagList = new ArrayList<>();
    while (true) {
      String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
      if (input.length() == 0)
        break;
      hashtagList.add(input);
    }

    // 공개/비공개
    boolean isPublic = false;
    String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      isPublic = true;
    }

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 수정 취소!");
      return;
    }

    theme.setTitle(newTitle);
    theme.setHashtags(hashtagList);
    theme.setCategory(categories.get(categoryNum-1));
    theme.setPublic(isPublic);
    System.out.println();
    System.out.println("테마 수정 완료!");
  }


}