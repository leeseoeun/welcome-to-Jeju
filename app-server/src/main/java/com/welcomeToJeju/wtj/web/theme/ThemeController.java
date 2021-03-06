package com.welcomeToJeju.wtj.web.theme;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.wtj.dao.PublicThemeDao;
import com.welcomeToJeju.wtj.dao.ShareThemeDao;
import com.welcomeToJeju.wtj.dao.UserDao;
import com.welcomeToJeju.wtj.domain.Theme;
import com.welcomeToJeju.wtj.domain.User;

@Controller
public class ThemeController {

  @Autowired PublicThemeDao publicThemeDao;
  @Autowired ShareThemeDao shareThemeDao;
  @Autowired UserDao userDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/theme/list")
  public ModelAndView allThemeList() throws Exception {
    Collection<Theme> publicThemeList = publicThemeDao.findAllPublicTheme();
    Collection<Theme> shareThemeList = shareThemeDao.findAllShareTheme();

    ModelAndView mv = new ModelAndView();
    mv.addObject("publicThemeList", publicThemeList);
    mv.addObject("shareThemeList", shareThemeList);
    mv.addObject("pageTitle", "전체 테마 보기");
    mv.addObject("contentUrl", "theme/AllThemeList.jsp");
    mv.setViewName("template_main");

    return mv;
  }

  @GetMapping("/user/themelist")
  public ModelAndView userThemeList(int no) throws Exception {
    Collection<Theme> publicThemeList = publicThemeDao.findAllPublicThemeByUserNo(no);
    Collection<Theme> shareThemeList = shareThemeDao.findAllShareThemeByUserNo(no);
    User user = userDao.findByNo(no);

    userDao.updateViewCount(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("publicThemeList", publicThemeList);
    mv.addObject("shareThemeList", shareThemeList);
    mv.addObject("user", user);
    mv.addObject("pageTitle", "유저 테마 목록 보기");
    mv.addObject("contentUrl", "theme/UserThemeList.jsp");
    mv.setViewName("template_main");

    return mv;
  }


}
