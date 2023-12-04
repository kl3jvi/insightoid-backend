package io.kl3jvi.models

import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class UserKtTest {

    @Test
    fun testGenerateUUIDFromUsername() {
        val result = generateUUIDFromUsername("kl3jvi", "kl3jvi123")
        assertEquals("3128ac87-3af8-3ba8-8e35-73639e5cc1c4", result)
    }
}