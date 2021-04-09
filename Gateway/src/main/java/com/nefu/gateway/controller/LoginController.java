package com.nefu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:50:00
 * 先校验验证码是否正确，再按照账号、密码的顺序进行校验，校验成功的话，把uid、rids封装到token返回给前端，前端之后通过
 * 在header里携带来表示登陆状态
 */
@RestController
@RequestMapping("/api/")
@Slf4j
public class LoginController {
/*
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${privateKey}")
    private String privateKey;

    @Value("${publicKey}")
    private String publicKey;

    private final String VERIFYCODE = "VerifyCodeList";

    //通过微服务来进行调用
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRoleServiceImpl userRoleServiceImpl;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    public CommonResult login(@RequestBody User user, @RequestParam("verifyCode") String code) {
        //检测验证码、账号和密码来进行登陆
        boolean passVerify = false;
        Set<Object> codeList = redisUtil.sGet(VERIFYCODE);
        Iterator<Object> iterator = codeList.iterator();
        while (iterator.hasNext()) {
            String vCode = (String) iterator.next();
            String c = vCode.substring(0, vCode.length() - 13);
            String time = vCode.substring(4);
            Long t = Long.valueOf(time);
            if (code.equalsIgnoreCase(c) && (System.currentTimeMillis() - t) / 1000 < 300) {
                passVerify = true;
            }
        }
        if (!passVerify) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "验证码错误或者过期，请重试！");
        }
        User u = userServiceImpl.getUserByNum(user.getNum());
        if (u == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户不存在，请重试");
        try {
            String realPwd = RSAUtil.decrypt(u.getPassword(), privateKey);
            //通过把用户的token封装到jwt中来实现登陆状态的认证
            MyToken t = new MyToken(user.getId(), userRoleServiceImpl.getRidsByUid(user.getId()));
            //把自定义的token封装为json格式
            String json = objectMapper.writeValueAsString(t);
            //对json格式再进行加密，防止被拦截之后以明文形式存在
            String secreteJson = RSAUtil.encrypt(json, publicKey);
            String token = JWT.create()
                    //签发时间
                    .withIssuedAt(new Date())
                    //设置token两小时过期
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
                    //传输的内容
                    .withAudience(secreteJson)
                    .sign(Algorithm.HMAC256(publicKey));
            if (realPwd.equals(user.getPassword())) return new CommonResult(200,"登陆成功，返回token",token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult(401,"密码错误");
    }*/
}
