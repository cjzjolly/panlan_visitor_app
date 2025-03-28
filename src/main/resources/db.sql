CREATE TABLE `Counters`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `count`     int(11) NOT NULL DEFAULT '1',
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

CREATE TABLE `RegInfo` (
                           `id` int (11) NOT NULL AUTO_INCREMENT,
                           `company` varchar (255) NULL DEFAULT '' COMMENT '公司名',
                           `visitorName` varchar (255) NOT NULL DEFAULT '' COMMENT '来访者',
                           `visitorFromAddr` longtext NULL COMMENT '来访人地址',
                           `visitorTel` varchar (255) NOT NULL DEFAULT '' COMMENT '来访人联系方式',
                           `visitorCount` int (11) NOT NULL COMMENT '来访人数',
                           `visitorCarID` varchar (255) NULL DEFAULT '' COMMENT '车牌号码',
                           `visitorTime` datetime NOT NULL COMMENT '预约来访时间',
                           `remark` longtext NULL COMMENT '备注',
                           `visitorToAddr` longtext NOT NULL COMMENT '到访地址',
                           `visitorToApartment` varchar (255) NOT NULL DEFAULT '' COMMENT '部门名称',
                           `visitorsReceptionistName` varchar (255) NOT NULL DEFAULT '' COMMENT '接待人',
                           `visitorsReceptionistTel` varchar (255) NOT NULL DEFAULT '' COMMENT '接待人联系方式',
                           PRIMARY KEY (`id`)
) ENGINE = innodb DEFAULT CHARACTER SET = "utf8"