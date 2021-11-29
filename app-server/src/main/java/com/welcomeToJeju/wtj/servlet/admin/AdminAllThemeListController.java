package com.welcomeToJeju.wtj.servlet.admin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.wtj.dao.PublicThemeDao;
import com.welcomeToJeju.wtj.domain.Theme;

//@Controller
public class AdminAllThemeListController {

  @Autowired PublicThemeDao publicThemeDao;

  //@GetMapping("/admin/alltheme")
  public ModelAndView list() throws Exception {

    Collection<Theme> themeList = publicThemeDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("allThemeList", themeList);
    mv.addObject("pageTitle", "전체테마 리스트");
    mv.addObject("contentUrl", "admin/AdminThemeList.jsp");
    mv.setViewName("template_main");

    return mv;
  }
}
