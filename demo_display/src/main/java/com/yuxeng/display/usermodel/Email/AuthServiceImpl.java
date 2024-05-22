package com.yuxeng.display.usermodel.Email;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateConfig.ResourceMode;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {


  @Override
  public void sendEmailStart(String email) {
    // 获取Html模板
    TemplateEngine engine = TemplateUtil.createEngine(
        new TemplateConfig("template", ResourceMode.CLASSPATH));
  }

}
