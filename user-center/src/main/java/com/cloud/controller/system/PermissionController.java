/**
 * @Project user-center
 * @Package com.cloud.controller
 * @FileName PermissionController.java
 */

package com.cloud.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.controller.BaseFormController;
import com.cloud.base.entity.BaseResult;
import com.cloud.base.enumeration.BaseResultCode;
import com.cloud.base.enumeration.SystemCode;
import com.cloud.entity.account.admin.AdminVo;
import com.cloud.entity.system.permission.Permission;
import com.cloud.entity.system.permission.PermissionQo;
import com.cloud.entity.system.permission.PermissionVo;
import com.cloud.service.system.PermissionService;

/**
 * 权限管理controller
 * @Author zivin
 * @Date 2017年10月15日
 */
@RestController
@RequestMapping(value = "/permission")
public class PermissionController extends BaseFormController<Permission, PermissionQo, PermissionVo> {

    @Autowired
    private PermissionService permissionService;

    /*
     * (non-Javadoc)
     * @see com.cloud.base.controller.BaseFormController#getService()
     */
    @Override
    protected PermissionService getService() {
        return permissionService;
    }

    /**
     * 登录用户获取菜单权限信息
     * @return
     */
    @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getPermission() {
        try {
            AdminVo adminVo = (AdminVo) getCurrentAdmin();
            if (null == adminVo) {
                return BaseResult.failure(BaseResultCode.OVERDUE_TOKEN);
            }
            // 重新在数据库中查询菜单权限信息，刷新缓存
            List<PermissionVo> list = permissionService.getPermissionByRoleId(adminVo.getRoleId());
            setMenu(list);

            // 构建返回结构
            JSONObject data = new JSONObject();
            JSONObject wholeList = new JSONObject();
            // 初始化系统结构
            for (SystemCode systemCode : SystemCode.values()) {
                wholeList.put(systemCode.getMsg(), new JSONArray());
            }
            // 构建菜单列表
            if (!CollectionUtils.isEmpty(list)) {
                for (PermissionVo vo : list) {
                    // 单一系统中的模块列表
                    JSONArray moduleList = wholeList.getJSONArray(SystemCode.getByCode(vo.getSystem()).getMsg());
                    JSONObject module;
                    // 模块列表为空，或者最后一个模块不是当前权限的模块，在列表中新建一个模块，否则获取最后一个模块
                    if (moduleList.isEmpty() || !moduleList.getJSONObject(moduleList.size() - 1).getString("name")
                            .equals(vo.getModuleName())) {
                        module = new JSONObject();
                        module.put("name", vo.getModuleName());
                        module.put("icon", vo.getModuleIcon());
                        module.put("path", vo.getModulePath());
                        module.put("code", vo.getModuleCode());
                        module.put("children", new JSONArray());
                        moduleList.add(module);
                    } else {
                        module = moduleList.getJSONObject(moduleList.size() - 1);
                    }

                    // 单一模块中的菜单列表
                    JSONArray menuList = module.getJSONArray("children");
                    JSONObject menu;
                    // 菜单列表为空，或者最后一个菜单不是当前权限的菜单，在列表中新建一个 菜单，否则获取最后一个菜单
                    if (menuList.isEmpty() || !menuList.getJSONObject(menuList.size() - 1).getString("name")
                            .equals(vo.getMenuName())) {
                        menu = new JSONObject();
                        menu.put("name", vo.getMenuName());
                        menu.put("icon", vo.getMenuIcon());
                        menu.put("path", vo.getMenuPath());
                        menu.put("code", vo.getMenuCode());
                        menu.put("children", new JSONArray());
                        menuList.add(menu);
                    } else {
                        menu = menuList.getJSONObject(menuList.size() - 1);
                    }

                    // 单一菜单中的权限列表
                    JSONArray permissionList = menu.getJSONArray("children");
                    JSONObject permission;
                    // 插入权限
                    permission = new JSONObject();
                    permission.put("name", vo.getPermissionName());
                    permission.put("code", vo.getPermissionCode());
                    permissionList.add(permission);
                }
            }
            data.put("menuList", wholeList);
            data.put("systemName", SystemCode.INTEGRATE_SYSTEM.getMsg());

            return BaseResult.success(data);
        } catch (Exception e) {
            logger.error("登录用户获取菜单权限信息失败", e);
            return BaseResult.failure("获取菜单权限信息失败");
        }
    }

    /**
     * 登录用户获取授权权限列表
     * @return
     */
    @RequestMapping(value = "/getPermissionList", method = RequestMethod.GET)
    @ResponseBody
    public List<PermissionVo> getPermissionList() {
        try {
            AdminVo adminVo = (AdminVo) getCurrentAdmin();
            if (null == adminVo) {
                return new ArrayList<>();
            }
            // 重新在数据库中查询菜单权限信息，刷新缓存
            return permissionService.getPermissionByRoleId(adminVo.getRoleId());
        } catch (Exception e) {
            logger.error("登录用户获取授权权限列表失败", e);
            return new ArrayList<>();
        }
    }

}
