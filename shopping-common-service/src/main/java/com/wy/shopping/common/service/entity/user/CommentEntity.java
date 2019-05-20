package com.wy.shopping.common.service.entity.user;

import lombok.*;

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
public class CommentEntity {

    /**
     * id
     */
    private String id;
    /**
     * 评论
     */
    private String comment;
    /**
     * 文章id
     */
    private String articleId;
}
