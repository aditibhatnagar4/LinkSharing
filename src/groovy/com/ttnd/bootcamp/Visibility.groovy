package com.ttnd.bootcamp

/**
 * Created by aditi on 11/2/16.
 */
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

