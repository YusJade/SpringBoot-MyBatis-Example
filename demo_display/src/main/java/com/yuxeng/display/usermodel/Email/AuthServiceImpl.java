package com.yuxeng.display.usermodel.Email;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateConfig.ResourceMode;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

  private final EmailService emailService;

  @Override
  public void sendEmailCode(String email) {
    // 获取Html模板——从./template
    TemplateEngine engine = TemplateUtil.createEngine(
        new TemplateConfig("./template", ResourceMode.CLASSPATH));
    Template template = engine.getTemplate("Email-Code.html");
    // 产生6位随机数，并放入缓存(其实我也不在缓存里加载，验证的时候用一下，但不会重新发送一样的)
    Object code = RandomUtil.randomNumbers(6);

    // 发送验证码——设置html的code
    emailService.sendEmail(new EmailDto(Collections.singletonList(email), "Your Code", template.render(
        Dict.create().set("code",code))));


  }

}
