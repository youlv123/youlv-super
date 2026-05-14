CREATE TABLE wm_notice.push_wx_message_record (
                                                  push_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                                  staff_id VARCHAR(64)  DEFAULT NULL COMMENT '员工编号',
                                                  staff_name VARCHAR(100)  DEFAULT NULL COMMENT '员工姓名',
                                                  wx_message_content TEXT COMMENT '微信消息内容',
                                                  message_type char(1) COMMENT '微信消息类型，0客户经理数据，1财富总监数据，2管理员数据，3客户销户提醒',
                                                  biz_date VARCHAR(100) DEFAULT NULL  COMMENT '业务日期,样式：20230618',
                                                  push_flag char(1) NOT NULL DEFAULT '0' COMMENT '推送标识，1推送成功，2推送失败',
                                                  error_log text DEFAULT NULL COMMENT '推送错误信息，标识为2推送失败才会有',
                                                  created_by VARCHAR(64) NOT NULL DEFAULT 'system' COMMENT '创建人',
                                                  created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                  updated_by VARCHAR(64) NOT NULL DEFAULT 'system' COMMENT '更新人',
                                                  updated_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                  del_flag char(1) DEFAULT '0'  COMMENT '逻辑删除',
                                                  PRIMARY KEY (push_id)
) COMMENT='企业微信推送消息记录表';