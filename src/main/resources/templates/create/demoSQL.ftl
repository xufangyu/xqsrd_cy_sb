DROP TABLE IF EXIST `x_${demoNameLower}`;
CREATE TABLE `x_${demoNameLower}` (
  `xid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一性主键，自增1',
<#list tableRows?keys as key>
  `${key}` varchar(255) DEFAULT NULL COMMENT 'xxx',
</#list>
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`xid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='x_${demoNameLower}表'