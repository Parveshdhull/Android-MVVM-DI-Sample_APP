package com.parvesh.pixasearch.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {

    /**
     * Tests for Utils function:
     * containsSpecialCharacters(input: String)
     */

    @Test
    fun `string contains special character, returns true`() {
        assertThat(Utils.containsSpecialCharacters("app+le//")).isTrue()
    }

    @Test
    fun `string doesn't contain special character, returns false`() {
        assertThat(Utils.containsSpecialCharacters("apple")).isFalse()
    }

    /**
     * Tests for Utils function:
     * urlEncodeString(input: String)
     */

    @Test
    fun `string is not url encoded, url encodes`() {
        assertThat(Utils.urlEncodeString("apple mango")).isEqualTo("apple+mango")
    }

    /**
     * Tests for Utils function:
     * removeSpecialCharacters(input: String)
     */

    @Test
    fun `string contains special characters, removes special character`() {
        assertThat(Utils.removeSpecialCharacters("app+le//")).isEqualTo("apple")
    }

    @Test
    fun `string doesn't contain special character, does nothing`() {
        assertThat(Utils.removeSpecialCharacters("apple")).isEqualTo("apple")
    }
}