/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName LoginController.java
 */

package com.cloud.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constant.WebConstant;
import com.cloud.base.controller.BaseController;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.AccountType;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.util.BaseRedisUtils;
import com.cloud.base.util.BaseStringUtils;
import com.cloud.config.AccountToken;
import com.cloud.entity.LoginVo;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.service.account.AdminServiceApi;
import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * 登录controller
 * @Author zivin
 * @Date 2017年9月19日
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private BaseRedisUtils baseRedisUtils;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private AdminServiceApi adminServiceApi;

    /**
     * VERIFICATION_CODE 验证码标记
     */
    private static final String VERIFICATION_CODE = "verificationCode";

    /**
     * 登录
     * @param httpServletRequest
     * @param loginVo
     * @return
     */
    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(HttpServletRequest httpServletRequest, @RequestBody LoginVo loginVo) {
        try {
            // 校验验证码
            String verificationCode = (String) baseRedisUtils
                    .get(BaseRedisUtils.generateKey(VERIFICATION_CODE, loginVo.getVerificationId()));
            if (BaseStringUtils.isBlank(verificationCode)) {
                return BaseResult.failure(BaseResultCode.VALIDATION_EXPIRED);
            }
            if (!verificationCode.equalsIgnoreCase(loginVo.getVerificationCode())) {
                return BaseResult.failure(BaseResultCode.INVALID_VALIDATION);
            }
        } catch (Exception e) {
            logger.error("验证码校验失败:", e);
            return BaseResult.failure("验证码校验失败");
        }

        // 校验账号
        if (StringUtils.isBlank(loginVo.getAccount())) {
            return BaseResult.failure("账号不允许为空");
        }

        try {
            AdminVo adminVo = adminServiceApi.getAdminByAccount(loginVo.getAccount());
            if (adminVo == null) {
                return BaseResult.failure(BaseResultCode.ACCOUNT_UNUSABLE);
            }
            // 登录
            Subject subject = SecurityUtils.getSubject();
            AccountToken token = new AccountToken(loginVo.getAccount(), adminVo.getSalt() + loginVo.getPassword(),
                    AccountType.ADMIN.getCode());
            // 进行身份认证
            subject.login(token);
            JSONObject data = new JSONObject();
            data.put("token", subject.getSession().getId());
            return BaseResult.success(data);
        } catch (LockedAccountException e) {
            return BaseResult.failure(BaseResultCode.ACCOUNT_UNUSABLE);
        } catch (AuthenticationException e) {
            return BaseResult.failure(BaseResultCode.LOGIN_ERROR);
        }
    }

    /**
     * 获取验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping(value = "/public/getVerificationCode", method = RequestMethod.GET)
    public void getVerificationCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            String verificationId) {
        try {
            byte[] captchaChallengeAsJpeg = null;
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            // 生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            baseRedisUtils.set(BaseRedisUtils.generateKey(VERIFICATION_CODE, verificationId), createText, 600000l);

            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            jpegOutputStream.close();

            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (Exception e) {
            logger.error("获取验证码失败:", e);
        }
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult logout(@RequestHeader(WebConstant.X_TOKEN) String token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            // 删除用户信息
            baseRedisUtils.removeAdmin(token);
            // 删除用户权限
            baseRedisUtils.removeMenu(token);
            return BaseResult.success();
        } catch (Exception e) {
            logger.error("登出失败:", e);
            return BaseResult.failure("登出失败");
        }
    }

}
