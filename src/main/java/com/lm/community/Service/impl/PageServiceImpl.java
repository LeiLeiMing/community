package com.lm.community.Service.impl;

import com.lm.community.Dao.LaunchDao;
import com.lm.community.Dao.PageDao;
import com.lm.community.Domain.Page;
import com.lm.community.Domain.Question;
import com.lm.community.Domain.SaveSession;
import com.lm.community.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pageService")
@Transactional
public class PageServiceImpl extends PageService {

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

    @Override
    public void updateViewCount(Integer id) {
        pageDao.updateViewCount(id);
    }

    @Override
    public List<Question> findSimleQuestion(String[] tags, Integer id) {
        Map<String,List<Question>> map = new HashMap<>();
        List<Question> list = new ArrayList<>();
        //开始查询与标签集合有关的问题
        for(String tag : tags){
            List<Question> simleQuestion = pageDao.findSimleQuestion(tag,id);
            map.put(tag,simleQuestion);
        }
        //放进list集合，方便到前端处理
        if(map!=null&&map.size()!=0){
            for(List<Question> value : map.values()){
                for(Question question1 : value){
                    list.add(question1);
                }
            }
        }

        //去重
        if(list!=null&&list.size()!=0){
            for(int i = 0;i<=list.size()-1;i++){
                for(int j = i+1;j<list.size();j++){
                    if(list.get(i).getId().equals(list.get(j).getId())){
                        list.remove(j);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void editQuestionById(Question question) {
        pageDao.editQuestionById(question);
    }

    /**
     * 热门问题
     * @return
     */
    @Override
    public List<Question> findHostQuestions() {
        return pageDao.findHostQuestions();
    }

    /**
     * 最新问题
     * @return
     */
    @Override
    public List<Question> findNewQuestion() {
        return pageDao.findNewQuestion();
    }

    @Override
    public List<Question> searchQuestion(String search) {
        return pageDao.searchQuestion(search);
    }

    /**
     * 更新点赞数
     * @param id
     */
    @Override
    public void updateLikecount(Integer id) {
        pageDao.updateLikecount(id);
    }


    @Override
    public Integer likecount(Integer questionid) {
        return pageDao.likecount(questionid);
    }

    @Override
    public List<Question> findAllHotQuestionByLimit(Integer page, Integer size,Model model) {
        Page pagetext = new Page();
        //热门总条数
        Integer count = this.findHotQuestionCount();
        pagetext.setData(page,size,count);
        model.addAttribute("pages",pagetext);
        return pageDao.findAllHotQuestionByLimit(pagetext.getBeginpage(),pagetext.getSize());
    }

    @Override
    public Integer findHotQuestionCount() {
        return pageDao.findHotQuestionCount();
    }
}
