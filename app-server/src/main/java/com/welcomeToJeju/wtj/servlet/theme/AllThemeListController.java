package com.welcomeToJeju.wtj.servlet.theme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.welcomeToJeju.wtj.dao.PublicThemeDao;
import com.welcomeToJeju.wtj.domain.Theme;

@WebServlet("/theme/list")
public class AllThemeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  PublicThemeDao publicThemeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    publicThemeDao = (PublicThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {

      Collection<Theme> themeList = publicThemeDao.findAllPublicTheme();

      request.setAttribute("allThemeList", themeList);

      request.setAttribute("pageTitle", "전체테마 리스트");
      request.setAttribute("contentUrl", "/theme/AllThemeList.jsp");

      request.getRequestDispatcher("/template_main.jsp").forward(request, response);


    } catch (Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }


  }
}