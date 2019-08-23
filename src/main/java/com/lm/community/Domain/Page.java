package com.lm.community.Domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class Page {

    //每个模块展示7个分页按钮，模块分为总按键数%7==0?按键数/7:按键数/7+1
    //如果11条数据，以当前的条件进行分页，分为2大模块
    //如何判断当前页数属于哪一个模块？1-7页属于第一模块，8-14页属于第二模块，以此类推
    //for(int i = 1;i<模块数;i++){int m =i*7;if(当前页==m){第i模块}。。。}
    //第一模块，
    //中间模块
    //最后模块
    private Integer page; //当前页
    private Integer beginpage;//起步查询页
    private Integer size;//每页显示条数
    private Integer countsize;//总按键数
    private Integer count;//总条数
    private boolean haveNextPage;//是否有下一页
    private boolean haveEndPage;//是否有最后一页
    private boolean havePrePage;//是否有上一页
    private boolean haveFirstPage;//是否有第一页
    private List<Integer> pages = new ArrayList<>();

    public void setData(Integer page, Integer size,Integer count) {
        this.page=page; //当前页
        this.beginpage = (page-1)*size; //开始查询页
        this.size = size; //每页显示
        this.count = count; //总条数
        //总页数=总条数%每页显示条数+1
        this.countsize = count % this.size ==0?(count / this.size):(count / this.size)+1;

        pages.add(page);
        for(int i =1;i<=3;i++){
            if(page-i > 0){
                pages.add(0,page-i);
            }
            if(page+i <= countsize){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if(page == 1){
            havePrePage = false;
        }else{
            havePrePage = true;
        }

        //是否展示下一页
        if(page == countsize){
            haveNextPage = false;
        }else{
            haveNextPage = true;
        }

        //是否展示首页
        if(pages.contains(1)){
            haveFirstPage = false;
        }else{
            haveFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(countsize)){
            haveEndPage = false;
        }else{
            haveEndPage = true;
        }
    }
}
