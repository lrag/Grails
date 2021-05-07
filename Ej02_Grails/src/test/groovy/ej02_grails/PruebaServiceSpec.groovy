package ej02_grails

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class PruebaServiceSpec extends Specification implements ServiceUnitTest<PruebaService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
