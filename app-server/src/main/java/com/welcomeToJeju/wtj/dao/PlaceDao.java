package com.welcomeToJeju.wtj.dao;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.wtj.domain.Place;

public interface PlaceDao {

  void insert(Place place) throws Exception;
  void insertPhoto(HashMap<String,Object> param) throws Exception;
  void insertComment(HashMap<String,Object> param) throws Exception;
  void insertPlaceUserTheme(HashMap<String,Object> param) throws Exception;

  void delete(HashMap<String,Object> param) throws Exception;
  void deletePhoto(String id) throws Exception;
  void deleteComment(String id) throws Exception;

  List<Place> findAllByThemeNo(int themeNo) throws Exception;
  List<Place> findTop10() throws Exception;

  Place findByPlaceId(String id) throws Exception;


}