package com.ttnd.bootcamp



class UnreadItemEmailJob {

    def userService

    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'emailCron', cronExpression: "0 0 1 ? * MON"
    }

    def execute() {
        // execute job
        userService.sendUnreadItemsEmail()
    }
}
