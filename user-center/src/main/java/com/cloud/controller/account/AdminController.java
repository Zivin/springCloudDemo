/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName AdminController.java
 */

package com.cloud.controller.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.base.controller.BaseFormController;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.entity.account.PasswordVo;
import com.cloud.entity.account.admin.Admin;
import com.cloud.entity.account.admin.AdminQo;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.service.account.AdminService;

/**
 * 管理员管理controller
 * @Author zivin
 * @Date 2017年10月8日
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseFormController<Admin, AdminQo, AdminVo> {

    @Autowired
    private AdminService adminService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected AdminService getService() {
        return adminService;
    }

    /**
     * 登录用户获取用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/getAdmin", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getAdmin() {
        try {
            AdminVo adminVo = (AdminVo) getCurrentAdmin();
            if (null == adminVo) {
                return BaseResult.failure(BaseResultCode.OVERDUE_TOKEN);
            }
            // 重新在数据库中查询用户信息，刷新缓存
            adminVo = adminService.get(adminVo.getId());
            setCurrentAdmin(adminVo);

            return BaseResult.success(adminVo);
        } catch (Exception e) {
            logger.error("登录用户获取用户信息失败", e);
            return BaseResult.failure("获取用户信息失败");
        }
    }

    /**
     * 登录用户编辑保存个人信息
     * @param adminVo
     * @return
     */
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateAdmin(@RequestBody AdminVo adminVo) {
        try {
            AdminVo admin = (AdminVo) getCurrentAdmin();
            if (!admin.getId().equals(adminVo.getId())) {
                return BaseResult.failure("非本人不允许编辑个人账户信息");
            }
            adminVo.setUpdateTime(new Date());
            adminVo.setUpdateUserId(admin.getId());
            return adminService.update(adminVo);
        } catch (Exception e) {
            logger.error("登录用户编辑保存个人信息失败", e);
            return BaseResult.failure("登录用户编辑保存个人信息失败");
        }
    }

    /**
     * 修改密码
     * @param passwordVo
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updatePassword(@RequestBody PasswordVo passwordVo) {
        try {
            AdminVo admin = (AdminVo) getCurrentAdmin();
            if (!admin.getId().equals(passwordVo.getUserId())) {
                return BaseResult.failure("非本人不允许编辑个人账户信息");
            }
            return adminService.updatePassword(passwordVo);
        } catch (Exception e) {
            logger.error("修改密码失败", e);
            return BaseResult.failure("修改密码失败");
        }
    }

    /**
     * 根据账号获取用户信息，用于登录
     * @param account
     * @return
     */
    @RequestMapping(value = "/getAdminByAccount", method = RequestMethod.GET)
    @ResponseBody
    public AdminVo getAdminByAccount(@RequestParam("account") String account) {
        try {
            AdminVo adminVo = adminService.getByAccount(account);
            if (null != adminVo) {
                // 更新当前登录用户的最后更新时间
                adminVo.setLastLoginTime(new Date());
                adminService.update(adminVo);
            }
            return adminVo;
        } catch (Exception e) {
            logger.error("根据账号获取用户信息失败", e);
            return null;
        }
    }

}
