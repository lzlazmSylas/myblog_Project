package com.lzl.web;

import com.lzl.po.Type;
import com.lzl.service.BlogService;
import com.lzl.service.TypeService;
import com.lzl.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeListController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @GetMapping("/types/{id}")
    public String listType(@PageableDefault(size=8,sort={"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable
            , @PathVariable Long id
            ,Model model){
        List<Type> types = typeService.listTypeTop(1000);
        if (id == -1){
            id= types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeType", id);

        return "category";
    }
}
