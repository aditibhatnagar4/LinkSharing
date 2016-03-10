package com.ttnd.bootcamp

enum Seriousness {

    SERIOUS,
    VERY_SERIOUS,
    CASUAL

    static convertSeriousness(String seriousness) {
        if (seriousness.equalsIgnoreCase("serious")) {
            return Seriousness.SERIOUS
        }

        if (seriousness.equalsIgnoreCase("very serious")) {
            return Seriousness.VERY_SERIOUS
        }

        if (seriousness.equalsIgnoreCase("casual")) {
            return Seriousness.CASUAL
        }
    }
}