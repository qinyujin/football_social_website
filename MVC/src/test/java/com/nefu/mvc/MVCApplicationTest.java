package com.nefu.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.mvc.component.TransferJson;
import com.nefu.mvc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:44:00
 */
@SpringBootTest
class MVCApplicationTest {

    @Autowired
    private TransferJson transferJson;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test1(){
        User user = new User(1,"赵四","001","1234","D://xxxx");
        String s = transferJson.transferToJson(user);
        System.out.println(s);
        User o = (User) transferJson.transferToClz(s, User.class);
        System.out.println(o);
    }

    @Test
    void test2(){
        String res = "[{\"id\":3,\"name\":\"张三\",\"num\":\"001\",\"password\":\"STbffk4gTxz4KRmT8fCUfFgPKcs4m5soLhtcKhCViC6BYAH5tw6Hulm+BHYVsBEuHvfqeTaekK9WvcKVVtLhWK7KRRmbcrKL2I7CMajM+xwKYvCvyPz3D4XF7u6OMhCyQiIAaSPQSh3JORV9vvvtuQ/AhJzo1yvvvfui0pmku58=\",\"icon\":\"0213\"},{\"id\":4,\"name\":\"王雷\",\"num\":\"002\",\"password\":\"RoW4qvz0SDfKywQyyO8cWmx4VhH8hHDUutxYbFeNAruHw2cm2MK5mbaBnQNbhs82TEyQdQs7T38Du25JWhF4UVQGYS3zn7OyGXCjJ6tupnIg+HwB+8cOd6TFcIZFm8WalN8Vb/9LkLV3Glhe/uU92OOuaByI0wOwasseAz4nBGI=\",\"icon\":\"D://xxx\"}]";
        List<User> o = (List<User>) transferJson.transferToClz(res, List.class);
        System.out.println(o);
        System.out.println(o.size());
        //不能使用增强for和迭代器
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i));
        }
    }
}