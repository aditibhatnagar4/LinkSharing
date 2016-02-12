package linksharing

import com.ttnd.bootcamp.Topic
import com.ttnd.bootcamp.User

class Test1Controller {

    def index() {

        User user = new User()
        user.addToTopics(new Topic())
        user.save()


    }
}
