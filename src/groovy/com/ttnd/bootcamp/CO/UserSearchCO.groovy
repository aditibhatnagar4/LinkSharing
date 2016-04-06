package com.ttnd.bootcamp.CO

import grails.validation.Validateable

@Validateable
class UserSearchCO extends SearchCO{
    Boolean active
}