cmake_minimum_required(VERSION 3.4.1)

add_library(
        message-native

        SHARED

        message-native.cpp)  # Убедитесь, что имя файла указано правильно

find_library(
        message-native

        log)

target_link_libraries(
        message-native

        ${log-lib})