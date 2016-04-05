package com.ttnd.bootcamp;

import grails.test.GrailsMock;
import grails.test.mixin.TestFor;

import static org.junit.Assert.*;

/**
 * Created by aditi on 2/4/16.
 */
@TestFor(LinksharingTagLib)
public class LinksharingTagLibTest {
    def"Mark as read"()

    {
        setup:
        tagLib.session.user = new User(id:1)
        LinkResource linkResource = new LinkResource(id:1)
        def mockedReadingItem = new GrailsMock(ReadingItem)
        mockedReadingItem.demand. static.findByResourceAndUser
    }

}