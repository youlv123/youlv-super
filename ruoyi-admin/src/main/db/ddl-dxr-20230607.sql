# 二维码查看权限表
CREATE TABLE qrcode_permission
(
    permission_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    item_id       BIGINT(20) COMMENT '物品ID',
    category_id   bigint(20) COMMENT '分类ID',
    qrcode_id     BIGINT(20) COMMENT '二维码id',
    user_id       bigint(20) COMMENT '用户ID',
    user_name     varchar(30) COMMENT '用户名称',
    edit_flag     char(1)    NOT NULL DEFAULT 'N' COMMENT '二维码修改权限，N表示无权限，Y表示有权限',
    created_by    VARCHAR(64) COMMENT '创建人',
    created_date  DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_by    VARCHAR(64) COMMENT '更新人',
    updated_date  DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    del_flag      char(1)             DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (permission_id)
) COMMENT ='二维码查看权限表';