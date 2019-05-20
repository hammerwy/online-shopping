package com.wy.shopping.user.service.repository;

import com.wy.shopping.common.service.entity.user.*;
import com.wy.shopping.common.service.req.user.LocationUpdateReq;
import com.wy.shopping.common.service.req.user.RoleMenuReq;
import com.wy.shopping.common.service.req.user.RolePermissionReq;
import com.wy.shopping.common.service.req.user.UserQueryReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangyong
 * @description
 * @date 2019-05-09
 */
@Repository
public interface UserRepository {
    /**
     * 查找服务条件的用户集合
     *
     * @param userQueryReq 查找条件
     * @return 用户集合
     */
    List<UserEntity> findUsers(UserQueryReq userQueryReq);

    /**
     * 添加一个用户信息
     *
     * @param userEntity 用户信息
     * @return affected row
     */
    Integer createUser(UserEntity userEntity);

    /**
     * 批量更新用户状态
     *
     * @param userStateCode 状态码
     * @param userIdList    用户id列表
     * @return affected rows
     */
    Integer batchUpdateUserState(@Param("userStateCode") Integer userStateCode,
                                 @Param("userIdList") List<String> userIdList);

    /**
     * 查询所有的角色
     *
     * @return 角色集合
     */
    List<RoleEntity> findRoles();

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    Integer deleteRole(String roleId);

    /**
     * 删除角色-权限关系
     *
     * @param roleId
     * @return
     */
    Integer deleteRolePermission(String roleId);

    /**
     * 删除角色-菜单关系
     *
     * @param roleId
     * @return
     */
    Integer deleteRoleMenu(String roleId);

    /**
     * 插入角色-菜单关系
     *
     * @param roleMenuReq
     */
    void insertRoleMenu(@Param("roleMenuReq") RoleMenuReq roleMenuReq);

    /**
     * 插入角色-权限关系
     *
     * @param rolePermissionReq
     */
    void insertRolePermission(@Param("rolePermissionReq") RolePermissionReq rolePermissionReq);

    /**
     * 查询所有权限
     *
     * @return 权限列表
     */
    List<PermissionEntity> findPermissions();

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    List<MenuEntity> findMenus();

    /**
     * 查询指定用户的地址信息
     *
     * @param userId 用户ID
     * @return 地址信息列表
     */
    List<LocationEntity> findLocations(String userId);

    /**
     * 新增收货地址
     *
     * @param locationEntity 收货地址
     */
    void createLocation(LocationEntity locationEntity);

    void deleteLocation(@Param("locationId") String locationId, @Param("userId") String userId);

    /**
     * 修改收货地址
     *
     * @param locationUpdateReq 收货地址修改请求
     * @param userId            用户ID
     */
    void updateLocation(@Param("locationUpdateReq") LocationUpdateReq locationUpdateReq, @Param("userId") String userId);
}
