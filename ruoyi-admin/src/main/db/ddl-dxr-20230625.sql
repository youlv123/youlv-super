CREATE TABLE work_time_record (
                                  record_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  belongs_projects char(1) NOT NULL  COMMENT '所属项目',
                                  today_date VARCHAR(64) NOT NULL COMMENT '当天日期',
                                  start_time DATETIME NOT NULL  COMMENT '开始时间',
                                  end_time DATETIME NOT NULL  COMMENT '结束时间',
                                  total_minutes_duration DECIMAL(6,2) NOT NULL  COMMENT '总共分钟时长',
                                  total_hours_duration DECIMAL(6,2) NOT NULL  COMMENT '总共小时时长',
                                  work_content TEXT COMMENT '工作内容描述',
                                  created_by VARCHAR(64) COMMENT '创建人',
                                  created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  updated_by VARCHAR(64) COMMENT '更新人',
                                  updated_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  del_flag char(1) DEFAULT '0'  COMMENT '逻辑删除',
                                  PRIMARY KEY (record_id)
) COMMENT='记录工作时长表';