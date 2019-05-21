package com.wy.shopping.common.service.facade.user;

import com.wy.shopping.common.service.entity.user.*;
import com.wy.shopping.common.service.req.BatchReq;
import com.wy.shopping.common.service.req.user.*;

import java.util.List;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param loginReq 登录参数
     * @return
     */
    UserEntity login(LoginReq loginReq);

    UserEntity register(RegisterReq registerReq);

    List<UserEntity> findUsers(UserQueryReq userQueryReq);

    void batchUpdateUserState(BatchReq<UserStateReq> userStateReqs);

    void createAdminUser(AdminCreateReq adminCreateReq);

    List<RoleEntity> findRoles();

    void deleteRole(String roleId);

    void updateMenuOfRole(RoleMenuReq roleMenuReq);

    void updatePermissionOfRole(RolePermissionReq rolePermissionReq);

    List<PermissionEntity> findPermissions();

    List<MenuEntity> findMenus();

    List<LocationEntity> findLocations(String userId);

    String createLocation(LocationCreateReq locationCreateReq, String userId);

    void deleteLocation(String locationId, String userId);

    void modifyLocation(LocationUpdateReq locationUpdateReq, String userId);

    String test();
}
