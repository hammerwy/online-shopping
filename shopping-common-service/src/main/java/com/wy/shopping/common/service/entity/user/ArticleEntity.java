package com.wy.shopping.common.service.entity.user;

import lombok.*;

import java.util.List;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {

    private String id;
    private String title;
    private String content;
    private UserEntity userEntity;
    private List<CommentEntity> commentEntityList;

}
