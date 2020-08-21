cmake_minimum_required(VERSION 3.4.1)
add_library( StoreUtil SHARED src/main/cpp/StoreUtil.cpp )
add_library( Store     SHARED src/main/cpp/Store.cpp src/main/cpp/StoreUtil.cpp )
