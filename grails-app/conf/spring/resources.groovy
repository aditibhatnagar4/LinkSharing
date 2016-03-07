import com.ttnd.bootcamp.customBean

// Place your Spring DSL code here
beans = {

    myBean(customBean) {
        name = "aditi"
        age = 10
    }

    customBeanUsingConstructor(customBean,"aditi",10){}
    customBean1(customBean,"rahul",10){}

    customBean(customBean){
        bean.scope='prototype'
    }
}
