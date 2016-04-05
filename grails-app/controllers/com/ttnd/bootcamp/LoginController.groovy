package com.ttnd.bootcamp

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.WebAttributes

import javax.servlet.http.HttpServletResponse

@Secured(['permitAll'])
class LoginController {

//    def index() {
//        if (session.user) {
//            forward(controller: "user", action: "index")
//        } else {
//            List<Resource> resources = Resource.getTopPosts()
//            List<Resource> recentPosts = Topic.getRecentPosts()
//            render view: 'homePage', model: [resources  : resources,
//                                             recentPosts: recentPosts]
//        }
//    }

//    def loginHandler(String username, String password) {
//        User user = User.findByUsernameAndPassword(username, password)
//        if (user) {
//            if (user.enabled) {
//                session.user = user
//                flash.message="Login success."
//
//
//                //return
//            } else {
//                flash.error = 'Your account is not enabled'
//
//            }
//        } else {
//            flash.error = "Could not find user with the specified username and password."
//
//
//           // render ([flash.error] as JSON)
//        }
//
//        redirect(controller: "login", action: "index")
//    }

    /**
     * Dependency injection for the authenticationTrustResolver.
     */
    def authenticationTrustResolver

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    /**
     * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
     */
    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        } else {
            redirect action: 'auth', params: params
        }
    }

    /**
     * Show the login page.
     */
    def auth() {

        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
        }

        String view = '/login/homePage'
        List<Resource> resources = Resource.getTopPosts()
        List<Resource> recentPosts = Topic.getRecentPosts()
//            render view: 'homePage', model: [resources  : resources,
//                                             recentPosts: recentPosts]
        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        render view: view, model: [postUrl            : postUrl,
                                   rememberMeParameter: config.rememberMe.parameter,
                                   resources          : resources,
                                   recentPosts        : recentPosts]
    }

    /**
     * The redirect action for Ajax requests.
     */
    def authAjax() {
        response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
        response.sendError HttpServletResponse.SC_UNAUTHORIZED
    }

    /**
     * Show denied page.
     */
    def denied() {
        if (springSecurityService.isLoggedIn() &&
                authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
            redirect action: 'full', params: params
        }
    }

    /**
     * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
     */
    def full() {
        def config = SpringSecurityUtils.securityConfig
        render view: '/login/homePage', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication),
                        postUrl  : "${request.contextPath}${config.apf.filterProcessesUrl}"]
    }

    /**
     * Callback after a failed login. Redirects to the auth page with a warning message.
     */
    def authfail() {

        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            } else if (exception instanceof CredentialsExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.passwordExpired")
            } else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            } else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            } else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        } else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    /**
     * The Ajax success redirect url.
     */
    def ajaxSuccess() {
        render([success: true, username: springSecurityService.authentication.name] as JSON)
    }

    /**
     * The Ajax denied redirect url.
     */
    def ajaxDenied() {
        render([error: 'access denied'] as JSON)
    }


    RedirectStrategy redirectStrategy

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }

    def validateEmail() {

        Integer numUser = User.countByemail(params.email)

        Boolean result = numUser ? false : true

        render result
    }

    def validateUserName() {
        Integer numUser = User.countByUsername(params.userName)
        Boolean result = numUser ? false : true

        render result

    }
}
