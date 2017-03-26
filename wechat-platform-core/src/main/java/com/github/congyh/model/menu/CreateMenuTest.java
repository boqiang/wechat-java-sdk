package com.github.congyh.model.menu;

import com.google.gson.Gson;

import static java.lang.System.out;

/**
 * 测试生成创建菜单的字符串
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class CreateMenuTest {
    public static void main(String[] args) {
        ClickButton cbtn1 = new ClickButton();
        cbtn1.setName("今日问候");
        cbtn1.setType("click");
        cbtn1.setKey("V1001_GREETINGS");

        ViewButton vbtn1 = new ViewButton();
        vbtn1.setName("团队主页");
        vbtn1.setType("view");
        vbtn1.setUrl("http://congyh.com");

        ClickButton bws1 = new ClickButton();
        bws1.setName("加入我们");
        bws1.setType("click");
        bws1.setKey("V1001_JOIN_US");

        ClickButton bws2 = new ClickButton();
        bws2.setName("给我们点赞");
        bws2.setType("click");
        bws2.setKey("V1001_THUMB_UP");

        ButtonWithSubbuttons bws = new ButtonWithSubbuttons();
        bws.setName("更多");
        bws.setButtons(new Button[] {bws1, bws2});

        Menu menu = new Menu();
        menu.setButtons(new Button[] {cbtn1, vbtn1, bws});

        String jsonMenu = new Gson().toJson(menu);
        out.println(jsonMenu);
    }
}
