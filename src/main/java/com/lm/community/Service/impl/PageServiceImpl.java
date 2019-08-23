package com.lm.community.Service.impl;

import com.lm.community.Dao.LaunchDao;
import com.lm.community.Dao.PageDao;
import com.lm.community.Domain.Page;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("pageService")
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;
    @Autowired
    private LaunchDao launchDao;


    /**
     * 查询该用户下的所有问题
     * @param page
     * @param size
     * @param model
     * @return
     */
    @Override
    public List<Question> findQuestionByUserId(Integer page, Integer size, Model model, HttpServletRequest request) {
        //获取Session中的用户ID
        SaveSession user = (SaveSession) request.getSession().getAttribute("user");
        if(user!=null){
            String userName = user.getName();
            //查询总数
            Integer count = pageDao.findQuestionCountByUid(userName);
            Page pagetext = new Page();
            pagetext.setData(page,size,count);
            model.addAttribute("pages",pagetext);
            System.out.println(pagetext);
            //把当前页和总页数传进domain工具中进行判断处理
            return launchDao.findAllQuestionByLimitName(userName,pagetext.getBeginpage(),pagetext.getSize());
        }
        return null;
    }

    /**
     * 根据id查询指定文章
     * @param id
     * @return
     */
    @Override
    public Question findQuestionById(Integer id) {
        return pageDao.findQuestionById(id);
    }
}
