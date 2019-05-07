package com.aston.businesslogic

import com.aston.spockfunctions.SpockIntegerUtils
import com.aston.spockfunctions.SpockStringUtils
import com.aston.spockmain.SpockMain
import com.aston.spockmain.impl.SpockMainImpl
import com.aston.spockobjects.SpockObject
import spock.lang.Specification

class SpockBusinessLogicSpec extends Specification {

    SpockBusinessLogic spockBusinessLogic = new SpockBusinessLogic()
    SpockIntegerUtils spockIntegerUtils = new SpockIntegerUtils()
    SpockStringUtils spockStringUtils = new SpockStringUtils()

    SpockMain spockMain = new SpockMainImpl(
            spockStringUtils: spockStringUtils,
            spockIntegerUtils: spockIntegerUtils
    )

    def "test setSpockIntegerUtils"() {
        when:
        spockBusinessLogic.setSpockIntegerUtils(spockIntegerUtils)

        then:
        0 * _._

        and:
        spockBusinessLogic.spockIntegerUtils == spockIntegerUtils
    }

    def "test setSpockStringUtils"() {
        when:
        spockBusinessLogic.setSpockStringUtils(spockStringUtils)

        then:
        0 * _._

        and:
        spockBusinessLogic.spockStringUtils == spockStringUtils
    }

    def "test setSpockMain"() {
        when: spockBusinessLogic.setSpockMain(spockMain)

        then:
        0 * _._

        and:
        spockBusinessLogic.spockMain == spockMain
    }

    def "test handleSpockObjectMath"() {
        given:
        spockBusinessLogic.setSpockMain(spockMain)
        ArrayList<String> errors = new ArrayList<>()
        SpockObject so = new SpockObject(spockInt: spockInteger,
                                         spockBoolean: spockBoolean,
                                         spockString: spockString,
                                         errorMessage: errors)

        when:
        spockBusinessLogic.setSpockIntegerUtils(spockIntegerUtils)
        spockBusinessLogic.setSpockStringUtils(spockStringUtils)
        spockBusinessLogic.handleSpockObjectMath(so, integer)

        then:
        0 * _._

        and:
        so.spockInt == result
        if (so.errorMessage.size() > 0) {
            so.errorMessage.get(0) == spockError
        }

        where:
        spockBoolean | spockInteger | spockString | integer | result | spockError
        true         | 2            | "sup"       | 1       | 3      | null
        false        | 2            | "sup"       | 1       | 1      | null
        false        | 0            | "sup"       | 1       | -1     | "Are negative numbers ever acceptable, yo?"
        true         | -1           | "sup"       | 1       | 0      | null
        true         | -2           | "sup"       | 1       | -1     | "re negtive numbers ever cceptble, yo?"
    }

    def "test validateSpockObject"() {
        given:
        SpockObject so = new SpockObject(spockString: "A", spockInt: 0, spockBoolean: true)

        when:
        spockBusinessLogic.validateSpockObject(so)

        then:
        0 * _._

        and:
        so.hasMessages()
        so.hasErrorMessage("Spock Integer must be one if Spock Boolean is true")
        so.hasErrorMessage("Spock String must be empty if Spock Integer is zero")
        so.errorMessage.size() == 2
    }

}
