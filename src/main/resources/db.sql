CREATE TABLE `Counters`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `count`     int(11) NOT NULL DEFAULT '1',
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

CREATE TABLE `RegInfo`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `company` VARCHAR(255) NOT NULL DEFAULT '',
    `visitorName`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorFromAddr`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorTel`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorCount`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorCarID`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `note`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorToAddr`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorToApartment`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorsReceptionistName`  VARCHAR(255) NOT NULL DEFAULT '',
    `visitorsReceptionistTel`  VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8