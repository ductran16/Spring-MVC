package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.FlowerDTO;
import com.laptrinhjavaweb.service.IFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "flowerController")
public class FlowerController {

    @Autowired
    private IFlowerService flowerService;

    @RequestMapping(value = "/quan-tri/hoa/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit, HttpServletRequest request){

        FlowerDTO model = new FlowerDTO();
        model.setPage(page);
        model.setLimit(limit);

        ModelAndView mav = new ModelAndView("admin/flower/list");

        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(flowerService.findAll(pageable));
        model.setTotalItem(flowerService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
//        if (request.getParameter("message") != null) {
//            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
//            mav.addObject("message", message.get("message"));
//            mav.addObject("alert", message.get("alert"));
//        }
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/hoa/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editFlower(){
        ModelAndView mav = new ModelAndView("admin/flower/edit");
        return mav;
    }
}
