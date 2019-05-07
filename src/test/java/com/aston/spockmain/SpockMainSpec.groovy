package com.aston.spockmain

import com.aston.spockfunctions.SpockIntegerUtils
import com.aston.spockfunctions.SpockStringUtils
import com.aston.spockmain.impl.SpockMainImpl
import spock.lang.Specification
import spock.lang.Unroll

class SpockMainSpec extends Specification {

    SpockStringUtils mockSpockStringUtils = Mock(SpockStringUtils)
    SpockIntegerUtils mockSpockIntegerUtils = Mock(SpockIntegerUtils)

    SpockMain spockMain = new SpockMainImpl(
            spockStringUtils: mockSpockStringUtils,
            spockIntegerUtils: mockSpockIntegerUtils
    )

    def "Test String with an A"() {
        given:
        String string = "A string, isn't it?"
        String expected = " string, isn't it?"

        when: String result = spockMain.spockString(string)

        then:
        2 * mockSpockStringUtils.removeA(string) >> expected
        0 * _._

        and:
        result != string
        result == expected
    }

    def "Test String with spaces"() {
        given:
        String string = " string with spaces "
        String expected = "stringwithspaces"

        when:
        String result = spockMain.spockString(string)

        then:
        1 * mockSpockStringUtils.clearSpaces(string) >> expected
        0 * _._

        and:
        result != string
        result == expected.toUpperCase()
    }

    @Unroll
    def "Test Integer Tet Cases"() {
        when:
        Integer result = spockMain.spockMath(integer1, integer2)

        then:
        if (testSub) {
            1 * mockSpockIntegerUtils.subtraction(integer1, integer2) >> expected
        } else {
            1 * mockSpockIntegerUtils.addition(integer1, integer2) >> expected
        }
        0 * _._

        and:
        result == 0

        where:
        integer1 | integer2 | testSub | expected
        1        | 2        | false   | 3
        2        | 1        | true    | 1
    }
}
