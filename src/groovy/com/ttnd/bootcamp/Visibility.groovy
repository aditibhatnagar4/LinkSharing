package com.ttnd.bootcamp

enum Visibility {

    PRIVATE,
    PUBLIC

    static convertVisibility(String visibility) {
        if (visibility.equalsIgnoreCase("private")) {
            return Visibility.PRIVATE
        }

        if (visibility.equalsIgnoreCase("public")) {
            return Visibility.PUBLIC
        }


    }

}

