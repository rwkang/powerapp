package shop.onekorea.powerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.powerapp.dto.ResponseDto;
import shop.onekorea.powerapp.entity.MenuEntity;
import shop.onekorea.powerapp.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired private MenuService menuService;

    @GetMapping("/search/{userid}")
    public ResponseDto<List<MenuEntity>> doGetSearchList(@PathVariable("userid") String userid) {
        System.out.println("MenuController.java.userid: ${userid}");

        return menuService.serviceGetSearchList(userid);

    }

}
