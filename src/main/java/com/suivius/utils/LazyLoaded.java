package com.suivius.utils;


public  interface LazyLoaded<T> {

public  void setLoadService(T arg0);

public  void load();
}