package com.wy.shopping.user.service;

import com.wy.shopping.common.service.entity.user.*;
import com.wy.shopping.common.service.enumeration.user.UserStateEnum;
import com.wy.shopping.common.service.enumeration.user.UserTypeEnum;
import com.wy.shopping.common.service.exception.BizException;
import com.wy.shopping.common.service.exception.ExceptionCodeEnum;
import com.wy.shopping.common.service.facade.user.UserService;
import com.wy.shopping.common.service.req.BatchReq;
import com.wy.shopping.common.service.req.user.*;
import com.wy.shopping.common.service.utils.Assert;
import com.wy.shopping.common.service.utils.BcryptUtil;
import com.wy.shopping.common.service.utils.KeyGenerator;
import com.wy.shopping.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangyong
 * @description
 * @date 2019-05-09
 */
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
@Service
public class UserServiceImpl implements UserService {

    private final String USER_KEY_PREFIX = "USER";

    @Resource
    private UserRepository userRepository;

    /**
     * 用户登录
     *
     * @param loginReq 登录信息
     * @return 登录用户
     */
    @Override
    public UserEntity login(LoginReq loginReq) {
        //校验参数
        checkParam(loginReq);
        /*
        查询用户是否已经存在
         */
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);
        List<UserEntity> userEntityList = userRepository.findUsers(userQueryReq);
        Assert.startCheck().notEmpty(userEntityList, ExceptionCodeEnum.LOGIN_FAIL);
        UserEntity user = userEntityList.get(0);
        /*
        校验密码
         */
        boolean check = BcryptUtil.checkpw(loginReq.getPassword(), user.getPassword());
        if (!check) {
            throw new BizException(ExceptionCodeEnum.PASSWORD_ERROR);
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param registerReq 注册参数
     * @return 注册后的用户
     */
    @Override
    public UserEntity register(RegisterReq registerReq) {
        checkParam(registerReq);
        UserEntity user = UserEntity.builder()
                .id(generateKey())
                .username(registerReq.getUsername())
                .licencePic(registerReq.getLicencePic())
                .mail(registerReq.getMail())
                .phone(registerReq.getPhone())
                .registerTime(LocalDateTime.now())
                .userTypeEnum(UserTypeEnum.getByCode(registerReq.getUserType()))
                .build();
        /*
        如果是企业用户则需要认证
         */
        if (user.getUserTypeEnum() == UserTypeEnum.COMPANY) {
            user.setUserStateEnum(UserStateEnum.OFF);
        }

        /*
        对用户密码进行加密存储，这里使用的是BCrypt
         */
        String hashpw = BcryptUtil.encrypt(registerReq.getPassword());
        user.setPassword(hashpw);

        userRepository.createUser(user);
        return user;
    }

    /**
     * 查找用户
     *
     * @param userQueryReq 查找条件
     * @return 用户实体列表
     */
    @Override
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userRepository.findUsers(userQueryReq);
    }

    /**
     * 批量更新用户状态
     *
     * @param userStateReqs 用户状态列表
     */
    @Override
    public void batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {
        Assert.startCheck(userStateReqs, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(userStateReqs.getReqList(), ExceptionCodeEnum.PARAM_NULL);
        Map<Integer, List<UserStateReq>> userStateMap = userStateReqs.getReqList().stream().collect(Collectors.groupingBy(UserStateReq::getUserState));
        /*
        批量更新用户状态
         */
        for (Map.Entry<Integer, List<UserStateReq>> entry : userStateMap.entrySet()) {
            List<String> userIdList = entry.getValue().stream().map(UserStateReq::getUserId).collect(Collectors.toList());
            userRepository.batchUpdateUserState(entry.getKey(), userIdList);
        }
    }

    /**
     * 创建管理员账号
     *
     * @param adminCreateReq 账号信息
     */
    @Override
    public void createAdminUser(AdminCreateReq adminCreateReq) {
        Assert.startCheck(adminCreateReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(adminCreateReq.getUsername(), ExceptionCodeEnum.USERNAME_NULL)
                .notEmpty(adminCreateReq.getPassword(), ExceptionCodeEnum.PASSWD_NULL)
                .notEmpty(adminCreateReq.getPhone(), ExceptionCodeEnum.PHONE_NULL)
                .notEmpty(adminCreateReq.getRoleId(), ExceptionCodeEnum.ROLE_NULL);

        List<UserEntity> users = userRepository.findUsers(UserQueryReq.builder()
                .phone(adminCreateReq.getPhone())
                .username(adminCreateReq.getUsername()).build());
        if (!CollectionUtils.isEmpty(users)) {
            throw new BizException(ExceptionCodeEnum.MAIL_OR_PHONE_ALREADY_EXISTS);
        }
        userRepository.createUser(UserEntity.builder()
                .id(generateKey())
                .username(adminCreateReq.getUsername())
                .userStateEnum(UserStateEnum.ON)
                .userTypeEnum(UserTypeEnum.ADMIN)
                .registerTime(LocalDateTime.now())
                .password(adminCreateReq.getPassword())
                .phone(adminCreateReq.getPhone())
                .roleEntity(RoleEntity.builder()
                        .id(adminCreateReq.getRoleId())
                        .build())
                .build());
    }

    /**
     * 查看所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<RoleEntity> findRoles() {
        return userRepository.findRoles();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteRole(String roleId) {
        Assert.startCheck().notEmpty(roleId, ExceptionCodeEnum.PARAM_NULL);
        userRepository.deleteRole(roleId);
        userRepository.deleteRoleMenu(roleId);
        userRepository.deleteRolePermission(roleId);
    }

    /**
     * 更新角色的菜单
     *
     * @param roleMenuReq 角色菜单请求信息
     */
    @Override
    public void updateMenuOfRole(RoleMenuReq roleMenuReq) {
        Assert.startCheck(roleMenuReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(roleMenuReq.getRoleId(), ExceptionCodeEnum.ROLE_NULL)
                .notEmpty(roleMenuReq.getMenuIdList(), ExceptionCodeEnum.MENUIDLIST_NULL);
        userRepository.deleteRoleMenu(roleMenuReq.getRoleId());
        userRepository.insertRoleMenu(roleMenuReq);
    }

    /**
     * 更新角色的权限
     *
     * @param rolePermissionReq 角色权限的请求信息
     */
    @Override
    public void updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        Assert.startCheck(rolePermissionReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(rolePermissionReq.getPermissionIdList(), ExceptionCodeEnum.PERMISSIONIDLIST_NULL);
        userRepository.deleteRolePermission(rolePermissionReq.getRoleId());
        userRepository.insertRolePermission(rolePermissionReq);
    }

    /**
     * 查询所有的权限
     *
     * @return 权限列表
     */
    @Override
    public List<PermissionEntity> findPermissions() {
        return userRepository.findPermissions();
    }

    /**
     * 查询所有的菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<MenuEntity> findMenus() {
        return userRepository.findMenus();
    }

    /**
     * 查询所有的地址信息
     *
     * @param userId 用户id
     * @return 用户地址列表
     */
    @Override
    public List<LocationEntity> findLocations(String userId) {
        return userRepository.findLocations(userId);
    }

    /**
     * 添加地址信息
     *
     * @param locationCreateReq 添加的位置信息
     * @param userId            用户id
     * @return 添加的地址信息记录id
     */
    @Override
    public String createLocation(LocationCreateReq locationCreateReq, String userId) {
        Assert.startCheck(locationCreateReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(userId, ExceptionCodeEnum.USERID_NULL)
                .notEmpty(locationCreateReq.getLocation(), ExceptionCodeEnum.LOCATION_NULL)
                .notEmpty(locationCreateReq.getPhone(), ExceptionCodeEnum.PHONE_NULL)
                .notEmpty(locationCreateReq.getName(), ExceptionCodeEnum.NAME_NULL);
        LocationEntity locationEntity = LocationEntity.builder()
                .id(generateKey())
                .userId(userId)
                .location(locationCreateReq.getLocation())
                .name(locationCreateReq.getName())
                .phone(locationCreateReq.getPhone())
                .postCode(locationCreateReq.getPostCode())
                .build();
        userRepository.createLocation(locationEntity);
        return locationEntity.getId();
    }

    /**
     * 删除地址
     *
     * @param locationId 地址id
     * @param userId     用户id
     */
    @Override
    public void deleteLocation(String locationId, String userId) {
        userRepository.deleteLocation(locationId, userId);
    }

    /**
     * 修改地址
     *
     * @param locationUpdateReq 需要更新的地址信息
     * @param userId            用户id
     */
    @Override
    public void modifyLocation(LocationUpdateReq locationUpdateReq, String userId) {
        Assert.startCheck(locationUpdateReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(userId, ExceptionCodeEnum.USERID_NULL)
                .allNotEmpty(ExceptionCodeEnum.LOCATIONUPDATEREQ_NULL,
                        locationUpdateReq.getLocation(),
                        locationUpdateReq.getName(),
                        locationUpdateReq.getLocationId(),
                        locationUpdateReq.getPostCode(),
                        locationUpdateReq.getPhone());
        userRepository.updateLocation(locationUpdateReq, userId);
    }

    /**
     * 用户表主键："USER" + uuid
     *
     * @return
     */
    private String generateKey() {
        return USER_KEY_PREFIX + KeyGenerator.generateKey();
    }

    /**
     * 创建用户请求对象
     *
     * @param loginReq
     * @return
     */
    private UserQueryReq buildUserQueryReq(LoginReq loginReq) {
        return UserQueryReq.builder()
                .password(loginReq.getPassword())
                .username(loginReq.getUsername())
                .mail(loginReq.getMail())
                .phone(loginReq.getPhone())
                .orderByRegisterTime(1)
                .build();
    }

    /**
     * 注册信息参数校验
     *
     * @param loginReq 注册信息参数
     */
    private void checkParam(LoginReq loginReq) {
        Assert.startCheck(loginReq, ExceptionCodeEnum.PARAM_NULL)
                /*
                密码不能为空
                */
                .notEmpty(loginReq.getPassword(), ExceptionCodeEnum.PARAM_NULL)
                /*
                手机、mail、用户名、至少要有一个
                 */
                .allNotEmpty(ExceptionCodeEnum.AUTH_NULL, loginReq.getUsername(), loginReq.getMail(), loginReq.getPhone());
    }

    /**
     * 校验注册参数，如果是非企业用户则不需要校验营业执照，和企业名称
     * 普通用户的名称可以为空
     *
     * @param registerReq 注册信息
     */
    private void checkParam(RegisterReq registerReq) {
        Assert.startCheck(registerReq, ExceptionCodeEnum.PARAM_NULL)
                .notEmpty(registerReq.getPassword(), ExceptionCodeEnum.PASSWD_NULL)
                .notEmpty(registerReq.getPhone(), ExceptionCodeEnum.PHONE_NULL)
                .notEmpty(registerReq.getMail(), ExceptionCodeEnum.MAIL_NULL)
                .notNull(registerReq.getUserType(), ExceptionCodeEnum.USERTYPE_NULL);
        if (registerReq.getUserType() == UserTypeEnum.COMPANY.getCode()) {
            Assert.startCheck()
                    .notEmpty(registerReq.getLicencePic(), ExceptionCodeEnum.LICENCE_NULL)
                    .notEmpty(registerReq.getUsername(), ExceptionCodeEnum.COMPANYNAME_NULL);
        }
        UserQueryReq userQueryReq = UserQueryReq.builder()
                .mail(registerReq.getMail())
                .phone(registerReq.getPhone()).build();
        List<UserEntity> users = userRepository.findUsers(userQueryReq);
        /*
        手机和邮箱不能重复
         */
        if (users.size() != 0) {
            throw new BizException(ExceptionCodeEnum.MAIL_OR_PHONE_ALREADY_EXISTS);
        }
    }

    @Override
    public String test() {
        return "hello world!";
    }
}
