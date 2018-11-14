package com.library.spring.conditional;

/**
 * Created by feifei.liu on 2017/3/29.
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
